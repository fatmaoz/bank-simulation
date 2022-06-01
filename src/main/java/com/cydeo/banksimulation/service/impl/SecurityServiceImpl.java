package com.cydeo.banksimulation.service.impl;

import com.cydeo.banksimulation.entity.User;
import com.cydeo.banksimulation.entity.common.UserPrincipal;
import com.cydeo.banksimulation.repository.UserRepository;
import com.cydeo.banksimulation.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user==null){
            throw  new UsernameNotFoundException("This user does not exists");
        }

        return new UserPrincipal(user);
    }
}
