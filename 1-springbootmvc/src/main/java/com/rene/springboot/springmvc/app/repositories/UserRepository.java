package com.rene.springboot.springmvc.app.repositories;

import com.rene.springboot.springmvc.app.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
