package swinRestaurant;

import swinRestaurant.Waiter.WaiterClient;

public class MainClass extends Thread {
	public static void main(String[] args) throws Exception {
		MainClass waiterThread = new MainClass();

		MainClass cookThread = new MainClass();

		MainClass receptionThread = new MainClass();

		// Test t = new Test();

		waiterThread.setName("waiterThread");
		waiterThread.start();

		cookThread.setName("cookThread");
		cookThread.start();

		receptionThread.setName("receptionThread");
		receptionThread.start();

	}

	@Override
	public void run() {
		if (Thread.currentThread().getName().equalsIgnoreCase("waiterThread")) {
			try {
				Waiter wait1 = new Waiter();
				wait1.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (Thread.currentThread().getName().equalsIgnoreCase("cookThread")) {
			try {
				Cook cook = new Cook();
				cook.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (Thread.currentThread().getName().equalsIgnoreCase("receptionThread")) {
			try {
				Reception reception = new Reception();
				reception.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
