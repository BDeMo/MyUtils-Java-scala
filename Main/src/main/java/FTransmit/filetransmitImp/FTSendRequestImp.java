package FTransmit.filetransmitImp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class FTSendRequestImp implements Runnable {

	int folder;
	String path = null;
	Socket sc = null;
	InputStream in = null;
	OutputStream out = null;
	
	public FTSendRequestImp(int folder, String path, Socket sc) {
		this.folder = folder;
		this.path = path;
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
			dos.writeInt(folder);
			dos.writeUTF("wanna_send");
			String res = dis.readUTF();
			if(!res.equals("OK"))
				throw new RefuseException("Refused");
			new Thread(new FTSendImp(sc, path)).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	@SuppressWarnings("serial")
	private class RefuseException extends Exception {
	    public RefuseException(String msg) {
	        super(msg);
	    }
	    @SuppressWarnings("unused")
		public RefuseException() {
	    }
	}

}
