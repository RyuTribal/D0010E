package CustomerSimulator.controller;

import CustomerSimulator.models.Customer;

import CustomerSimulator.models.Store;

public class Pay extends Event {
	private double timeStarted;
    private Store store;
    private Customer customer;
    private double newTime;

    public Pay(Store s, EventQueue q, double t, Customer c, double nt) {
    	super(s, q, t);
        this.store = s;
        this.customer = c;
        this.timeStarted = t;
        this.newTime = nt;
    }
    
    public void run() {
        this.store.decreasePeopleInStore();
        this.store.setEventName("Pay");
        this.store.increasMoney();
        store.setCurrentTime(this.timeStarted);
        this.store.removeFromQueue(customer.getID(), this.newTime);
        this.store.setFreeTime();
        store.update();
    }
    
	public double getTime() {
		return this.timeStarted;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
}