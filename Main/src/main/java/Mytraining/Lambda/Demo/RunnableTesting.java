package Mytraining.Lambda.Demo;

public class RunnableTesting {

	public static void main(String[] args) {
		for(int i=0; i < 10; i++) {
			new Thread(()->System.out.println("runnable lambda")).start();
		}
	}

}
