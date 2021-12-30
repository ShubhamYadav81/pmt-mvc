package com.pmt.mvc.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmt.mvc.dao.AccountRepository;
import com.pmt.mvc.dto.AccountDto;
import com.pmt.mvc.excpetion.AccountNotFoundException;
import com.pmt.mvc.model.Account;

@Service
public class AccountServices {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	ModelMapper mapper;

	public Account getAccount(String accountName){

		Account account = null;
		Optional<Account> optional = accountRepository.getAccountByName(accountName);
		
		/*if (account.getAccountId()==0) {
			 throw new AccountNotFoundException("account not found");
		}*/
		if(optional.isPresent())
		{
			account = optional.get();
		}
		return account;
	}

	public boolean deleteAccountByAccountName(String accountName) {
		int flag = 0;

		boolean result = false;
		flag = accountRepository.deleteAccountByName(accountName);
		if (flag > 0) {
			result = true;
		}
		return result;
	}

	public boolean updatePasswordByAccountName(String accountName, String password) {

		boolean result = false;
		Optional<Account> optional = accountRepository.getAccountByName(accountName);

		if (optional.isPresent()) {
			accountRepository.updatePasswordByUserName(accountName, password);
			result = true;
		}

		return result;
	}

	public AccountDto getAccountRecord(String accountName) {

		Account account = null;
		AccountDto accountDto = new AccountDto();
		Optional<Account> optional = accountRepository.getAccountByName(accountName);
		if (optional.isPresent()) {
			account = optional.get();
			accountDto.setAccountId(account.getAccountId());
			accountDto.setAccountName(account.getAccountName());
			accountDto.setAccountPassword(account.getAccountPassword());
			accountDto.setAccountUrl(account.getAccountUrl());
		}
		return accountDto;
	}

}
