package com.spring.libraryMngSys.service;

import com.spring.libraryMngSys.model.MyUser;
import com.spring.libraryMngSys.repository.CacheRepository;
import com.spring.libraryMngSys.repository.MyUserRepository;
import com.spring.libraryMngSys.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Value("${users.student.authority}")
    String studentAuthority;

    @Value("${users.admin.authority}")
    String adminAuthority;

    @Autowired
    CacheRepository cacheRepository;

    @Autowired
    MyUserRepository myUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    public MyUser createUser(MyUser myUser) {
        return myUserRepository.save(myUser);
    }

    public MyUser createUser(UserCreateRequest userCreateRequest) {
        MyUser.MyUserBuilder myUserBuilder = MyUser.builder()
                .username(userCreateRequest.getUsername())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()));

        if(userCreateRequest.getStudent()!=null){
            myUserBuilder.authority(studentAuthority);
        }else{
            myUserBuilder.authority(adminAuthority);
        }

        return myUserRepository.save(myUserBuilder.build());
    }
}
