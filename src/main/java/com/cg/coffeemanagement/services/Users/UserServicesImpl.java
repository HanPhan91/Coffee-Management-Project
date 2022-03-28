package com.cg.coffeemanagement.services.Users;

import com.cg.coffeemanagement.exception.DataInputException;
<<<<<<< HEAD
import com.cg.coffeemanagement.model.*;
import com.cg.coffeemanagement.model.dto.AvatarDto;
=======
import com.cg.coffeemanagement.model.Avatar;
import com.cg.coffeemanagement.model.Staff;
import com.cg.coffeemanagement.model.User;
>>>>>>> han
import com.cg.coffeemanagement.model.dto.UserDto;
import com.cg.coffeemanagement.repository.Staffs.StaffRepository;
import com.cg.coffeemanagement.repository.Users.AvataRepository;
import com.cg.coffeemanagement.repository.Users.UserRepository;
import com.cg.coffeemanagement.services.Upload.UploadService;
import com.cg.coffeemanagement.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
=======
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
>>>>>>> han

import javax.transaction.Transactional;
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
<<<<<<< HEAD
    private PasswordEncoder passwordEncoder;

    @Autowired
=======
>>>>>>> han
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
<<<<<<< HEAD
        user.setPassword(passwordEncoder.encode(user.getPassword()));
=======
>>>>>>> han
        return userRepository.save(user);
    }

    @Override
<<<<<<< HEAD
    public void saveAvatar(Long id,  Avatar avatar) {
        userRepository.saveAvatar(id, avatar);
    }

    @Override
=======
>>>>>>> han
    public void remove(Long id) {

    }

    @Override
    public List<User> findByDeletedFalse() {
<<<<<<< HEAD
        return userRepository.findByDeletedFalse(Sort.by(Sort.Direction.DESC, "createAt"));
=======
        return userRepository.findByDeletedFalse();
>>>>>>> han
    }

    @Override
    public List<User> findByDeletedTrue() {
<<<<<<< HEAD
        return userRepository.findByDeletedTrue(Sort.by(Sort.Direction.DESC, "createAt"));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
=======
        return userRepository.findByDeletedTrue();
>>>>>>> han
    }

    @Override
    public User create(UserDto userDto) {
        Staff staff = staffRepository.findById(userDto.getId_staff()).get();
        User user = userDto.toUser();
        user.setStaff(staff);

        if (userDto.getFile() == null) {
            user.setAvatar(null);
<<<<<<< HEAD
            save(user);
=======
            userRepository.save(user);
>>>>>>> han
            return user;
        }

        Avatar avatar = avataRepository.save(userDto.toAvatar());
        user.setAvatar(avatar);
<<<<<<< HEAD
        save(user);
=======
        userRepository.save(user);
>>>>>>> han
        uploadAndSaveAvatar(userDto, user, avatar);
        return user;
    }

    @Override
<<<<<<< HEAD
    public User edit(User user, UserDto userDto) {
        Avatar avatar = avataRepository.save(userDto.toAvatar());
        user.setAvatar(avatar);
        saveAvatar(user.getId(), avatar);
        uploadAndSaveAvatar(userDto, user, avatar);
        return user;
=======
    public User edit(User user ,UserDto userDto) {
        if (userDto.getFile() == null) {
            user.setAvatar(null);
            user.setPassword(user.getPassword());
            userRepository.save(user);
            return user;
        }
        else {
            Avatar avatar = avataRepository.save(userDto.toAvatar());
            user.setAvatar(avatar);
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
            uploadAndSaveAvatar(userDto, user, avatar);
            return user;
        }
>>>>>>> han
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
<<<<<<< HEAD

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

    @Override
    public void changePass(Long id, String password){
        String pass = passwordEncoder.encode(password);
        userRepository.changePass(id, pass);
    }
=======
>>>>>>> han
}
