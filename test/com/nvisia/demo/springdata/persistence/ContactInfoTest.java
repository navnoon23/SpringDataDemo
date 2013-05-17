package com.nvisia.demo.springdata.persistence;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nvisia.demo.springdata.beans.ContactInfo;
import com.nvisia.demo.springdata.beans.ContactType;
import com.nvisia.demo.springdata.beans.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ContactInfoTest {

	@Autowired 
	private CustomerRepository customerRepository;

	@Test
	public void testAddContactInfo() {
		Long id = 2L;
		Customer cust = customerRepository.findOne(id);
		Assert.assertEquals(id, cust.getId());
		
		ContactInfo ci = cust.getContactInfoByType(ContactType.EMAIL);
		if( ci == null ) {
			ci = createContactInfo(cust);
			cust.addContactInfo(ci);
		} else {
			String s = ci.getDetails();
			if( s.endsWith("@GMAIL.COM")) {
				s = s.substring(0,s.indexOf('@'))+"@YAHOO.COM";
				ci.setDetails(s);
			} else if ( s.endsWith("@YAHOO.COM") ) {
				s = s.substring(0,s.indexOf('@'))+"@GMAIL.COM";
				ci.setDetails(s);
			}
		}
		customerRepository.save(cust);
		
		Customer saved = customerRepository.findOne(id);
		Assert.assertEquals(id, saved.getId());
		Set<ContactInfo> cinfos = saved.getContactInfos();
		Assert.assertNotNull(cinfos);
		Assert.assertFalse(cinfos.isEmpty());
		ContactInfo savedInfo = cinfos.iterator().next();
		Assert.assertEquals(ci.getCustomerId(), savedInfo.getCustomerId());
		Assert.assertEquals(ci.getContactType(), savedInfo.getContactType());
		Assert.assertEquals(ci.getDetails(), savedInfo.getDetails());
	}
	
	private ContactInfo createContactInfo(Customer cust) {
		ContactInfo ci = new ContactInfo();
		ci.setContactType(ContactType.EMAIL);
		ci.setCustomerId(cust.getId());
		ci.setDetails(cust.getFirstName()+cust.getLastName()+"@GMAIL.COM");
		return ci;
		
	}
}
