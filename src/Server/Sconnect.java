package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Sconnect extends Thread {
	private Socket WithClient = null;
	private SCenter sc = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;

	public Sconnect(Socket withClient, SCenter sc) {
		this.WithClient = withClient;
		this.sc = sc;
	}

	@Override
	public void run() {
		//Send();
		Receive();
	}

	public Socket getWithClient() {
		return WithClient;
	}

	public void Send(String msg, Socket Client) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					sendMsg = Client.getOutputStream();
					byte[] Msg = msg.getBytes();
					sendMsg.write(Msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}

	private void Receive() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						reMsg = WithClient.getInputStream();
						byte[] Msg = new byte[200];
						reMsg.read(Msg);
						String msg = new String(Msg).trim();
						sc.receive(msg, WithClient);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

}
