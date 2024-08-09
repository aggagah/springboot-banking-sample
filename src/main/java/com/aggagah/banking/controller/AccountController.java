package com.aggagah.banking.controller;

import com.aggagah.banking.dto.AccountDto;
import com.aggagah.banking.dto.ErrorResponse;
import com.aggagah.banking.dto.SuccessResponse;
import com.aggagah.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<SuccessResponse<AccountDto>> createAccount(@RequestBody AccountDto accountDto){
        SuccessResponse<AccountDto> response = new SuccessResponse<>("success", HttpStatus.CREATED.value(), "data created", accountService.createAccount(accountDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDetailAccount(@PathVariable Long id){
        AccountDto accDetail = accountService.detailAccount(id);

        if(accDetail == null){
            ErrorResponse errorResponse = new ErrorResponse("error", HttpStatus.NOT_FOUND.value(), "data not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        SuccessResponse<AccountDto> response = new SuccessResponse<>("success", HttpStatus.OK.value(), "data found", accDetail);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Object> getAllAccount(){
        List<AccountDto> data = accountService.getAllAccount();
        if(data == null){
            ErrorResponse errorResponse = new ErrorResponse("error", HttpStatus.NOT_FOUND.value(), "data not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        SuccessResponse<List<AccountDto>> response = new SuccessResponse<>("success", HttpStatus.OK.value(), "data found", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<Object> updateBalance(@PathVariable Long id, @RequestBody double balance){
        AccountDto data = accountService.depositBalance(id, balance);
        if(data == null){
            ErrorResponse errorResponse = new ErrorResponse("error", HttpStatus.NOT_FOUND.value(), "user not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        SuccessResponse<AccountDto> response = new SuccessResponse<>("success", HttpStatus.OK.value(), "success deposit balance", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<Object> withdrawBalance(@PathVariable Long id, @RequestBody double balance){
        try {
            AccountDto data = accountService.withdrawBalance(id, balance);
            if (data == null){
                ErrorResponse errorResponse = new ErrorResponse("error", HttpStatus.NOT_FOUND.value(), "user not found");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            SuccessResponse<AccountDto> response = new SuccessResponse<>("success", HttpStatus.OK.value(), "success withdraw : "+balance, data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(IllegalArgumentException e){
            ErrorResponse errorResponse = new ErrorResponse("error", HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
