//package com.cg.coffeemanagement.services.Role;
//
//import com.cg.coffeemanagement.repository.Roles.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//public class RoleServicesImpl implements IRoleServices{
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public List<Role> findAll() {
//        return roleRepository.findAll();
//    }
//
//    @Override
//    public Optional<Role> findById(Long id) {
//        return roleRepository.findById(id);
//    }
//
//    @Override
//    public Role getById(Long id) {
//        return null;
//    }
//
//    @Override
//    public Role save(Role role) {
//        return null;
//    }
//
//    @Override
//    public void remove(Long id) {
//
//    }
//}
