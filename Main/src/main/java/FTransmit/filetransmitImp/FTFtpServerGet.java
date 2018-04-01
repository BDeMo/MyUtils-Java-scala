package FTransmit.filetransmitImp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FTFtpServerGet implements Runnable {
	
	int port_get;
	
	public FTFtpServerGet(int port_get) {
		this.port_get = port_get;
	}
	public void run() {
		ServerSocket ss = null;
		Socket sc = null;
		try {
			ss = new ServerSocket(port_get);
			System.out.println("FTPserver_Get on");
			while(true) {
				sc = ss.accept();
				new Thread(new FTGetResponeImp(sc)).start();
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
