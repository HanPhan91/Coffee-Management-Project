package com.cg.coffeemanagement.services.BillDetailService;

import com.cg.coffeemanagement.model.BillDetail;
import com.cg.coffeemanagement.repository.BillDetail.BillDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BillDetailServiceImpl implements BillDetailService {

    @Autowired
    BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> findAll() {
        return null;
    }

    @Override
    public Optional<BillDetail> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public BillDetail getById(Long id) {
        return null;
    }

    @Override
    public BillDetail save(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }

    @Override
    public void remove(Long id) {
    }

}
