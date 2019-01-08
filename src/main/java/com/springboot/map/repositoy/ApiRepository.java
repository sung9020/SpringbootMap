package com.springboot.map.repositoy;

import com.springboot.map.entity.ApiInfo;
import org.springframework.data.repository.CrudRepository;

public interface ApiRepository extends CrudRepository<ApiInfo, Integer> {
    ApiInfo findByDomain(String domain);
}
