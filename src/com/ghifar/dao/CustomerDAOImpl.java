package com.ghifar.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ghifar.entity.Customer;

//Repository annotations is always applied to DAO implementation
@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomer() {
		
//		get the current hibernate session
		Session currentSession= sessionFactory.getCurrentSession();
		
//		querying
		Query<Customer> theQuery= currentSession.createQuery("from Customer order by lastName", Customer.class);
		
//		Executing the Query
		List<Customer> customers= theQuery.getResultList();
		
//		return Results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		Session currentSession= sessionFactory.getCurrentSession();
		
//		in hibernate there are save to just ONLY save the data, update to just ONLY update the data and saveOrUpdate to do both of the function
//		saveOrUpdate = if (primaryKey/Id) exist then
//						insert new Customer
//						else update exiting customer
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomerById(int theId) {
//		get the current hibernate session
		Session currentSession= sessionFactory.getCurrentSession();
		
//		reterieve/ read from database using the Id
		Customer theCustomer= currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session currentSession= sessionFactory.getCurrentSession();
		
////		cara 1
//		Query theQuery= currentSession.createQuery("delete from customer where id=:customerId");
//		theQuery.setParameter("customerId", theId);
//		theQuery.executeUpdate();
		
//		cara2
		
		Customer theCustomer= currentSession.get(Customer.class, theId);
		currentSession.delete(theCustomer);
		
		
	}
	
	
	
	

}
