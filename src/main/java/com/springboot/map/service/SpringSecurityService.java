package com.springboot.map.service;

import com.springboot.map.repositoy.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.map.entity.UserInfo;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringSecurityService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserInfo userInfo = memberRepository.findByUserId(userId);
        return new User(userInfo.getUserId(), userInfo.getPassword(), userInfo.getAuthrity() );
    }


}
