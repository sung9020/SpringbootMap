package com.springboot.map.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ResultDto {

    boolean result;
    String msg;
    Map<String, Object> value;
}
