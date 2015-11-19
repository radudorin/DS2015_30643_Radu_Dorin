package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Car;

public interface RMIInterface extends Remote {

	double computeTax(Car car) throws RemoteException;

	double computePrice(Car car) throws RemoteException;

}
