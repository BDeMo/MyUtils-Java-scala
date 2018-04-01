package FTransmit.filetransmit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import FTransmit.filetransmitImp.FTFtpServerGet;
import FTransmit.filetransmitImp.FTFtpServerSend;
import FTransmit.filetransmitImp.FTGetRequestImp;
import FTransmit.filetransmitImp.FTReceiveImp;
import FTransmit.filetransmitImp.FTSendImp;
import FTransmit.filetransmitImp.FTSendRequestImp;

public class FTransmit {
	final static int port4Single = 7071;
	final static int port4FTP_send = 7072;
	final static int port4FTP_get = 7073;
	final static String version = "1.1";
	
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Try help to view commands.");
			return;
		}
		String cmd = args[0];
		String[] arg = new String[args.length - 1];
		for(int  i = 0; i < arg.length; i++) {
			arg[i] = args[i + 1];
		}
		if(cmd.toLowerCase().equals("help")) {
			operateHelp(arg);
		}else if(cmd.toLowerCase().equals("producer")) {
			operateProducer(arg);
		}else if(cmd.toLowerCase().equals("version")) {
			operateVersion(arg);
		}else if(cmd.toLowerCase().equals("send")) {
			operateSend(arg);
		}else if(cmd.toLowerCase().equals("receive")) {
			operateReceive(arg);
		}else if(cmd.toLowerCase().equals("ftpserver")) {
			operateFTPServer(arg);
		}else if(cmd.toLowerCase().equals("ftpsend")) {
			operateFTPSend(arg);
		}else if(cmd.toLowerCase().equals("ftpget")) {
			operateFTPGet(arg);
		}else {
			System.out.println("That's not a command.");
		}
		
	}
	private static void operateFTPGet(String[] path) {
		if(path.length == 0) {
			System.out.println("There's no path.");
			return;
		}
		Matcher m = null;
		Socket sc = null;
		try {
			m = Pattern.compile("((([0-9]{1,3})(.)){3}([0-9]{1,3}))(/)(.*)").matcher(path[0]);
			if(m.matches()) {
				sc = new Socket(m.group(1), port4FTP_get);
				new Thread(new FTGetRequestImp(m.group(7), path[1], sc)).start();
			}
			else{
				System.out.println("There must be a absolute path like *.*.*.*/filepath.");
				return;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	private static void operateHelp(String[] arg) {
		System.out.println("Command:\n"
				+ "version \n"
				+ "send [inetaddress]/[filepath]\n"
				+ "receive [savepath]\n"
				+ "ftpserver \n"
				+ "ftpsend [inetaddress/filepath, foldnameNumber]\n"
				+ "ftpget [inetaddress/filepath, savepath]\n"
				+ "\n");
	}
	private static void operateProducer(String[] arg) {
		System.out.println("Producer: SamJ");
	}
	private static void operateVersion(String[] arg) {
		System.out.println("Version: " + version);
	}
	private static void operateSend(String[] path) {
		if(path.length == 0) {
			System.out.println("There's no path.");
			return;
		}
		Matcher m = null;
		Socket sc = null;
		try {
			m = Pattern.compile("((([0-9]{1,3})(.)){3}([0-9]{1,3}))(/)(.*)").matcher(path[0]);
			if(m.matches()) {
				sc = new Socket(m.group(1), port4Single);
				new Thread(new FTSendImp(sc, m.group(7))).start();
			}
			else{
				System.out.println("There must be a absolute path like *.*.*.*/filepath.");
				return;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	private static void operateReceive(String[] savePath) {
		ServerSocket ss = null;
		Socket sc = null;
		try {
			ss = new ServerSocket(port4Single);
			while(true) {
				sc = ss.accept();
				if(savePath.length == 0)
					new Thread(new FTReceiveImp(sc, null)).start();
				else
					new Thread(new FTReceiveImp(sc, savePath[0])).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private static void operateFTPServer(String[] arg) {
		try {
			new Thread(new FTFtpServerSend(port4FTP_send)).start();
			new Thread(new FTFtpServerGet(port4FTP_get)).start();			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void operateFTPSend(String[] path) {
		if(path.length == 0) {
			System.out.println("There's no path.");
			return;
		}
		Matcher m = null;
		Socket sc = null;
		try {
			m = Pattern.compile("((([0-9]{1,3})(.)){3}([0-9]{1,3}))(/)(.*)").matcher(path[0]);
			if(m.matches()) {
				sc = new Socket(m.group(1), port4FTP_send);
				new Thread(new FTSendRequestImp(Integer.parseInt(path[1]), m.group(7), sc)).start();
			}
			else{
				System.out.println("There must be a absolute path like *.*.*.*/filepath.");
				return;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
