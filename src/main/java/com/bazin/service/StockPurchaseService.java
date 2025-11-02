package com.bazin.service;

import com.bazin.dto.StockPurchaseDTO;
import com.bazin.entity.StockPurchase;
import com.bazin.repository.StockPurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class StockPurchaseService {

  private final StockPurchaseRepository stockPurchaseRepository;

  public StockPurchaseService(StockPurchaseRepository stockPurchaseRepository) {
    this.stockPurchaseRepository = stockPurchaseRepository;
  }

  public StockPurchase saveStockPurchase(StockPurchaseDTO stockPurchaseDTO) {
    StockPurchase stockPurchase = new StockPurchase();
    stockPurchase.setTicker(stockPurchaseDTO.getTicker());
    stockPurchase.setPurchaseDateTime(stockPurchaseDTO.getPurchaseDateTime());
    stockPurchase.setPrice(stockPurchaseDTO.getPrice());
    stockPurchase.setQuantity(stockPurchaseDTO.getQuantity());
    return stockPurchaseRepository.save(stockPurchase);
  }
}