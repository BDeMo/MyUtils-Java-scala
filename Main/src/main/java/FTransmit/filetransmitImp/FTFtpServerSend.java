package FTransmit.filetransmitImp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FTFtpServerSend implements Runnable{

	int port_send;
	
	public FTFtpServerSend(int port_send) {
		this.port_send = port_send;
	}
	public void run() {
		ServerSocket ss = null;
		Socket sc = null;
		try {
			ss = new ServerSocket(port_send);
			System.out.println("FTPserver_Send on");
			while(true) {
				sc = ss.accept();
				new Thread(new FTSendResponeImp(sc)).start();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
