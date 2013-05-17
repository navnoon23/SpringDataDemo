package com.nvisia.demo.springdata.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nvisia.demo.springdata.beans.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CustomerRepositoryTest {
	
	@Autowired 
	private CustomerRepository customerRepository;

	@Test
	public void testFindByLastName() {
		List<Customer> list = customerRepository.findByLastName("SMITH");
		Assert.assertNotNull(list);
		Assert.assertEquals(2,list.size());
	}

	@Test
	public void testFindById() {
		Customer customer = customerRepository.findOne(1L);
		Assert.assertNotNull(customer);
		Assert.assertEquals(1l, customer.getId().longValue());
		Assert.assertEquals("JOHN", customer.getFirstName());
		Assert.assertEquals("DOE", customer.getLastName());
		Assert.assertEquals("M", customer.getGender());
	}
	
	//Test
	public void testCreateCustomer() {
		Customer customer = createCustomer("JOE", "DEAN", "M", createDate(1980,2,1));
		customerRepository.save(customer);
		Customer saved = customerRepository.findOne(customer.getId());
		Assert.assertEquals(customer.getId(),saved.getId());
		Assert.assertEquals(customer.getFirstName(),saved.getFirstName());
		Assert.assertEquals(customer.getLastName(),saved.getLastName());
		Assert.assertEquals(customer.getGender(),saved.getGender());
		Assert.assertEquals(customer.getBirthDate(),saved.getBirthDate());
	}
	
	@Test
	public void testCRUDCustomer() {
		// create
		Customer customer = createCustomer("JACK", "BLACK", "M", createDate(1975, 1, 1));
		customerRepository.save(customer);
		
		//read
		List<Customer> list = customerRepository.findByLastName("BLACK");
		Assert.assertNotNull(list);
		Assert.assertEquals(1,list.size());
		
		Customer saved = list.get(0);
		Assert.assertEquals(customer.getFirstName(), saved.getFirstName());
		Assert.assertEquals(customer.getLastName(), saved.getLastName());
		Assert.assertEquals(customer.getGender(), saved.getGender());
		Assert.assertEquals(customer.getBirthDate(), saved.getBirthDate());
		
		// update
		Date d = createDate(2013, 04, 01);
		saved.setDeathDate(d);
		customerRepository.save(saved);
		list = customerRepository.findByLastName("BLACK");
		Assert.assertNotNull(list);
		Assert.assertEquals(1,list.size());
		Customer saved2 = list.get(0);
		Assert.assertEquals(d, saved2.getDeathDate());
		
		// delete
		customerRepository.delete(saved);
		list = customerRepository.findByLastName("BLACK");
		Assert.assertNotNull(list);
		Assert.assertEquals(0,list.size());
	}
	
	@Test
	public void findByLastAndFirst() {
		String first = "JOHN";
		String last = "DOE";
		List<Customer> list = customerRepository.findByLastNameAndFirstName(last, first);
		Assert.assertNotNull(list);
		Assert.assertFalse(list.isEmpty());
		for( Customer cust : list ) {
			Assert.assertEquals(last, cust.getLastName());
			Assert.assertEquals(first, cust.getFirstName());
		}
	}
	
	@Test
	public void findByFirstOrLast() {
		String name = "SMITH";
		List<Customer> list = customerRepository.findByFirstOrLast(name);
		Assert.assertNotNull(list);
		Assert.assertFalse(list.isEmpty());
		for( Customer c : list ) {
			Assert.assertTrue(c.getFirstName().equals(name) || c.getLastName().equals(name));
		}
	}

	private Customer createCustomer(String first, String last, String gender, Date dob) {
		Customer customer = new Customer();
		customer.setFirstName(first);
		customer.setLastName(last);
		customer.setGender(gender);
		customer.setBirthDate(dob);
		return customer;
	}

	private Date createDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, day);
		return cal.getTime();
	}
}
