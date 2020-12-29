package com.px.bean;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    private Map<String,Object> map = new HashMap<>();

    public Msg(Map<String, Object> map) {
        this.map = map;
    }

    public Msg() {
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
    public Msg add(String key,Object value){
        this.getMap().put(key,value);
        return this;
    }
}
