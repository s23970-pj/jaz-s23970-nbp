package com.example.jazs23970nbp.controller;

import com.example.jazs23970nbp.model.Currency;
import com.example.jazs23970nbp.Service.NbpService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="Kontroler walutowy", description="endpointy do zarządzania walutą")
public class NbpController {
    private final NbpService nbpService;

    public NbpController(NbpService nbpService) {
        this.nbpService = nbpService;
    }
    @Operation(summary = "Średni kurs walutowy", description = "Użytkownik dostarcza nazwe waluty wg.standard ISO 4217 format dat: YYYY-MM-DD")
    @GetMapping("/currency/{code}/{startDate}/{endDate}")
    public ResponseEntity<Currency> getCurrency(@ApiParam(value = "Currency code", example = "GBP") @PathVariable("code") String code,
                                                @ApiParam(value = "Start date of range", example = "2023-06-01") @PathVariable("startDate") String startDate,
                                                @ApiParam(value = "End date of range", example = "2023-06-12")@PathVariable("endDate") String endDate
    ){
        return ResponseEntity.ok(nbpService.getCurrency(code,startDate,endDate));
    }
}
