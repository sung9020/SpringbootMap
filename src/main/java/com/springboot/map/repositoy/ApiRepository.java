package com.springboot.map.repositoy;

import com.springboot.map.entity.ApiInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface ApiRepository extends JpaRepository<ApiInfo, Integer> {
    @Query("SELECT NEW com.springboot.map.entity.ApiInfo(id,domain,appkey,authkeyword) " +
            "FROM ApiInfo " +
            "WHERE domain = :domain")
    Stream<ApiInfo> findByDomain(@Param("domain") String domain);
}
