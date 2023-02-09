package Exercice3;

import java.io.*;
import java.net.*;

public class Ex3_Server
{
	public static void clientProcessing(Socket socket)
	{
		try
		{
			InputStream is = socket.getInputStream();
			OutputStream os=socket.getOutputStream();
			BufferedReader br_socket=new BufferedReader(new InputStreamReader(is));
			String id_file=br_socket.readLine();
			
			BufferedReader br_file=new BufferedReader(new FileReader(id_file+".txt"));
			
			while(br_file.ready()) {
				
				char b=(char) br_file.read();
				
				os.write(b);
				
			}
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void main(String[] args) {
		
		try {
			
			ServerSocket serverSocket = new ServerSocket();
			
			InetSocketAddress addr=new InetSocketAddress("localhost",5555);
			
			serverSocket.bind(addr);
			
			while(true) {
				
				Socket newSocket=serverSocket.accept();
				
				clientProcessing(newSocket);
				
				newSocket.close();
				
			}
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}