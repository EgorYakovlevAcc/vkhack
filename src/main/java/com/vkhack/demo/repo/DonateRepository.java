package com.vkhack.demo.repo;

import com.vkhack.demo.model.Donate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonateRepository extends JpaRepository<Donate, Long> {
    Donate findDonateById(Long id);
}
