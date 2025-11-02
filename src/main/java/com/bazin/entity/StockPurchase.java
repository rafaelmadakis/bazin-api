package com.bazin.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class StockPurchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String ticker;

  @Column(nullable = false)
  private LocalDateTime purchaseDateTime;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false)
  private Integer quantity;

  // Getters e Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public LocalDateTime getPurchaseDateTime() {
    return purchaseDateTime;
  }

  public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
    this.purchaseDateTime = purchaseDateTime;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StockPurchase that = (StockPurchase) o;
    return Objects.equals(id, that.id) && Objects.equals(ticker, that.ticker) && Objects.equals(purchaseDateTime,
        that.purchaseDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ticker, purchaseDateTime);
  }
}
