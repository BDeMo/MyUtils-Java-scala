package FTransmit.filetransmitImp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class FTSendImp implements Runnable{
	String path = null;
	Socket sc = null;
	FileInputStream fis = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	byte[] sendBytes = null;
	File targetFile = null;
	int length = 0;
	
	public FTSendImp(Socket sc, String path) {
		this.sc = sc;
		this.path = path;
	}
	public void run() {
		try{
			targetFile = new File(path);
			fis = new FileInputStream(targetFile);
			dos = new DataOutputStream(sc.getOutputStream());
			dos.writeUTF(targetFile.getName());
			sendBytes = new byte[65536];
			while((length = fis.read(sendBytes, 0, sendBytes.length)) > 0){
				dos.write(sendBytes, 0, length);
				dos.flush();
//				System.out.println("this time send :" + length);
			}
			System.out.println("Sending Complete:" +sc.getRemoteSocketAddress()+"\n\t"+ targetFile.getAbsolutePath());
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				fis.close();
				dos.close();
////				sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
