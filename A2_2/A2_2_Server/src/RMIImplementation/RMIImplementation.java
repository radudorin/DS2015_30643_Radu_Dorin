package RMIImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.RMIInterface;
import model.Car;

public class RMIImplementation extends UnicastRemoteObject implements RMIInterface {

	private static final long serialVersionUID = 1L;

	public RMIImplementation() throws RemoteException {
		super();
	}

	@Override
	public double computeTax(Car car) {
		int engineCap = car.getEngineCapacity();
		int sum = 0;
		if(engineCap < 1600) {
			sum = 8;
		} else if(engineCap > 1600 && engineCap <= 2000) {
			sum = 18;
		} else if(engineCap > 2000 && engineCap <= 2600) {
			sum = 72;
		} else if(engineCap > 2600 && engineCap <= 3000) {
			sum = 144;
		} else if(engineCap > 3000){
			sum = 290;
		}
				
		double result = (engineCap / 200) * sum;
		
		System.out.println("sum = " + sum + "    result = " + result + "     engine cap = " + engineCap);
		
		return result;
	}

	@Override
	public double computePrice(Car car) {
		double price = car.getPrice();
		int year = car.getYear();
		
		double result = price - (price/7) * (2015 - year);
		
		System.out.println("price = " + price + "      year = " + year + "      result = " + result);
		
		return result;
	}

	

}
