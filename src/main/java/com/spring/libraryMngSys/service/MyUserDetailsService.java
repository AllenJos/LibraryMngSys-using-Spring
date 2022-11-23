package com.spring.libraryMngSys.service;

import com.spring.libraryMngSys.model.MyUser;
import com.spring.libraryMngSys.repository.CacheRepository;
import com.spring.libraryMngSys.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    CacheRepository cacheRepository;

    @Autowired
    MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = cacheRepository.get(username);
        if(myUser==null){
            myUser = myUserRepository.findByUsername(username);
            if(myUser!=null)
                cacheRepository.set(myUser);
        }

        return myUser;
    }
}
