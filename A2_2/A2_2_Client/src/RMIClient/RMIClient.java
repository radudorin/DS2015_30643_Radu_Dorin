package RMIClient;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import constants.Constants;
import interfaces.RMIInterface;
import model.Car;

public class RMIClient {

	public static String computePrice(Car car) throws AccessException, RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("localhost", Constants.RMI_PORT);
		RMIInterface rmi = (RMIInterface) registry.lookup(Constants.RMI_ID);

		return String.valueOf(rmi.computePrice(car));
	}

	public static String computeTax(Car car) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("localhost", Constants.RMI_PORT);
		RMIInterface rmi = (RMIInterface) registry.lookup(Constants.RMI_ID);

		return String.valueOf(rmi.computeTax(car));
	}
}
