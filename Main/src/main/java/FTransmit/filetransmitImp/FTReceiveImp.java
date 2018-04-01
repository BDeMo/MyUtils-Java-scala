package FTransmit.filetransmitImp;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class FTReceiveImp implements Runnable {
	Socket sc = null;
	String savePath = null;
	public FTReceiveImp(Socket sc, String savePath) {
		this.sc = sc;		
		this.savePath = savePath;
	}
	public void run(){
		DataInputStream dis = null;
		FileOutputStream fos = null;
		byte[] recieveBytes = null;
		int length = 0;
		File f = null;
		String fileName = null;
		try {
			dis = new DataInputStream(sc.getInputStream());
			fileName = dis.readUTF();
			if(savePath != null){
				if(savePath.endsWith("/")
						|| savePath.endsWith("\\"))
					f = new File(savePath + fileName);
				else 
					f = new File(savePath + "/"+ fileName);
			}else
				f = new File(fileName);
			if(f.exists()) {
				System.out.println("File Existed.");
				return;
			}
			if(!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			fos = new FileOutputStream(f);
			System.out.println(sc.getRemoteSocketAddress()+ ":" + f.getAbsolutePath());
			recieveBytes = new byte[65536];
			while(true){
				length = readn(dis, recieveBytes, recieveBytes.length);
				if(length == 0){
					break;
				}
				fos.write(recieveBytes, 0, recieveBytes.length);
				fos.flush();
			}
			System.out.println("Recieving Completed");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fos.close();
//				dis.close();
////				sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static int readn(InputStream is, byte[] bytes, int length) throws IOException {
		int remain = length;
		int tmp = 0;
		int done = 0;
		try{
			while(true){
				tmp = is.read(bytes, done, remain);
				if(tmp == 0
						||tmp == -1)break;
				done += tmp;
				remain -= tmp;
				if(length == 0)break;
			}
			return done;
		}catch(Exception e){
			e.printStackTrace();
			throw new IOException("Ur programme suck in socket reading.");
		}
	}

}
