package ExecTest;

import java.io.*;

public class ExecTestMain {
	public static void main(String[] args) throws IOException, InterruptedException {
    String cmd = "cmd /c dir c:\\windows";
    String cmds = "echo $JAVA_HOME";
	String[] command = { "cmd", "/c", cmds };
		final Process process = Runtime.getRuntime().exec(command);
    printMessage(process.getInputStream());
    printMessage(process.getErrorStream());
    int value = process.waitFor();
    System.out.println(value);
	}

	private static void printMessage(final InputStream input) {
    	new Thread(new Runnable() {
			public void run() {
				Reader reader = new InputStreamReader(input);
				BufferedReader bf = new BufferedReader(reader);
				String line = null;
				try {
					while((line=bf.readLine())!=null) {
					System.out.println(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
    	}).start();
	}
}
