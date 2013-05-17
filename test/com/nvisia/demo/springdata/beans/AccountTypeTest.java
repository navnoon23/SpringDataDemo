package com.nvisia.demo.springdata.beans;

import junit.framework.Assert;

import org.junit.Test;

public class AccountTypeTest {
	
	@Test
	public void test() {
		AccountType at1 = AccountType.valueOf("BASIC");
		Assert.assertNotNull(at1);
		Assert.assertEquals(AccountType.BASIC, at1);
		
		AccountType at2 = AccountType.getAccountType("BAS");
		Assert.assertNotNull(at2);
		Assert.assertEquals(AccountType.BASIC, at2);
	}

}
