import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Act4_Client {
	public static int heartbeat;
	public static String nom;

	@SuppressWarnings("InfiniteLoopStatement")
	public static void main(String[] args) {

		heartbeat = 2 + (new Random().nextInt(5) + 1);
		System.out.println("Aquest client ha decidit fer un heartbeat de " + heartbeat + " segons");

		nom = "client_" + generate();
		Act4_Server_Interface serv = null;

		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 5555);
			serv = (Act4_Server_Interface) registry.lookup("Act4");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (serv != null) {
			try {
				serv.anunciarPresencia(nom, heartbeat);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		for (;;) {
			long startTime = System.nanoTime();
			long targetTime = startTime + (heartbeat * 1_000_000_000L);

			for (;;) {
				if (System.nanoTime() >= targetTime) {
					System.out.println("Batec!");
					try {
						assert serv != null;
						serv.batec(nom);
					} catch (RemoteException e) {
						throw new RuntimeException(e);
					}
					break;
				}
			}
		}
	}

	public static String generate() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rand = new Random();
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < 5; i++)
			result.append(alphabet.charAt(rand.nextInt(alphabet.length())));
		return result.toString();
	}
}