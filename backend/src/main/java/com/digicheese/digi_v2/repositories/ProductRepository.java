package com.digicheese.digi_v2.repositories;

import com.digicheese.digi_v2.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}