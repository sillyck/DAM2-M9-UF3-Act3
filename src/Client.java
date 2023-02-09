public class Client {
	public String nom;
	public int heartbeat;
	public int passadesServidor;
	public boolean estat;

	public Client() {

	}

	public Client(String nom, int heartbeat) {
		this.nom = nom;
		this.heartbeat = heartbeat;
		passadesServidor = 0;
		estat = true;
	}

	public Client(String nom, int heartbeat, int passadesServidor, boolean estat) {
		this.nom = nom;
		this.heartbeat = heartbeat;
		this.passadesServidor = passadesServidor;
		this.estat = estat;
	}
}