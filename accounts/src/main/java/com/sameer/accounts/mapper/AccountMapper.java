package com.sameer.accounts.mapper;

import com.sameer.accounts.dto.AccountsDto;
import com.sameer.accounts.entity.Accounts;

public class AccountMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts,AccountsDto accountsDto){
        return AccountsDto.builder()
                .accountNumber(accounts.getAccountNumber())
                .accountType(accounts.getAccountType())
                .branchAddress(accounts.getBranchAddress())
                .build();

    }

    public static Accounts mapTAccounts(AccountsDto accountsDto, Accounts accounts){
        return Accounts.builder()
                .accountNumber(accountsDto.getAccountNumber())
                .accountType(accountsDto.getAccountType())
                .branchAddress(accountsDto.getBranchAddress())
                .build();
    }
}
