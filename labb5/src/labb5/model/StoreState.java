package labb5.model;

import java.util.ArrayList;

import labb5.controller.CustomerEnter;
import labb5.controller.EventQueue;
import labb5.controller.StoreClose;
import labb5.random.ExponentialRandomStream;
import labb5.random.UniformRandomStream;

public class StoreState extends State {
	private double timeCloses;
	private double currentTime = 0;
	private int registriesOpen;
	private int maxOccupants;
	private ExponentialRandomStream arrivalTimes;
	private UniformRandomStream pickTimes;
	private UniformRandomStream payTimes;
	
	private ArrayList<Customer> customers;
	
	private EventQueue queue;
	public StoreState(double timeCloses, int registriesOpen, int maxOccupants, double lambda, long seed) {
		this.timeCloses = timeCloses;
		this.registriesOpen = registriesOpen;
		this.maxOccupants = maxOccupants;
		this.queue = new EventQueue();
		this.queue.addEvent(new StoreClose(this, this.queue, 0));
		this.customers = new ArrayList<Customer>();
		this.arrivalTimes = new ExponentialRandomStream(lambda, seed);
		this.customers = new ArrayList<Customer>();
		
	}
	
	public void closeStore() {
		stopSim();
	}
	
	public boolean getStoreStatus() {
		return getStatus();
	}
	
	public EventQueue getQueue() {
		return this.queue;
	}
	
	
}
