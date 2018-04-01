package Mytraining.DP.DP_CutRod;

public class DemoCutRod {
	
	public final static int[] price = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30, 32, 32, 40, 41, 41, 44, 45, 48, 53, 60, 63, 66, 67, 80, 81, 81, 82}; 
	
	private static int Cut(int n) {
		if(n == 0) {
			return price[0];
		}
		int q = Integer.MIN_VALUE;
		for(int i = 1;  i <= n; i++) {
			q = Mytraining.DP.utils.MyComparator.GetMax(q, price[i] + Cut(n-i));
		}
		return q;
	}
	
	public static void main(String[] args) {
		System.out.println("price length = " + price.length);
		for(int i = 0; i < price.length; i++) {
			long time = System.currentTimeMillis();
			System.out.println("n = " + i + "\tr = " + Cut(i));
			time = System.currentTimeMillis() - time;
			System.out.println(time);
		}
	}
	
}
