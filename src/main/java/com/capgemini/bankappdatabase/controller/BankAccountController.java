package com.capgemini.bankappdatabase.controller;



import javax.security.auth.login.AccountNotFoundException;

import com.capgemini.bankappdatabase.exceptions.LowBalanceException;
import com.capgemini.bankappdatabase.service.BankAccountService;


public class BankAccountController {
	private BankAccountService bankAccountService;


	private BankAccountController(BankAccountService bankAccountService) {
		super();
		this.bankAccountService = bankAccountService;
	}

	public double getBalance(long accountId) throws AccountNotFoundException {
		return bankAccountService.getBalance(accountId);
	}

	public double withdraw(long accountId, double amount) throws LowBalanceException, AccountNotFoundException {
		return bankAccountService.withdraw(accountId, amount);
	}

	public double deposit(long accountId, double amount) throws AccountNotFoundException {
		return bankAccountService.deposit(accountId, amount);
	}

	public boolean fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException, AccountNotFoundException {
		return bankAccountService.fundTransfer(fromAccount, toAccount, amount);
	}
}
