package Client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
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
	private byte[] getMsg =new byte[1024];
	private ArrayList<RDTO> rList = null;

	public Cconnect(Socket withServer, Socket withServer2) {

		this.withServer = withServer;
		this.withServer2 = withServer2;
		receive();
		Serializablereceive();
		Hmain = Hmain.getInstance(this);
	}

	private void receive() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("receive start");
				try {
					while (true) {
						reMsg = withServer.getInputStream();
						reMsg.read(getMsg);
						String get = new String(getMsg).trim();
//						get = get.trim(); 트림은 공백을제고하고 리턴값으로 String를 받기에 꼭 앞에 변수를 적어주어야한다
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
						reMsg = withServer2.getInputStream();
						reMsg.read(getMsg);
						ByteArrayInputStream bais = new ByteArrayInputStream(getMsg);
						ObjectInputStream ois = new ObjectInputStream(bais);
						Object objectMember = ois.readObject();
						rList = (ArrayList<RDTO>) objectMember;
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
			byte[] sMsg = Msg.getBytes();
			sendMsg.write(sMsg);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<RDTO> getrList() {
		return rList;
	}

}
