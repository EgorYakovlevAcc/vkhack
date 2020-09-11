package com.vkhack.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DonateServiceImpl implements DonateService {
    @Autowired
    private DonateRepository donateRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Donate save(Donate donate) {
        return donateRepository.save(donate);
    }

    @Override
    public void delete(Donate donate) {
        donateRepository.delete(donate);
    }

    @Override
    public Donate findDonateById(Long id) {
        return donateRepository.findDonateById(id);
    }

    @Override
    public List<Donate> findAll() {
        return donateRepository.findAll();
    }

    @Override
    public void deleteAll() {
        donateRepository.deleteAll();
    }
}
