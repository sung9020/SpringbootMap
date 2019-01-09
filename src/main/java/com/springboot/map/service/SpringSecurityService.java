package com.springboot.map.service;

import com.springboot.map.dto.UserInfoDto;
import com.springboot.map.repositoy.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.map.entity.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.rmi.server.UnicastServerRef;

import javax.annotation.PostConstruct;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class SpringSecurityService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @PostConstruct
    public void init(){
        UserInfoDto user = new UserInfoDto();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setId(0);
        user.setUserid("123msn");
        user.setPassword(bCryptPasswordEncoder.encode("1234"));
        user.setRoles("ADMIN");
        saveUserInfo(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfoDto userInfoDto = memberRepository.findByUserId(username).findFirst().map(UserInfoDto::new).get();

        return new User(userInfoDto.getUserid(), userInfoDto.getPassword(), AuthorityUtils.createAuthorityList(userInfoDto.getRoles()) );
    }

    @Transactional
    public int saveUserInfo(UserInfoDto user){
        return memberRepository.save(user.getEntity()).getId();
    }

}
