package com.example.jayson.tools;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Map;

/**
 * 跳转时，传递Map参数工具
 */
public class SerializableMapTool implements Serializable {
    private Map<String, Object> map;

    private Map<String, Object> getMap() {
        return map;
    }

    private void setMap(Map<String, Object> map) {
        this.map = map;
    }

    private static SerializableMapTool setSerializableMap(Map<String, Object> map) {
        SerializableMapTool m = new SerializableMapTool();
        m.setMap(map);
        return m;
    }

    public static Bundle setBundleMap(Map<String, Object> map) {
        Bundle bundle = new Bundle();
        SerializableMapTool m = setSerializableMap(map);
        bundle.putSerializable("map", m);
        return bundle;
    }

    public static Map<String, Object> getIntentMap(Intent intent, String intentKey) {
        Bundle bundleExtra = intent.getBundleExtra(intentKey);
        assert bundleExtra != null;
        SerializableMapTool serializableMap = (SerializableMapTool) bundleExtra.getSerializable("map");
        assert serializableMap != null;
        return serializableMap.getMap();
    }
}
