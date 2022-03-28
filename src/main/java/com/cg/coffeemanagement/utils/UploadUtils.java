package com.cg.coffeemanagement.utils;

import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.dto.TempDTO;
import com.cg.coffeemanagement.model.dto.UserDto;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UploadUtils {
    public static final String IMAGE_UPLOAD_FOLDER = "avatar";
    public static final String VIDEO_UPLOAD_FOLDER = "product_videos";

    public Map buildImageUploadParams(UserDto userDto) {
        if (userDto == null || userDto.getId() == null)
            throw new DataInputException("Không thể upload hình ảnh của sản phẩm chưa được lưu");

        String publicId = String.format("%s/%s", IMAGE_UPLOAD_FOLDER, userDto.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

//    public Map buildImageDestroyParams(Product product, String publicId) {
//        if (product == null || product.getId() == null)
//            throw new DataInputException("Không thể destroy hình ảnh của sản phẩm không xác định");
//
//        return ObjectUtils.asMap(
//                "public_id", publicId,
//                "overwrite", true,
//                "resource_type", "image"
//        );
//    }

}
