package FTransmit.filetransmitImp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FTSendResponeImp implements Runnable {
	
	String path = null;
	Socket sc = null;
	InputStream in = null;
	OutputStream out = null;
	
	public FTSendResponeImp(Socket sc) {
		this.sc = sc;
		try {
			this.in = sc.getInputStream();
			this.out = sc.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		DataInputStream dis = new DataInputStream(in);
		DataOutputStream dos = new DataOutputStream(out);
		try {
			path = dis.readInt() + "";
			String req = dis.readUTF();
			if(!req.equals("wanna_send")) {
				return;
			}
			dos.writeUTF("OK");
			new Thread(new FTReceiveImp(sc, path)).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
