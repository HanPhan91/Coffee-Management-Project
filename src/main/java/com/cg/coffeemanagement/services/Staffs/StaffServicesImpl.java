package com.cg.coffeemanagement.services.Staffs;

import com.cg.coffeemanagement.model.Staff;
import com.cg.coffeemanagement.repository.Staffs.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServicesImpl implements IStaffServices {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public Staff getById(Long id) {
        return null;
    }

    @Override
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<Staff> findByDeletedFalse() {
        return staffRepository.findByDeletedFalse(Sort.by(Sort.Direction.DESC,"createAt"));
    }

    @Override
    public List<Staff> findByDeletedTrue() {
        return staffRepository.findByDeletedTrue(Sort.by(Sort.Direction.DESC,"createAt"));
    }


    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteStaff(id);

    }

    @Override
    public void restoreStaff(Long id) {
        staffRepository.restoreStaff(id);
    }

    @Override
    public List<Staff> findByDeletedTrue() {
        return staffRepository.findByDeletedTrue();
    }

    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteStaff(id);

    }

    @Override
    public void restoreStaff(Long id) {
        staffRepository.restoreStaff(id);
    }
}
