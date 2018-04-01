package FTransmit.filetransmitImp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FTGetResponeImp implements Runnable {
	
	Socket sc = null;
	InputStream in = null;
	OutputStream out = null;
	
	public FTGetResponeImp(Socket sc) {
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
			File f = new File(dis.readUTF());
			System.out.println("F:" + f.getAbsolutePath());
			if(!f.exists())
				dos.writeUTF("refuse");
			dos.writeUTF("OK");
			String res = dis.readUTF();
			System.out.println(res);
			if(res.equals("ready"))
				new Thread(new FTSendImp(sc, f.getAbsolutePath())).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
