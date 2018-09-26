package com.capgemini.bankappdatabase.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.bankappdatabase.dbconnection.DbUtil;
import com.capgemini.bankappdatabase.entities.BankAccount;
import com.capgemini.bankappdatabase.repository.BankAccountRepository;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {
	@Autowired
	DbUtil dbutil;
	
	@Override
	public double getBalance(long accountId) throws AccountNotFoundException {
		String query = "SELECT balance FROM accounts WHERE accountid = ?";
		try (Connection connection = dbutil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setLong(1, accountId);
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) { 
					return result.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new AccountNotFoundException("Account doesn't exist!");
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		String query = "UPDATE accounts SET balance = ? WHERE accountid = ?";
		try (Connection connection = dbutil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setDouble(1, newBalance);
			statement.setLong(2, accountId);
			if (statement.executeUpdate() != 0) {
				System.out.println("Record inserted successfully");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addBankAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BankAccount findBankAccountById(long accointId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankAccount> findAllBankAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount updateBankAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}