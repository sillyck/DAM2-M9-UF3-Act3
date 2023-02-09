package Exercice3;

import java.io.*;
import java.net.*;

public class Ex3_Client
{
	public static void main(String[] args)
	{
		if(args.length!=1)
		{
			System.out.println("Parameters: <file id>");
			System.exit(1);
		}
		try
		{
			String id_file = args[0];
			
			Socket clientSocket = new Socket();
			
			InetSocketAddress addr = new InetSocketAddress("localhost",5555);
			
			clientSocket.connect(addr);
			
			InputStream is = clientSocket.getInputStream();
			
			OutputStream os = clientSocket.getOutputStream();
			
			PrintWriter pw = new PrintWriter(os,true);
			
			System.out.println("Downloading file "+id_file);
			
			pw.println(id_file);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			char buffer[] = new char[1];
			
			while(br.read(buffer)!=-1)
			{
				System.out.print(buffer[0]);;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}