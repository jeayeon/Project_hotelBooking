package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class SMain {

	
	public static void main(String[] args) throws IOException {
		
		ServerSocket Server = new ServerSocket();
		Socket WithClient = null;
		Server.bind(new InetSocketAddress("10.0.0.115", 7890));
		
		SCenter sc = new SCenter();
		
		
		while(true) {
			System.out.println("클라이언트 대기중");
			WithClient = Server.accept();
			System.out.println("클라이언트 접속완료 정보 : "+WithClient);
			
			Sconnect s = new Sconnect(WithClient,sc);
			sc.addServer(s);
			s.start();
		}
	}

	
}
