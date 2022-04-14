package com.cg.coffeemanagement.repository.Staffs;

import com.cg.coffeemanagement.model.Staff;
import com.cg.coffeemanagement.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {


    List<Staff> findByDeletedFalse(Sort createAt);

    List<Staff> findByDeletedTrue(Sort createAt);

    List<Staff> findByDeletedFalse();

    List<Staff> findByDeletedTrue();

    List<Staff> findByDeletedFalseAndHasUserFalse();

    @Modifying
    @Query("UPDATE Staff s SET s.hasUser = true WHERE s.id=:id")
    void staffHasUser(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Staff s SET s.deleted = true WHERE s.id = :id")
    void deleteStaff(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Staff s SET s.deleted = false WHERE s.id = :id")
    void restoreStaff(@Param("id") Long id);

    @Query("SELECT s FROM Staff s WHERE s.deleted = false AND s.position.permission.permissionAccess > :permission")
    List<Staff> findStaffNotDeletedAndPermissionSmaller(@Param("permission") int permission);

    @Query("SELECT s FROM Staff s WHERE s.deleted = false AND s.hasUser= false AND s.position.permission.permissionAccess >= :permission")
    List<Staff> findStaffNotDeletedAndPermissionEqualAndNotUser(@Param("permission") int permission);

    @Query("SELECT count(s.id) FROM Staff s WHERE s.deleted = false")
    Integer countStaff();
}
