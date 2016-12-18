package org.record.tiny.utils;

import java.util.HashMap;
import java.util.Map;

public class StickEvent
{
    private Map<String, Object> stickMap ;

    public StickEvent()
    {
        stickMap = new HashMap<>();
    }

    public StickEvent(Map<String, Object> stick)
    {
        stickMap = stick;
    }

    public Map<String, Object> getStickMap()
    {
        return stickMap;
    }

    public void setStickMap(Map<String, Object> stickMap)
    {
        this.stickMap = stickMap;
    }
}
