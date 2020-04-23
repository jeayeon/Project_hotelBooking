package Server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Sconnect extends Thread  {
	private Socket WithClient = null;
	private Socket WithClient2 =null;
	private ServerSocket server2=null;
	private SCenter sc = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private int randomP;

	public Sconnect(Socket withClient, SCenter sc, int randomP, ServerSocket server2) {
		this.WithClient = withClient;
		this.randomP = randomP;
		this.server2 = server2;
		this.sc = sc;
	}

	@Override
	public void run() {
		init(WithClient,randomP); //객체소켓을보내기위한
		Receive();
	}

	private void init(Socket withClient, int randomP) {
		try {
//			System.out.println("서버커넥트에서: "+msg);
			sendMsg = withClient.getOutputStream();
			byte[] Msg = (""+randomP).trim().getBytes();
			sendMsg.write(Msg);
			
			WithClient2=server2.accept();
			System.out.println("2번째 소켓 접속완료");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Socket getWithClient() {
		return WithClient;
	}

	public void Send(String msg, Socket Client) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
//					System.out.println("서버커넥트에서: "+msg);
					sendMsg = Client.getOutputStream();
					byte[] Msg = msg.trim().getBytes();
					sendMsg.write(Msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}

	private void Receive() {//멀티스레드 돌려있는상태
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

	}
	
	public void SerializableSend( ArrayList<Object> List, Socket Client2) {
		 byte[] serializedMember;
		try {
			ByteArrayOutputStream baos =new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(List);
			serializedMember = baos.toByteArray();
			sendMsg = Client2.getOutputStream();
			sendMsg.write(serializedMember);
			System.out.println("객체 출발했니?");
//			System.out.println("serializedMember는 무엇인가? : "+ serializedMember);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
