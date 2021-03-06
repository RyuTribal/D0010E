package CustomerSimulator.controller;

import CustomerSimulator.models.Customer;
import CustomerSimulator.models.Store;

public abstract class Event {
	private Store store;
	private EventQueue evqueue;
	private double timeStamp;
	abstract public void run();
	abstract public double getTime();
	abstract public Customer getCustomer();
	
	public Event(Store s, EventQueue q, double t) {
		this.store = s;
		this.evqueue = q;
		this.timeStamp = t;
	}
}
