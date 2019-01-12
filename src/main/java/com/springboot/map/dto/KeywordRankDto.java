package com.springboot.map.dto;

import com.springboot.map.entity.KeywordRank;
import lombok.Data;

@Data
public class KeywordRankDto {

    int id;


    String keyword;

    int totalcount;

    public KeywordRank getEntity(){
        return KeywordRank.builder()
                .keyword(keyword)
                .totalcount(totalcount).build();
    }

    public KeywordRank getEntity(int id, String keyword, int totalcount){
        KeywordRank entity = new KeywordRank(id,keyword,totalcount);
        return entity;
    }

    public KeywordRankDto(){

    }
    public KeywordRankDto(KeywordRank entity){
        this.id = entity.getId();
        this.keyword = entity.getKeyword();
        this.totalcount = entity.getTotalcount();
    }
}
