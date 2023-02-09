import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Act4_Server_Interface extends Remote {
	void anunciarPresencia(String nomClient, int heartbeat) throws RemoteException;

	void batec(String nomClient) throws RemoteException;
}