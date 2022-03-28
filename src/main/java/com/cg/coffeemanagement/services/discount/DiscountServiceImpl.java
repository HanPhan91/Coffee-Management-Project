package com.cg.coffeemanagement.services.discount;

import com.cg.coffeemanagement.model.Discount;
import com.cg.coffeemanagement.repository.discount.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiscountServiceImpl implements IDiscountService{

    @Autowired
    private DiscountRepository discountRepository;;
    @Override
    public List<Discount> findAll() {
        return discountRepository.findAll();
    }

    @Override
    public Optional<Discount> findById(Long id) {
        return discountRepository.findById(id);
    }

    @Override
    public Discount getById(Long id) {
        return discountRepository.getById(id);
    }

    @Override
    public Discount save(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<Discount> findByDeletedFalse() {
        return discountRepository.findByDeletedFalse();
    }

    @Override
    public List<Discount> findByDeletedTrue() {
        return discountRepository.findByDeletedTrue();
    }

    @Override
    public void deleteDiscount(Long id) {
        discountRepository.deleteDiscount(id);
    }

    @Override
    public void restoreDiscount(Long id) {
        discountRepository.restoreDiscount(id);
    }
}
