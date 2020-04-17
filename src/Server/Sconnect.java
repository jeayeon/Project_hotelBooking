package Server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Sconnect extends Thread  {
	private Socket WithClient = null;
	private Socket WithClient2 =null;
	private SCenter sc = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;

	public Sconnect(Socket withClient, SCenter sc, Socket withClient2) {
		this.WithClient = withClient;
		this.WithClient2 = withClient2;
		this.sc = sc;
	}

	@Override
	public void run() {
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
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
				while (true) {
					try {
						reMsg = WithClient.getInputStream();
						byte[] Msg = new byte[200];
						reMsg.read(Msg);
						String msg = new String(Msg).trim();
						sc.receive(msg, WithClient,WithClient2);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
//			}
//		}).start();

	}
	
	public void SerializableSend(ArrayList<Object> rList, Socket Client2) {
		 byte[] serializedMember;
		try {
			ByteArrayOutputStream baos =new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(rList);
			serializedMember = baos.toByteArray();
			sendMsg = Client2.getOutputStream();
			sendMsg.write(serializedMember);
			
			System.out.println("serializedMember는 무엇인가? : "+ serializedMember);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
