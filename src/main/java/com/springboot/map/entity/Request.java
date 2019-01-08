package com.springboot.map.entity;

import lombok.Data;

@Data
public class Request {
    private String query;
    private String category_group_code;
    private String x;
    private String y;
    private Integer redius;
    private String rect;
    private int page;
    private int size;
    private String sort;

    public Request(){
        this.page = 1;
        this.size = 15;
        this.sort = "accuracy";
    }

}
