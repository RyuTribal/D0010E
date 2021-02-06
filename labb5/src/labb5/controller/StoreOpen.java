package labb5.controller;

import labb5.random.ExponentialRandomStream;

public class StoreOpen {
	private double timePeopleEntered;
	private double timeCloses;
	private ExponentialRandomStream arriveTimes;
	public StoreOpen(double timeCloses, ExponentialRandomStream arriveTimes) {
		this.timeCloses = timeCloses;
		this.arriveTimes = arriveTimes;
	}
	public void run() {
		while (timeCloses > timePeopleEntered) {
			timePeopleEntered += arriveTimes.next();
		}
	}
}
