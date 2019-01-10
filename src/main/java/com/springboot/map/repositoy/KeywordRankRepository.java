package com.springboot.map.repositoy;


import com.springboot.map.entity.KeywordRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;


public interface KeywordRankRepository extends JpaRepository<KeywordRank, Integer> {
    Stream<KeywordRank> findTop10ByOrderByTotalcountDesc();

    @Query("SELECT NEW com.springboot.map.entity.KeywordRank(id, keyword, totalcount ) " +
            "FROM KeywordRank " +
            "WHERE keyword = :keyword")
    Stream<KeywordRank> findByKeyword(@Param("keyword") String keyword);
}
