package com.springboot.map.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Result {

    boolean result;
    String msg;
    Map<String, Object> value;
}
