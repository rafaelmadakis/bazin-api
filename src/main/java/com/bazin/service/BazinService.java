package com.bazin.service;

import com.bazin.dto.DividendRequestDTO;
import com.bazin.dto.DividendResponseDTO;
import com.bazin.exception.InvalidInputException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BazinService {

  public DividendResponseDTO calculate(DividendRequestDTO req) {
    List<BigDecimal> divs = req.getDividends();
    if (divs == null || divs.size() != 5) {
      throw new InvalidInputException("É necessário exatamente 5 valores de dividendos (últimos 5 anos)");
    }

    // calculando média
    BigDecimal sum = BigDecimal.ZERO;
    for (BigDecimal d : divs) {
      if (d == null || d.compareTo(BigDecimal.ZERO) < 0) {
        throw new InvalidInputException("Dividendos devem ser valores não-negativos");
      }
      sum = sum.add(d);
    }
    BigDecimal average = sum.divide(new BigDecimal(divs.size()), 10, RoundingMode.HALF_UP);

    BigDecimal yieldPercent = req.getDesiredYieldPercent() == null ? new BigDecimal("6") : req.getDesiredYieldPercent();
    if (yieldPercent.compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidInputException("yield desejado deve ser maior que 0");
    }

    // preço teto = média / (yieldPercent / 100)
    BigDecimal divisor = yieldPercent.divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP);
    BigDecimal priceTeto = average.divide(divisor, 2, RoundingMode.HALF_UP);

    return new DividendResponseDTO(average.setScale(2, RoundingMode.HALF_UP), yieldPercent.setScale(2, RoundingMode.HALF_UP), priceTeto);
  }
}