package com.aggagah.banking.mapper;

import com.aggagah.banking.dto.AccountDto;
import com.aggagah.banking.model.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        return new Account(
          accountDto.getId(),
                accountDto.getName(),
                accountDto.getBalance()
        );
    };

    public static AccountDto mapToDto(Account account){
        return new AccountDto(
                account.getId(),
                account.getName(),
                account.getBalance()
        );
    }
}
