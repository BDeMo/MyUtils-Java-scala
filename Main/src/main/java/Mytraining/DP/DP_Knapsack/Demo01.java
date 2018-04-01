package Mytraining.DP.DP_Knapsack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Demo01 {

	public static void main(String[] args) {
        List<Integer[]> items = new ArrayList<Integer[]>();
        
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        /*
         * 	number,capacity:	n,c
         	weight:	w1,w2,w3...
          	value:	v1,v2,v3...
         * 
         * 	example:
         	5,10
			2,2,6,5,4
			6,3,5,4,6
			
			answer:
			15
         * 
         */
        int n = Integer.parseInt(line.split(",")[0]);
        int c = Integer.parseInt(line.split(",")[1]);
        line = in.nextLine();
        String[] strWeight = line.split(",");
        line = in.nextLine();
        String[] strValue = line.split(",");
        for(int i = 0; i < n; i++) {
        	Integer[] wv = new Integer[2];
        	wv[0] = Integer.parseInt(strWeight[i]);
        	wv[1] = Integer.parseInt(strValue[i]);
        	items.add(wv);
        }
        System.out.println(solve(items, n, c));
	}
	public static Integer solve(List<Integer[]> items, int n, int c) {
		if(n <= 0
				|| c <= 0
				|| items.get(n - 1)[0] > c)
			return 0;
		int res = Mytraining.DP.utils.MyComparator.GetMax(items.get(n-1)[1] + solve(items, n-1, c - items.get(n-1)[0]),
				solve(items, n-1, c));
		System.out.println(res+","+n+","+c);
		return res;
	}

}
