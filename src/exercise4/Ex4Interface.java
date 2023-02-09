package exercise4;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Ex4Interface extends Remote
{
	void publishMessage(String message) throws RemoteException;
}