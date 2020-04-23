package Client;

import java.net.Socket;

public class CMain {

	public static void main(String[] args) throws Exception {

		Socket WithServer = new Socket("10.0.0.115",784);
		new Cconnect(WithServer);
	}

}

