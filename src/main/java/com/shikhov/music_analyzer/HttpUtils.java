package com.shikhov.music_analyzer;

import org.apache.http.NameValuePair;

import java.util.List;

public class HttpUtils {
    static public String getParamValue(List<NameValuePair> pairs, String key) {
        String value = null;
        for (NameValuePair pair : pairs) {
            if (pair.getName().equals(key)) {
                value = pair.getValue();
            }
        }
        return value;
    }
}
