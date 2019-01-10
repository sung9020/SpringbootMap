package com.springboot.map.repositoy;

import com.springboot.map.entity.ApiInfo;
import com.springboot.map.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface MemberRepository extends JpaRepository<UserInfo, Integer> {
    @Query("SELECT NEW com.springboot.map.entity.UserInfo(id, userid, password, roles) " +
            "FROM UserInfo " +
            "WHERE userid = :userid")
    Stream<UserInfo> findByUserId(@Param("userid") String userid);
}
