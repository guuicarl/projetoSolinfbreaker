package com.acoes.solinfbreaker.repository;

import com.acoes.solinfbreaker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository  extends JpaRepository<User, Long> {
}
