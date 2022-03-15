package com.cg.coffeemanagement.repository.Staffs;

import com.cg.coffeemanagement.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    public List<Staff> findByDeletedFalse();
}
