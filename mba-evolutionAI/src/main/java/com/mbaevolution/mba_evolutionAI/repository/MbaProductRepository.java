package com.mbaevolution.mba_evolutionAI.repository;

import com.mbaevolution.mba_evolutionAI.entity.MbaProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbaProductRepository extends JpaRepository<MbaProduct, Long> {
}