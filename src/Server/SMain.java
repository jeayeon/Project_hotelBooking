package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class SMain {

	public static void main(String[] args) throws IOException {
		Random r = new Random();
		int RandomP = r.nextInt(8999)+1000;

		ServerSocket Server = new ServerSocket();
		Socket WithClient = null;
		Server.bind(new InetSocketAddress("10.0.0.115", 784));

		ServerSocket Server2 = new ServerSocket();
		Server2.bind(new InetSocketAddress("10.0.0.115", RandomP));

		SCenter sc = new SCenter();

		while (true) {
			System.out.println("클라이언트 대기중");
			WithClient = Server.accept();
			System.out.println("클라이언트 접속완료 정보 : " + WithClient);


			Sconnect s = new Sconnect(WithClient, sc, RandomP,Server2);
			sc.addServer(s);
			s.start();
		}
	}

}
