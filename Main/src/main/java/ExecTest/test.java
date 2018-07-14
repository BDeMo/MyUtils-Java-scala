package ExecTest;

import java.io.*;
public class test{
	public static void main(String[] args){
		try{
			String cmds = "echo $JAVA_HOME";
			// String[] command = { "/bin/sh", "-c", cmds };
			String[] command = {"cmd", "/c", cmds};
			printMessage(Runtime.getRuntime().exec(command).getInputStream());
		}catch(Exception e){
			;
		}
	}
	private static void printMessage(InputStream input){
		try{
			BufferedReader bf = new BufferedReader(new InputStreamReader(input));
			System.out.println(bf.readLine());
		}catch(Exception e){
			;
		}
	}
}