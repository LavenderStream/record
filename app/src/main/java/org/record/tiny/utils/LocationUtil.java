package org.record.tiny.utils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.record.tiny.ui.RecordApplication;

@SuppressWarnings("All")
public class LocationUtil {
    private static final int SCAN_SPAN_TIME = 1000;
    private static final String COOR_TYPE = "bd09ll";

    private static LocationUtil instance;
    private LocationClient mLocationClient = null;
    private BDLocationListener mListener = null;
    private Callback.TCallBack<String> mAddressCallBack;

    private LocationUtil() {
    }

    public static LocationUtil getInstance() {
        if (instance == null) {
            synchronized (LocationUtil.class) {
                if (instance == null) {
                    instance = new LocationUtil();
                }
            }
        }
        return instance;
    }

    public void start(Callback.TCallBack<String> address) {
        if (address != null) {
            mAddressCallBack = address;

            setLocationConfig();
            registerLocation();
        }
    }

    private void registerLocation() {
        mLocationClient.registerLocationListener(mListener);
        mLocationClient.start();
    }

    public void stop() {
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.stop();
            mLocationClient.unRegisterLocationListener(mListener);
        }
    }

    private void setLocationConfig() {
        mLocationClient = new LocationClient(RecordApplication.getContext());
        mListener = new LocationListener();

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType(COOR_TYPE);//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(SCAN_SPAN_TIME);
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(false);//可选，默认false,设置是否使用gps
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setPriority(LocationClientOption.NetWorkFirst);
        mLocationClient.setLocOption(option);
    }

    private String filterLocal(String local) {
        //把数字徒变成特殊字符
        local = local.replaceAll("\\d+", "#");
        LogUtils.d("LocationUtil -> filterLocal: " + local);
        if (local.contains("#"))
            return local.substring(0, local.indexOf("#"));
        return local;
    }

    class LocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location != null && location.getPoiList() != null) {
                if (mAddressCallBack != null) {
                    if (location.getPoiList().size() != 0) {
                        String local = location.getPoiList().get(0).getName();
                        mAddressCallBack.Done(filterLocal(local));
                    } else {
                        mAddressCallBack.Done(location.getCity());
                    }
                }
            } else {
                if (mAddressCallBack != null) {
                    mAddressCallBack.Done(null);
                }
            }

            if (mLocationClient != null) {
                mLocationClient.stop();
            }
        }
    }
}
