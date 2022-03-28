package com.cg.coffeemanagement.repository.Staffs;

import com.cg.coffeemanagement.model.Staff;
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


    @Modifying
    @Query("UPDATE Staff s SET s.deleted = true WHERE s.id = :id")
    void deleteStaff(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Staff s SET s.deleted = false WHERE s.id = :id")
    void restoreStaff(@Param("id") Long id);
}
