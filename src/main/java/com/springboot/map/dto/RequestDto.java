package com.springboot.map.dto;

import lombok.Data;

@Data
public class RequestDto {
    private String query;
    private String category_group_code;
    private String x;
    private String y;
    private Integer redius;
    private String rect;
    private int page;
    private int size;
    private String sort;

    public RequestDto(){
        this.page = 1;
        this.size = 15;
        this.sort = "accuracy";
    }

}
