package com.springboot.map.dto;

import lombok.Data;

import java.util.Map;

@Data
public class MetaDto {

    Map<String, Object> same_name;
    String pageable_count;
    String total_count;
    String is_end;

}
