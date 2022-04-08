package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import model.Customer;

public class CustomerService {
	static CustomerService  customerService = new CustomerService();

	private static List<Customer> customers;
	
	private CustomerService() {
		customers = new ArrayList();
	}
	
	public static CustomerService getInstance() {
		return customerService;
	}
	
	public void addCustomer(String email,String firstName,String lastName) {
		customers.add(new Customer(firstName,lastName,email));
	}
	public Customer getCustomer(String customerEmail) {
		
		for(Customer customer:CustomerService.customers) {
			if(customer.getEmail().equalsIgnoreCase(customerEmail)) {
				return customer;
			}
		}
		return null;
	}
	
	public Collection<Customer> getAllCustomers(){
		return CustomerService.customers;
	}
	
	// provide a static reference
}
