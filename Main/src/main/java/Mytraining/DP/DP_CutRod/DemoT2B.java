package Mytraining.DP.DP_CutRod;

public class DemoT2B {

	private static int r[];
	private static int[] price = DemoCutRod.price;
	
	private static int aux(int n) {
		if(r[n] >= 0)
			return r[n];
		int q;
		if(n == 0)
			q = 0;
		else {
			q = Integer.MIN_VALUE;
			for(int i = 1; i<= n; i++) {
				q = Mytraining.DP.utils.MyComparator.GetMax(q, price[i] + aux(n-i));
			}
			r[n] = q;
		}
		return q;
	}
	
	public static void main(String[] args) {
		r = new int[price.length];
		for (int i=0; i<r.length; i++) {
			r[i] = Integer.MIN_VALUE;
		}
		System.out.println("price length = " + price.length);
		for(int i = 0; i < price.length; i++) {
			long time = System.currentTimeMillis();
			System.out.println("n = " + i + "\tr = " + aux(i));
			time = System.currentTimeMillis() - time;
			System.out.println(time);
		}
	}

}
