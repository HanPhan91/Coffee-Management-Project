package com.cg.coffeemanagement.services.Users;

import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.UserDto;
import com.cg.coffeemanagement.repository.Users.UserRepository;
import com.cg.coffeemanagement.services.Upload.UploadService;
import com.cg.coffeemanagement.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServicesImpl implements IUserServices{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private UploadUtils uploadUtils;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {

    }
}
