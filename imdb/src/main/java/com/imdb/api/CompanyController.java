package com.imdb.api;

import com.imdb.domain.*;
import com.imdb.domain.dto.ImdbResponseCode;
import com.imdb.domain.dto.ImdbResponseEntity;
import com.imdb.domain.entities.Company;
import com.imdb.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCompany(@RequestBody CompanyCreateRequestDto companyCreateRequestDto) {
        companyService.createCompay(companyCreateRequestDto);
        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.CREATED, null);

    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateCompany(@RequestBody CompanyUpdateRequestDto companyUpdateRequestDto) {
        companyService.updateCompany(companyUpdateRequestDto);
        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.OK, null);

    }


    @GetMapping("/companies")
    public ResponseEntity<Object> companies() {

        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.OK, companyService.allCompany());

    }

    @PostMapping("/company-movies")
    public ResponseEntity<Object> companyMovies(@RequestBody CompanyMoviesRequestDto companyMoviesRequestDto) {
        
        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.OK, companyService.companyMovies(companyMoviesRequestDto));

    }

}
