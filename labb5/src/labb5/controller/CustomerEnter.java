package labb5.controller;

import labb5.model.Customer;
import labb5.model.State;

public class CustomerEnter extends Event {
	Customer customer;
	public CustomerEnter(EventQueue eventQueue, double time) {
		super(eventQueue);
		customer = (Customer) state;
		
	}

	@Override
	public void run() {
		System.out.print("Customer "+customer.getId()+" entered");
		
	}

	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return "Arrived";
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}

}
