package com.cg.coffeemanagement.services.Staffs;

import com.cg.coffeemanagement.model.Staff;
import com.cg.coffeemanagement.services.IGeneralServices;

import java.util.List;


public interface IStaffServices extends IGeneralServices<Staff>{

    public List<Staff> findByDeletedFalse();

}
