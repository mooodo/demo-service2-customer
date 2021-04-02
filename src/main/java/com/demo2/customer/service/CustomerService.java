/* 
 * Created by 2019年1月13日
 */
package com.demo2.customer.service;

import java.util.List;

import com.demo2.customer.entity.Address;
import com.demo2.customer.entity.Customer;

/**
 * The service of customer
 * @author fangang
 */
public interface CustomerService {
	/**
	 * save a customer
	 * @param customer
	 */
	public void save(Customer customer);
	/**
	 * delete a customer
	 * @param id
	 */
	public void delete(long id);
	/**
	 * @param id
	 * @return the customer of the id.
	 */
	public Customer load(long id);
	/**
	 * @param ids the list of ids
	 * @return the list of customers
	 */
	public List<Customer> loadMore(List<Long> ids);
	/**
	 * @param id
	 * @return the address of the id
	 */
	public Address loadAddress(long id);
	/**
	 * @param ids the list of ids of the addresses
	 * @return the list of the addresses
	 */
	public List<Address> loadAddresses(List<Long> ids);
}
