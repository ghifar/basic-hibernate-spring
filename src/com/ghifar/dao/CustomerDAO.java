package com.ghifar.dao;

import java.util.List;

import com.ghifar.entity.Customer;

public interface CustomerDAO {
	public List<Customer> getCustomer();
	public void saveCustomer(Customer theCustomer);
	public Customer getCustomerById(int theId);
	public void deleteCustomer(int theId);
	

}
