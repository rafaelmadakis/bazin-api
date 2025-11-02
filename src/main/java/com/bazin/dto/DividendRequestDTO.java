package com.bazin.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class DividendRequestDTO {

  @NotNull(message = "lista de dividendos não pode ser nula")
  @Size(min = 5, max = 5, message = "forneça exatamente 5 valores (últimos 5 anos)")
  private List<BigDecimal> dividends;

  // yield desejado em porcentagem, ex: 6 = 6%
  private BigDecimal desiredYieldPercent = new BigDecimal("6");

  public List<BigDecimal> getDividends() {
    return dividends;
  }

  public void setDividends(List<BigDecimal> dividends) {
    this.dividends = dividends;
  }

  public BigDecimal getDesiredYieldPercent() {
    return desiredYieldPercent;
  }

  public void setDesiredYieldPercent(BigDecimal desiredYieldPercent) {
    this.desiredYieldPercent = desiredYieldPercent;
  }
}
