package com.bazin.dto;

import java.math.BigDecimal;

public class DividendResponseDTO {

  private BigDecimal averageDividend;
  private BigDecimal desiredYieldPercent;
  private BigDecimal priceTeto;

  public DividendResponseDTO() {
  }

  public DividendResponseDTO(BigDecimal averageDividend, BigDecimal desiredYieldPercent, BigDecimal priceTeto) {
    this.averageDividend = averageDividend;
    this.desiredYieldPercent = desiredYieldPercent;
    this.priceTeto = priceTeto;
  }

  public BigDecimal getAverageDividend() {
    return averageDividend;
  }

  public void setAverageDividend(BigDecimal averageDividend) {
    this.averageDividend = averageDividend;
  }

  public BigDecimal getDesiredYieldPercent() {
    return desiredYieldPercent;
  }

  public void setDesiredYieldPercent(BigDecimal desiredYieldPercent) {
    this.desiredYieldPercent = desiredYieldPercent;
  }

  public BigDecimal getPriceTeto() {
    return priceTeto;
  }

  public void setPriceTeto(BigDecimal priceTeto) {
    this.priceTeto = priceTeto;
  }
}