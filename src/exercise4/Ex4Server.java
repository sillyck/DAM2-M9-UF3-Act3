package exercise4;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

// Do not forget the implements Ex4Inteface clause, if you do you can have Remote type error in method exportObject of UnicastRemoteObject
public class Ex4Server implements Ex4Interface
{
	@Override
	public void publishMessage(String message) throws RemoteException
	{
		System.out.println(message);
	}
	
	public static void main(String[] args)
	{
		Registry reg = null;
		
		try
		{
			reg = LocateRegistry.createRegistry(5555);
		}
		catch(Exception e)
		{
			System.out.println("ERROR: Registry cannot be created.");
			e.printStackTrace();
		}
		Ex4Server serverObject=new Ex4Server();
		
		// Cast serverObject with Remote
		try
		{
			reg.rebind("Ex4",(Ex4Interface) UnicastRemoteObject.exportObject(serverObject, 0));
		}
		catch(Exception e)
		{
			System.out.println("ERROR: Server object cannot be registered.");
			e.printStackTrace();
		}
	}
}