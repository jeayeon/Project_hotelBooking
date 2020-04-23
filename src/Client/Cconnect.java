package Client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import JDBC.MDTO;
import JDBC.RDTO;
import Swing.Hmain;

public class Cconnect {

	private Socket withServer = null;
	private Socket withServer2 = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private Hmain Hmain = null;
	private ArrayList<Object> List = null;
	private Object objectList = null;

	public Cconnect(Socket withServer) {

		this.withServer = withServer;
		init();
		receive();
		Serializablereceive();
		Hmain = Hmain.getInstance(this);
	}

	private void init() {
		try {
			byte[] getMsg1 = new byte[200];
			reMsg = withServer.getInputStream();
			reMsg.read(getMsg1);
			String get = new String(getMsg1).trim();
			int port = Integer.parseInt(get);
			withServer2 = new Socket("10.0.0.115",port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void receive() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("receive start");
				try {
					while (true) {
						byte[] getMsg1 = new byte[200];
						reMsg = withServer.getInputStream();
						reMsg.read(getMsg1);
						String get = new String(getMsg1).trim();
						char num = get.charAt(0);
						if (num == '!') { // 로그인
							Hmain.Login(get);
							// Cconect생성자가 호출할때 띄우는데 회원가입후 join클래스에서 Hmain이 생성되기떄문에 널포인트에러가뜸
						} else if (num == '@') { // id중복체크
							Hmain.join(get);
						} 
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}).start();

	}

	private void Serializablereceive() { // 객체를 받을때 사용할 메소드
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						byte[] getMsg = new byte[1024];
						reMsg = withServer2.getInputStream();
						System.out.println("도착안했어");
						reMsg.read(getMsg);
						ByteArrayInputStream bais = new ByteArrayInputStream(getMsg);
						ObjectInputStream ois = new ObjectInputStream(bais);
						objectList = ois.readObject();
						List = (ArrayList<Object>) objectList;
						System.out.println("C커넥트에서 사이즈:" + List.size());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void send(String Msg) {
		try {
			sendMsg = withServer.getOutputStream();
			byte[] sMsg = Msg.trim().getBytes();
			sendMsg.write(sMsg);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Object getrList() {
		return objectList;
	}

}
