package com.bazin.repository;

import com.bazin.entity.StockPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPurchaseRepository extends JpaRepository<StockPurchase, Long> {
}
