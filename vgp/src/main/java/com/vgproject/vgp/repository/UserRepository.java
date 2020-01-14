package com.vgproject.vgp.repository;

import com.vgproject.vgp.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * author LucasDonizeti
 */
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);

}
