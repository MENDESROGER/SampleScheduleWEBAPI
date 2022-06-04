package com.evaluation.schedule.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaluation.schedule.security.jwt.model.UserModel;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    public Optional<UserModel> findByLogin(String login);

}
