package com.cg.coffeemanagement.services.Users;

import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.Avatar;
import com.cg.coffeemanagement.model.Staff;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.UserDto;
import com.cg.coffeemanagement.repository.Staffs.StaffRepository;
import com.cg.coffeemanagement.repository.Users.AvataRepository;
import com.cg.coffeemanagement.repository.Users.UserRepository;
import com.cg.coffeemanagement.services.Upload.UploadService;
import com.cg.coffeemanagement.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {


    @Autowired
    private UserRepository userRepository;

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
    public User create(UserDto userDto) {
        Staff staff = staffRepository.findById(userDto.getId_staff()).get();
        User user = userDto.toUser();
        user.setStaff(staff);

        if (userDto.getFile() == null) {
            user.setAvatar(null);
            userRepository.save(user);
            return user;
        }

        Avatar avatar = avataRepository.save(userDto.toAvatar());
        user.setAvatar(avatar);
        userRepository.save(user);
        uploadAndSaveAvatar(userDto, user, avatar);
        return user;
    }

    @Override
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
}
