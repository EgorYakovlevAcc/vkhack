package com.vkhack.demo;

import java.util.List;

public interface DonateService {
    Donate save(Donate donate);
    void delete(Donate donate);

    Donate findDonateById(Long id);

    List<Donate> findAll();

    void deleteAll();
}
