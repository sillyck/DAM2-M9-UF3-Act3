package exercise4;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Ex4Client
{
	public static void main(String[] args)
	{
		if(args.length==0)
		{
			System.out.println("arguments: <message>");
			System.exit(1);
		}
		
		String message = args[0];
		Ex4Interface serv = null;
		
		try
		{
			Registry registry = LocateRegistry.getRegistry("localhost",5555);
			serv = (Ex4Interface)registry.lookup("Ex4");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(serv!=null)
		{
			try
			{
				serv.publishMessage(message);
			}
			catch(RemoteException e)
			{
				e.printStackTrace();
			}
		}
	}
}