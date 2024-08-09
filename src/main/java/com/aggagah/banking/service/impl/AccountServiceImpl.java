package com.aggagah.banking.service.impl;

import com.aggagah.banking.dto.AccountDto;
import com.aggagah.banking.mapper.AccountMapper;
import com.aggagah.banking.model.Account;
import com.aggagah.banking.repository.AccountRepository;
import com.aggagah.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account saved = accountRepository.save(account);

        return AccountMapper.mapToDto(saved);
    }

    @Override
    public AccountDto detailAccount(Long id) {
        Account findAccount = accountRepository.findById(id).orElse(null);

        if(findAccount == null){
            return null;
        }

        return AccountMapper.mapToDto(findAccount);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> data = accountRepository.findAll();
        if (data.isEmpty()){
            return null;
        }

        List<AccountDto> dataDto = new ArrayList<>();
        for (var akun: data){
            dataDto.add(AccountMapper.mapToDto(akun));
        }
        
        return dataDto;
    }

    @Override
    public AccountDto depositBalance(Long id, double balance) {
        // cari akun based on id
        Account findById = accountRepository.findById(id).orElse(null);

        if(findById == null){
            return null;
        }

        double totalBalance = findById.getBalance() + balance;
        findById.setBalance(totalBalance);
        Account updatedAccount = accountRepository.save(findById);


        return AccountMapper.mapToDto(updatedAccount);
    }

    @Override
    public AccountDto withdrawBalance(Long id, double balance) {
        Account findAccount = accountRepository.findById(id).orElse(null);

        if(findAccount == null){
            return null;
        }

        double currBalance = findAccount.getBalance() - balance;
        if(currBalance < 0){
            throw new IllegalArgumentException("Insufficinet balance for withdrawal");
        }

        findAccount.setBalance(currBalance);
        Account updatedAccount = accountRepository.save(findAccount);

        return AccountMapper.mapToDto(updatedAccount);
    }

}
