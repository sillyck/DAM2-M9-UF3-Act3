import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Act4_Server implements Act4_Server_Interface {
	public static List<Client> taula;

	@Override
	public void anunciarPresencia(String nomClient, int heartbeat) throws RemoteException {
		taula.add(new Client(nomClient, heartbeat));
		System.out.println(
				"Nou client afegit a la taula; es diu " + nomClient + " i te un heartbeat de " + heartbeat + "s");
	}

	@SuppressWarnings({ "StringOperationCanBeSimplified", "ForLoopReplaceableByForEach" })
	@Override
	public void batec(String nomClient) throws RemoteException {
		for (int i = 0; i < taula.size(); i++)
			if (taula.get(i).nom.toString().equals(nomClient)) {
				taula.get(i).estat = true;
				taula.get(i).passadesServidor = 0;
				System.out.println("Batec rebut desde " + nomClient);
			}
	}

	@SuppressWarnings({ "RedundantCast", "InfiniteLoopStatement" })
	public static void main(String[] args) {
		Registry reg = null;
		taula = new ArrayList<>();

		try {
			reg = LocateRegistry.createRegistry(5555);
		} catch (Exception e) {
			System.out.println("ERROR: Registry cannot be created.");
			e.printStackTrace();
		}
		Act4_Server serverObject = new Act4_Server();

		// Cast serverObject with Remote
		try {
			assert reg != null;
			reg.rebind("Act4", (Act4_Server_Interface) UnicastRemoteObject.exportObject(serverObject, 0));
		} catch (Exception e) {
			System.out.println("ERROR: Server object cannot be registered.");
			e.printStackTrace();
		}

		for (;;) {
			long startTime = System.nanoTime();
			long targetTime = startTime + 1_000_000_000L;

			for (;;)
				if (System.nanoTime() >= targetTime) {
					actualitzarTaula();
					break;
				}
		}
	}

	@SuppressWarnings("ForLoopReplaceableByForEach")
	public static synchronized void actualitzarTaula() {
		for (int i = 0; i < taula.size(); i++)
			if (taula.get(i).estat) {
				taula.get(i).passadesServidor++;
				if (taula.get(i).passadesServidor >= (taula.get(i).heartbeat * 2)) {
					taula.get(i).passadesServidor = (taula.get(i).heartbeat * 2);
					taula.get(i).estat = false;
					System.out.println("Fa " + (taula.get(i).heartbeat * 2) + "s que no rebo res desde "
							+ taula.get(i).nom + ". El marco com inactiu.");
				}
			}
	}

}