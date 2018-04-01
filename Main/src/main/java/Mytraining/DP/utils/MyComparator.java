package Mytraining.DP.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyComparator {
	
	public static int GetMax(int... a) {
		List<Integer> nums = new ArrayList<Integer>();
		for(int  i=0 ; i<a.length; i++) {
			nums.add(a[i]);
		}
		return Collections.max(nums);
	}
	
}
