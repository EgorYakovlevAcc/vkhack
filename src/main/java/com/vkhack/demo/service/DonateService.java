package com.vkhack.demo.service;

import com.vkhack.demo.model.Donate;

import java.util.List;

public interface DonateService {
    Donate save(Donate donate);
    void delete(Donate donate);

    Donate findDonateById(Long id);

    List<Donate> findAll();

    void deleteAll();
}
