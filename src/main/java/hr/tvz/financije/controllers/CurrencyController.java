package hr.tvz.financije.controllers;

import hr.tvz.financije.services.CurrencyService;
import hr.tvz.financije.services.models.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9000")
public class CurrencyController {

    private final CurrencyService service;

    @GetMapping
    public ResponseEntity<List<CurrencyDto>> getCurrencies() {
        return ResponseEntity.ok(service.getCurrencies());
    }
}
