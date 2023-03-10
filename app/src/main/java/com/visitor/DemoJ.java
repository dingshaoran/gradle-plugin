package com.visitor;

import org.json.JSONException;
import org.json.JSONObject;

class DemoJ {

    void demo() {
        JSONObject aa = null;
        try {
            aa = new JSONObject("aaaa");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        System.out.println(aa);
    }
}
