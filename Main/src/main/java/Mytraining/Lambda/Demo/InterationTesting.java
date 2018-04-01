package Mytraining.Lambda.Demo;

import java.util.Arrays;
import java.util.List;

public class InterationTesting {
	
	/**
	 * 2 types of coding.
	 * @param args
	 */
	public static void main(String[] args) {
		//List,List<String>
		List lst1 = Arrays.asList("Lambda", "Interation", "Testing", "List");
		List<String> lst2 = Arrays.asList("Lambda", "Interation", "Testing", "List");
		
		lst1.forEach(System.out::println);
		lst1.forEach(n -> System.out.println(n.getClass() + " " + n));
		lst2.forEach(System.out::println);
		lst2.forEach(n -> System.out.println(n.getClass() + n));

		List intlst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
		intlst.forEach(System.out::println);
		
	}

}
