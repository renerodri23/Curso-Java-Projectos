package com.rene.springboot.springmvc.app.services;

import com.rene.springboot.springmvc.app.entities.User;
import com.rene.springboot.springmvc.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserService{


    private final UserRepository repo;

    public UserServicesImpl(@Autowired UserRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) this.repo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);

    }
}
