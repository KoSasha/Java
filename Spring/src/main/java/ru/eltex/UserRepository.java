package ru.eltex;

import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface UserRepository extends CrudRepository<User, String> {
    List<User> findByFio(String fio);
}
