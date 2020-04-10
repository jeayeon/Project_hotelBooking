package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import Swing.Hmain;

public class Cconnect {

	private Socket withServer = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private Hmain Hmain =null;
	
	public Cconnect(Socket withServer) {

		this.withServer = withServer;
		receive();
		Hmain =Hmain.getInstance(this);
	}

	private void receive() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("receive start");
				try {
					while (true) {
						reMsg = withServer.getInputStream();
						byte[] getMsg = new byte[200];
						reMsg.read(getMsg);
						String get = new String(getMsg).trim();
//						get = get.trim(); 트림은 공백을제고하고 리턴값으로 String를 받기에 꼭 앞에 변수를 적어주어야한다
//						System.out.println(get);
						char num = get.charAt(0);
						if(num=='!') { // 로그인
							Hmain.Login(get); 
			//Cconect생성자가 호출할때 띄우는데 회원가입후 join클래스에서 Hmain이 생성되기떄문에 널포인트에러가뜸
						}else if(num=='@') { // id중복체크
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

}
