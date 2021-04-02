/* 
 * create by 2020年1月21日 下午5:16:04
 */
package com.demo2.customer.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo2.support.utils.DateUtils;
import com.demo2.customer.entity.Address;
import com.demo2.customer.entity.Customer;

/**
 * @author fangang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
	@Autowired
	private CustomerService service;
	
	/**
	 * The test show:
	 * how to save, load, delete a simple value object to database.
	 */
	@Test
	public void testSaveAndDelete() {
		Customer customer = new Customer();
		long id = 50010;
		customer.setId(id);
		customer.setName("Johnwood");
		customer.setSex("Male");
		customer.setBirthday(DateUtils.getDate("2013-07-08","yyyy-MM-dd"));
		customer.setIdentification("110211199901013322");
		customer.setPhoneNumber("010-88897070");
		service.save(customer);
		
		Customer actual = service.load(id);
		actual.setExclude(new String[] {"addresses"});
		assertThat("save and load failure! ", actual, equalTo(customer));
		
		service.delete(id);
		Customer deleted = service.load(id);
		assertNull("delete failure! ", deleted);
	}
	
	/**
	 * The test show:
	 * how to save, load, delete a value object with one-to-many relation to database.
	 * that is said:
	 * 1) from customer to address have a one-to-many relation. 
	 * 2) set the relation's isAggregation=true in vObj.xml
	 * 3) save a customer then save his addresses in a same transaction.
	 * 4) load a customer then load his addresses.
	 * 5) delete a customer then save his addresses in a same transaction.
	 */
	@Test
	public void testSaveAndDeleteWithAddress() {
		Customer customer = new Customer();
		long id = 50011;
		customer.setId(id);
		customer.setName("Swaarzi");
		customer.setSex("Male");
		customer.setBirthday(DateUtils.getDate("1995-01-01","yyyy-MM-dd"));
		customer.setIdentification("110211199501013344");
		customer.setPhoneNumber("010-88896666");
		
		List<Address> addresses = new ArrayList<>();
		Address address = new Address();
		address.setId((long)500110);
		address.setCustomerId(id);
		address.setCountryId((long)1000);
		address.setProvinceId((long)370000);
		address.setCityId((long)370100);
		address.setZoneId((long)370102);
		address.setAddress("Happy street No.12");
		address.setPhoneNumber("010-88896666");
		addresses.add(address);

		Address address1 = new Address();
		address1.setId((long)500111);
		address1.setCustomerId(id);
		address.setCountryId((long)1000);
		address.setProvinceId((long)370000);
		address.setCityId((long)370100);
		address.setZoneId((long)370102);
		address1.setAddress("The park of Gushan");
		address1.setPhoneNumber("010-88896666");
		addresses.add(address1);
		customer.setAddresses(addresses);
		service.save(customer);
		
		Customer actual = service.load(id);
		assertThat(actual, equalTo(customer));
		
		service.delete(id);
		Customer deleted = service.load(id);
		assertNull(deleted);
		Address address2 = service.loadAddress(id);
		assertNull(address2);
	}
}
