package RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import RMIImplementation.RMIImplementation;
import constants.Constants;

public class RMIServer {

	public static final void main(String args[]) throws RemoteException, AlreadyBoundException {
		RMIImplementation impl = new RMIImplementation();
		Registry registry = LocateRegistry.createRegistry(Constants.RMI_PORT);
		registry.bind(Constants.RMI_ID, impl);
		System.out.println("Server started");
	}
	
}
