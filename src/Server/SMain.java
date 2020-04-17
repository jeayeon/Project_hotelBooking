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
		
		ServerSocket Server2 = new ServerSocket();
		Socket WithClient2 = null;
		Server2.bind(new InetSocketAddress("10.0.0.115", 4565));
		
		
		SCenter sc = new SCenter();
		
		
		while(true) {
			System.out.println("클라이언트 대기중");
			WithClient = Server.accept();
			System.out.println("클라이언트 접속완료 정보 : "+WithClient);
			
			WithClient2 = Server2.accept();
			
			Sconnect s = new Sconnect(WithClient,sc,WithClient2);
			sc.addServer(s);
			s.start();
		}
	}

	
}
