package com.vgproject.vgp.controller;

import com.vgproject.vgp.model.User;
import com.vgproject.vgp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

/**
 * author LucasDonizeti
 */
@RestController
@RequestMapping("user")
public class UserEndPoint {
    private final UserRepository userDAO;

    @Autowired
    public UserEndPoint(UserRepository userDAO) {
        this.userDAO = userDAO;
    }

    //get usuario (paginação suportada)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUsers(Pageable pageable) {
        Page<User> users = (Page<User>) userDAO.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //adicionar usuario
    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return new ResponseEntity<>(userDAO.save(user), HttpStatus.OK);
    }

    //admin deleta usuario pelo id
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (userDAO.existsById(id)) {
            userDAO.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //usuario logado se atualiza
    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDAO.existsById(user.getId())) {
            if (user.getId() == userDAO.findByUsername(userDetails.getUsername()).getId()) {
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                return new ResponseEntity<>(userDAO.save(user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //atualiza pelo admin
    @PutMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminUpdateUser(@RequestBody User user) {
        if (userDAO.existsById(user.getId())) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return new ResponseEntity<>(userDAO.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
