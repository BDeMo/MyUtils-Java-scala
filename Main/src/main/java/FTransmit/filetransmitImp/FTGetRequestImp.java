package FTransmit.filetransmitImp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FTGetRequestImp implements Runnable{
	String path = null;
	String target = null;
	Socket sc = null;
	InputStream in = null;
	OutputStream out = null;
	
	public FTGetRequestImp(String target, String path, Socket sc) {
		this.path = path;
		this.target = target;
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
			dos.writeUTF(target);
			String res = dis.readUTF();
			if(!res.equals("OK"))
				throw new RefuseException("Refused");
			dos.writeUTF("ready");
			new Thread(new FTReceiveImp(sc, path)).start();
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
