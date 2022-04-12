package com.cg.coffeemanagement.services.Staffs;

import com.cg.coffeemanagement.model.Staff;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IStaffServices extends IGeneralServices<Staff> {

    List<Staff> findByDeletedFalse();

    List<Staff> findByDeletedTrue();

    void deleteStaff(@Param("id") Long id);

    void restoreStaff(@Param("id") Long id);

    List<Staff> findStaffNotUser();

    void staffHasUser(@Param("id") Long id);

    List<Staff> findStaffNotDeletedAndPermissionSmaller(@Param("permission") int permission);

    List<Staff> findStaffNotDeletedAndPermissionEqualAndNotUser(@Param("permission") int permission);
}
