package com.cg.coffeemanagement.services.Users;

import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.exception.EmailExistsException;
import com.cg.coffeemanagement.model.*;
import com.cg.coffeemanagement.model.dto.UserDto;
import com.cg.coffeemanagement.repository.Staffs.StaffRepository;
import com.cg.coffeemanagement.repository.Users.AvataRepository;
import com.cg.coffeemanagement.repository.Users.UserRepository;
import com.cg.coffeemanagement.services.Upload.UploadService;
import com.cg.coffeemanagement.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserServicesImpl implements IUserServices {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AvataRepository avataRepository;

    @Autowired
    private StaffRepository staffRepository;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<User> findByDeletedFalse() {
        return userRepository.findByDeletedFalse();
    }

    @Override
    public List<User> findByDeletedTrue() {
        return userRepository.findByDeletedTrue();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User create(UserDto userDto) {
        Staff staff = staffRepository.findById(userDto.getId_staff()).get();
        User user = userDto.toUser();
        user.setStaff(staff);

        if (userDto.getFile() == null) {
            user.setAvatar(null);
            save(user);
            return user;
        }

        Avatar avatar = avataRepository.save(userDto.toAvatar());
        user.setAvatar(avatar);
        save(user);
        uploadAndSaveAvatar(userDto, user, avatar);
        return user;
    }

    @Override
    public User edit(User user, UserDto userDto) {
        if (userDto.getFile() == null) {
            user.setAvatar(null);
            if (userDto.getPassword().equals("null")) {
                return user;
            }
            user.setPassword(userDto.getPassword());
            save(user);
            return user;
        } else {
            Avatar avatar = avataRepository.save(userDto.toAvatar());
            user.setAvatar(avatar);
            if (userDto.getPassword().isEmpty()) {
                save(user);
                uploadAndSaveAvatar(userDto, user, avatar);
                return user;
            }
            user.setPassword(userDto.getPassword());
            save(user);
            uploadAndSaveAvatar(userDto, user, avatar);
            return user;
        }
    }

    public void uploadAndSaveAvatar(UserDto userDto, User user, Avatar avatar) {
        try {
            Map uploadResult = uploadService.uploadImage(userDto.getFile(), uploadUtils.buildImageUploadParams(userDto));
            String fileUrl = (String) uploadResult.get("secure_url");
            avatar.setFileUrl(fileUrl);
            avatar.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
            avatar.setCloudId(avatar.getFileFolder() + "/" + avatar.getId());
            user.setAvatar(avatar);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload thất bại");
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public void restoreUser(Long id) {
        userRepository.restoreUser(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public Optional<User> findUserDTOByUsername(String username) {
        return userRepository.findUserDTOByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.getByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }

}
