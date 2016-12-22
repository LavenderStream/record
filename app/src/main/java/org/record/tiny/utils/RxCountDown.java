package org.record.tiny.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

@SuppressWarnings("All")
public class RxCountDown {
    public static Flowable<Integer> countdown(int time) {
        if (time < 0) time = 0;

        final int countTime = time;
        return Flowable.interval(0, 1, TimeUnit.SECONDS)
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long increaseTime) throws Exception {
                        return countTime - increaseTime.intValue();
                    }
                })
                .take(countTime + 1);
    }
}
