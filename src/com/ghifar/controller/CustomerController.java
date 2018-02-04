package com.ghifar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ghifar.dao.CustomerDAO;
import com.ghifar.entity.Customer;
import com.ghifar.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
//	need to inject cusomter service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomer(Model theModel) {
//		Get the customers from the DAO
		List<Customer> theCustomers =customerService.getCustomer();
		
//		Add the customers to the model
		theModel.addAttribute("customers",theCustomers);
//							  (name "customers" untuk dipake di view	, Value where it comes from above this line code)
		
		return "list-customers";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
//		create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
//		save the customer
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
		
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
//		get the customer from the service then to the DAO then from database
		Customer theCustomer= customerService.getCustomerById(theId);

		//set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int theId, Model theModel) {
		
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
}
