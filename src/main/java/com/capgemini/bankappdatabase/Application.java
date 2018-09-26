package com.capgemini.bankappdatabase;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.capgemini.bankappdatabase.config.AppConfig;

import com.capgemini.bankappdatabase.controller.BankAccountController;
import com.capgemini.bankappdatabase.exceptions.LowBalanceException;


public class Application {
	public static void main(String[] args) throws LowBalanceException, AccountNotFoundException {
		/*ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");*/  //activating container(which is a class) by creating object of its implementation class
	                                         //it will read all the configuration done in applicationcontext.xml file when it finds classpath       
		
		
		
		
		ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);  /*used when we create java class to omit the xml usage*/

	 BankAccountController bankAccountController=context.getBean("bankAccountController",BankAccountController.class);
	 //pass bean id and class of the controller created in the applicationcontext.xml file
	 //now using this object we can call any method
	 System.out.println(bankAccountController.getBalance(1234));
	 System.out.println(bankAccountController.withdraw(1234,4000));
	 System.out.println(bankAccountController.deposit(1234,10000));
	 System.out.println(bankAccountController.fundTransfer(1234,1235,5000));
	 System.out.println(bankAccountController.getBalance(1234));
	 System.out.println(bankAccountController.getBalance(1235));

}
}
