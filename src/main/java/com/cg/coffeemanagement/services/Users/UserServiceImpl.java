package com.cg.coffeemanagement.services.Users;

import com.cg.coffeemanagement.exception.DataInputException;

import com.cg.coffeemanagement.model.*;
import com.cg.coffeemanagement.model.Avatar;
import com.cg.coffeemanagement.model.Staff;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.AvatarDto;
import com.cg.coffeemanagement.model.dto.UserDto;
import com.cg.coffeemanagement.repository.Staffs.StaffRepository;
import com.cg.coffeemanagement.repository.Users.AvataRepository;
import com.cg.coffeemanagement.repository.Users.UserRepository;
import com.cg.coffeemanagement.services.Upload.UploadService;
import com.cg.coffeemanagement.utils.UploadUtils;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    public static final String IMAGE_UPLOAD_FOLDER = "avatar";

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
    public void saveAvatar(Long id,  Long idavatar) {
        userRepository.saveAvatar(id, idavatar);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public List<User> findByDeletedFalse() {
        return userRepository.findByDeletedFalse(Sort.by(Sort.Direction.DESC, "createAt"));
    }

    @Override
    public List<User> findByDeletedTrue() {
        return userRepository.findByDeletedTrue(Sort.by(Sort.Direction.DESC, "createAt"));
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
            userRepository.save(user);
            return user;
        }

        Avatar avatar = avataRepository.save(userDto.toAvatar());
        user.setAvatar(avatar);
        save(user);
        changePass(user.getId(), user.getPassword());
        uploadAndSaveAvatar(userDto, user, avatar);
        return user;
    }

    @Override
    public User changeAvatar(User user, AvatarDto avatarDto) {
       Avatar avatar = avataRepository.save(avatarDto.toAvatar());
       user.setAvatar(avatar);
       save(user);
        try {
            String publicId = String.format("%s/%s", IMAGE_UPLOAD_FOLDER, avatar.getId());
            Map uploadResult = uploadService.uploadImage(avatarDto.getFile(), ObjectUtils.asMap(
                    "public_id", publicId,
                    "overwrite", true,
                    "resource_type", "image"
            ));
            String fileUrl = (String) uploadResult.get("secure_url");
            avatar.setFileUrl(fileUrl);
            avatar.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
            avatar.setCloudId(avatar.getFileFolder() + "/" + avatar.getId());
            user.setAvatar(avatar);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload thất bại");
        }
        return user;
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

    @Override
    public void changePass(Long id, String password){
        String pass = passwordEncoder.encode(password);
        userRepository.changePass(id, pass);
    }
}
