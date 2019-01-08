package com.springboot.map.repositoy;

import com.springboot.map.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<UserInfo, Integer> {
    UserInfo findByUserId(String userId);
}
