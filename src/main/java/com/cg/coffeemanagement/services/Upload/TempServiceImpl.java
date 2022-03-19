package com.cg.coffeemanagement.services.Upload;

import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.dto.TempDTO;
import com.cg.coffeemanagement.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TempServiceImpl implements TempService {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private UploadUtils uploadUtils;

    @Override
    public List<TempDTO> findAll() {
        return null;
    }

    @Override
    public Optional<TempDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public TempDTO getById(Long id) {
        return null;
    }

    @Override
    public TempDTO save(TempDTO tempDTO) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void uploadImage(TempDTO tempDTO) {
        try {
            uploadService.uploadImage(tempDTO.getFile(), uploadUtils.buildImageUploadParams(tempDTO));

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại");
        }
    }

}
