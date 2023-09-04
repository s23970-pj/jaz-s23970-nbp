package com.example.jazs23970nbp.controller;

import com.example.jazs23970nbp.model.Currency;
import com.example.jazs23970nbp.Service.NbpService;


import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="Kontroler walutowy", description="endpointy do zarządzania walutą")
public class NbpController {
    private final NbpService nbpService;

    public NbpController(NbpService nbpService) {
        this.nbpService = nbpService;
    }

    @Operation(summary = "Średni kurs walutowy", description = "Użytkownik dostarcza nazwe waluty wg.standard ISO 4217 format dat: YYYY-MM-DD")
    @GetMapping("/currency/{code}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode= "400", description = "Invalid query posted to NBP API"),
            @ApiResponse(responseCode = "404", description = "NBP API haven't found request"),
            @ApiResponse(responseCode = "500", description = "Internal Error on the server side")
    })
    public ResponseEntity<Currency> getCurrency(@Parameter(description = "Currency code", example = "GBP") @PathVariable("code") String code,
                                                @Parameter(description = "From what date", example = "2023-06-01") @RequestParam("startDate") String startDate,
                                                @Parameter(description = "Till what date", example = "2023-06-12")@RequestParam("endDate") String endDate
    ){
        System.out.println(code+startDate+endDate);
        return ResponseEntity.ok(nbpService.getCurrency(code,startDate,endDate));
    }
}
