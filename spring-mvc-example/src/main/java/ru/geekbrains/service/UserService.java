package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.entity.User;
import ru.geekbrains.persist.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    public Page<User> filterByAge(Integer minAge, Integer maxAge, Pageable pageable) {
        if (minAge == null && maxAge == null) {
            return repository.findAll(pageable);
        }
        if (minAge != null && maxAge == null) {
            return repository.findByAgeGreaterThanEqual(minAge, pageable);
        }
        if (minAge == null) {
            return repository.findByAgeLessThanEqual(maxAge, pageable);
        }
        return repository.findByAgeBetween(minAge, maxAge, pageable);
    }

    @Transactional
    public void save(User user) {
        repository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }
}
