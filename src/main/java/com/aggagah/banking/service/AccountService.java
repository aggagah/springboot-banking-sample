package com.aggagah.banking.service;

import com.aggagah.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount (AccountDto accountDto);

    AccountDto detailAccount (Long id);

    List<AccountDto> getAllAccount ();

    AccountDto depositBalance (Long id, double balance);

    AccountDto withdrawBalance (Long id, double balance);

}
