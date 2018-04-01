package Mytraining.DP.DP_CutRod;

public class DemoB2T {

	private static int r[];
	private static int[] price = DemoCutRod.price;
	
	public static void main(String[] args) {
		r = new int[price.length];
		for(int n = 1; n<price.length; n++) {
			for (int i=0; i<r.length; i++) {
				r[i] = 0;
			}
			long time = System.currentTimeMillis();
			for(int j = 1; j <= n; j++) {
				int q = Integer.MIN_VALUE;
				for(int i = 1; i <= j; i++)
					q = Mytraining.DP.utils.MyComparator.GetMax(q, price[i] + r[j-i]);
				r[j] = q;
			}
			System.out.println("n = " + n + "\tr = " + r[n]);
			time = System.currentTimeMillis() - time;
			System.out.println(time);
		}
			
	}
	
}
