package com.nvisia.demo.springdata.persistence;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nvisia.demo.springdata.beans.Account;
import com.nvisia.demo.springdata.beans.AccountStatus;
import com.nvisia.demo.springdata.beans.AccountType;
import com.nvisia.demo.springdata.beans.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AccountRepositoryTest {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Test
	public void findAccountById() {
		Long id = 1L;
		Account saved = accountRepository.findOne(id);
		Assert.assertNotNull(saved);
		Assert.assertEquals(id, saved.getCustomer().getId());
		Assert.assertEquals(AccountStatus.PENDING, saved.getAccountStatus());
		Assert.assertEquals(AccountType.BASIC, saved.getAccountType());
	}
	
	@Test
	public void findAccountByCustomer() {
		Long custId = 1L;
		Customer cust = customerRepository.findOne(custId);
		Assert.assertNotNull(cust);
		
		List<Account> accounts = accountRepository.findByCustomer(cust);
		Assert.assertTrue(accounts.size() > 0);
		
		Account saved = accounts.get(0);
		Assert.assertEquals(cust.getId(), saved.getCustomer().getId());
		Assert.assertEquals(AccountStatus.PENDING, saved.getAccountStatus());
		Assert.assertEquals(AccountType.BASIC, saved.getAccountType());
	}
	
	@Test
	public void findAccountByCustomerId() {
		Long custId = 1L;
		List<Account> accounts = accountRepository.findByCustomerId(custId);
		Assert.assertTrue(accounts.size() > 0);
		
		Account saved = accounts.get(0);
		Assert.assertEquals(custId, saved.getCustomer().getId());
		Assert.assertEquals(AccountStatus.PENDING, saved.getAccountStatus());
		Assert.assertEquals(AccountType.BASIC, saved.getAccountType());
	}
	
	@Test
	public void createAndFindAccount() {
		Long custId = 3L;
		// delete all 
		accountRepository.deleteAllForCustomer(custId);

		// find cust
		Customer cust = customerRepository.findOne(custId);

		// create acct
		Account acct = createAccount(cust, AccountStatus.ACTIVE, AccountType.PREMIUM);
		accountRepository.save(acct);
		Assert.assertNotNull(acct.getId());
		
		// verify
		Account saved = accountRepository.findOne(acct.getId());
		Assert.assertEquals(acct.getAccountStatus(), saved.getAccountStatus());
		Assert.assertEquals(acct.getAccountType(), saved.getAccountType());
		Assert.assertEquals(acct.getCustomer().getId(), saved.getCustomer().getId());
		
		// delete all
		accountRepository.deleteAllForCustomer(custId);
		
		// verify no accts for cust
		List<Account> accounts = accountRepository.findByCustomerId(custId);
		Assert.assertEquals(0, accounts.size());
	}
	
	@Test
	public void updateAccount() {
		// retrieve
		Long id = 1L;
		Account saved = accountRepository.findOne(id);
		Assert.assertNotNull(saved);
		Assert.assertEquals(AccountStatus.PENDING, saved.getAccountStatus());
		Assert.assertEquals(AccountType.BASIC, saved.getAccountType());
		
		//update status to active
		saved.setAccountStatus(AccountStatus.ACTIVE);
		accountRepository.save(saved);
		
		//verify updated status
		Account updated = accountRepository.findOne(id);
		Assert.assertEquals(AccountStatus.ACTIVE, saved.getAccountStatus());

		// update status back to pending
		updated.setAccountStatus(AccountStatus.PENDING);
		accountRepository.save(updated);
}

	private Account createAccount(Customer cust, AccountStatus stat, AccountType type) {
		Account acct = new Account();
		acct.setAccountStatus(stat);
		acct.setAccountType(type);
		acct.setCreated(new Date());
		acct.setCustomer(cust);
		return acct;
	}
}
