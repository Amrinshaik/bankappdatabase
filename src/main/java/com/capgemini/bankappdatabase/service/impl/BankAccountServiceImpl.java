package com.capgemini.bankappdatabase.service.impl;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankappdatabase.exceptions.LowBalanceException;
import com.capgemini.bankappdatabase.repository.BankAccountRepository;
import com.capgemini.bankappdatabase.service.BankAccountService;



@Service      //commenting out bean tags in xml file and adding annotations in all 3 implementation classes to get the objects created of these 3 classes using @annotation for injecting dependency 
public class BankAccountServiceImpl implements BankAccountService {
	private BankAccountRepository bankAccountRepository;

	/*
	 * public void setBankAccountRepository(BankAccountRepository
	 * bankAccountRepository) { this.bankAccountRepository = bankAccountRepository;   //commenting out the setter methods in implementation classes
	 * }                                                                              //and properties tag in xml file that is to be used only when we have setter 
	 */                                                                               //methods in use and adding parameterizes constructor with constructor tag in xml file

	@Autowired
	private BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
		super();
		this.bankAccountRepository = bankAccountRepository;
	}

	@Override
	public double getBalance(long accountId) throws AccountNotFoundException {
		return bankAccountRepository.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount) throws LowBalanceException, AccountNotFoundException {
		double balance = bankAccountRepository.getBalance(accountId);
		if (balance != -1) { // checking if bank account exists
			if (balance - amount >= 0) {
				bankAccountRepository.updateBalance(accountId, balance - amount);
				return bankAccountRepository.getBalance(accountId);
			} else
				throw new LowBalanceException("You donot have sufficient balance.");
		}
		return balance; // -1 is returned
	}

	@Override
	public double deposit(long accountId, double amount) throws AccountNotFoundException {
		double balance = bankAccountRepository.getBalance(accountId);
		if (balance != -1) {
			bankAccountRepository.updateBalance(accountId, balance + amount);
			return bankAccountRepository.getBalance(accountId);
		}
		return balance;
	}

	@Override
	public boolean fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException, AccountNotFoundException {
		double balance = withdraw(fromAccount, amount);
		if (balance != -1) {
			if (deposit(toAccount, amount) == -1) {
				deposit(fromAccount, amount);
				return false;
			}
			return true;
		}
		return false;
	}
}
