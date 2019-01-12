package com.springboot.map.service;

import com.springboot.map.dto.UserInfoDto;
import com.springboot.map.repositoy.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import org.springframework.transaction.annotation.Transactional;


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
