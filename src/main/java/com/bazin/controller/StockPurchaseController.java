package com.bazin.controller;

import com.bazin.dto.StockPurchaseDTO;
import com.bazin.entity.StockPurchase;
import com.bazin.service.StockPurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock-purchases")
public class StockPurchaseController {

  private final StockPurchaseService stockPurchaseService;

  public StockPurchaseController(StockPurchaseService stockPurchaseService) {
    this.stockPurchaseService = stockPurchaseService;
  }

  @PostMapping
  public ResponseEntity<StockPurchase> saveStockPurchase(@Valid @RequestBody StockPurchaseDTO stockPurchaseDTO) {
    StockPurchase savedStockPurchase = stockPurchaseService.saveStockPurchase(stockPurchaseDTO);
    return new ResponseEntity<>(savedStockPurchase, HttpStatus.CREATED);
  }
}