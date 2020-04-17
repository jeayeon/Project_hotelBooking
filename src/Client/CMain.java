package Client;

import java.net.Socket;

public class CMain {

	public static void main(String[] args) throws Exception {

		Socket WithServer = new Socket("10.0.0.115",7890);
		Socket WithServer2 = new Socket("10.0.0.115",4565);
		new Cconnect(WithServer,WithServer2);
	}

}

