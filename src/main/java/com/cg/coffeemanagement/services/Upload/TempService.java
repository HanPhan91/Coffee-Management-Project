package com.cg.coffeemanagement.services.Upload;

import com.cg.coffeemanagement.model.dto.TempDTO;
import com.cg.coffeemanagement.services.IGeneralServices;


public interface TempService extends IGeneralServices<TempDTO> {

    void uploadImage(TempDTO tempDTO);
}
