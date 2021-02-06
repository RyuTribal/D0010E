package labb5;

import labb5.controller.Event;
import labb5.controller.EventQueue;
import labb5.model.StoreState;

public class Simulator {
	public void run(double timeCloses, int registriesOpen, int maxOccupants, double lambda, double minPick, double maxPick, double minPay, double maxPay, long seed) {
		System.out.println("Starting...");
		StoreState store = new StoreState(timeCloses, registriesOpen, maxOccupants, lambda, seed);
		System.out.println(store.getStoreStatus());
		while(store.getStoreStatus()) {
			Event event = store.getQueue().getFirst();
			event.run();
			store.getQueue().removeFirst();
		}
		
		System.out.print("Ended");
		
	}
}
