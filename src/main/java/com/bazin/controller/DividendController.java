package com.bazin.controller;

import com.bazin.dto.DividendRequestDTO;
import com.bazin.dto.DividendResponseDTO;
import com.bazin.service.BazinService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/bazin")
public class DividendController {

  private final BazinService bazinService;

  public DividendController(BazinService bazinService) {
    this.bazinService = bazinService;
  }

  @PostMapping("/price-teto")
  public ResponseEntity<DividendResponseDTO> calculate(@RequestBody @Valid DividendRequestDTO request) {
    DividendResponseDTO resp = bazinService.calculate(request);
    return ResponseEntity.ok(resp);
  }
}
