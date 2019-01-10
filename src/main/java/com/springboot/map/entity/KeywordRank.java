package com.springboot.map.entity;

import lombok.Builder;
import lombok.Data;
import net.bytebuddy.asm.Advice;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Date;

@Entity
@Table(name = "KEYWORDRANK")
@Data
public class KeywordRank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column
    String keyword;

    @Column
    int totalcount;

    @Builder
    public KeywordRank(String keyword, int totalcount){
        this.keyword = keyword;
        this.totalcount = totalcount;
    }

    public KeywordRank(){

    }

    public KeywordRank(int id, String keyword, int totalcount){
        this.id = id;
        this.keyword = keyword;
        this.totalcount = totalcount;
    }

}
