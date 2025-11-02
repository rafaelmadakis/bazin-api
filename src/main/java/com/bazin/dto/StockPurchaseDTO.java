package com.bazin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class StockPurchaseDTO {

  @NotBlank(message = "O ticker não pode estar vazio.")
  @Size(max = 10, message = "O ticker deve ter no máximo 10 caracteres.")
  private String ticker;

  @NotNull(message = "A data e hora da compra não podem ser nulas.")
  private LocalDateTime purchaseDateTime;

  @NotNull(message = "O preço não pode ser nulo.")
  private BigDecimal price;

  @NotNull(message = "A quantidade não pode ser nula.")
  private Integer quantity;

  // Getters e Setters
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
    StockPurchaseDTO that = (StockPurchaseDTO) o;
    return Objects.equals(ticker, that.ticker) && Objects.equals(purchaseDateTime, that.purchaseDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ticker, purchaseDateTime);
  }
}