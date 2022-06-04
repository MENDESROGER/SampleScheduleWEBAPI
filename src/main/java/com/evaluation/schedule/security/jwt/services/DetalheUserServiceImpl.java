package com.evaluation.schedule.security.jwt.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.evaluation.schedule.security.jwt.data.DetailUserDate;
import com.evaluation.schedule.security.jwt.model.UserModel;
import com.evaluation.schedule.security.jwt.repository.UserRepository;

import java.util.Optional;

@Component
public class DetalheUserServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public DetalheUserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = repository.findByLogin(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User [" + username + "] not found");
        }

        return new DetailUserDate(user);
    }

}
