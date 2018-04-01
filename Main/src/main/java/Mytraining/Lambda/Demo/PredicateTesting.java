package Mytraining.Lambda.Demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTesting {

	public static void main(String[] args) {
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
	    System.out.println("Languages which starts with J :");
	    filter1(languages, (str)->((String) str).startsWith("J"));
	 
	    System.out.println("Languages which ends with a ");
	    filter1(languages, (str)->((String) str).endsWith("a"));
	 
	    System.out.println("Print all languages :");
	    filter1(languages, (str)->true);
	 
	    System.out.println("Print no language : ");
	    filter2(languages, (str)->false);
	 
	    System.out.println("Print language whose length greater than 4:");
	    filter2(languages, (str)->((String) str).length() > 4);
	}

	public static void filter1(List names, Predicate condition) {
	    names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
	        System.out.println(name + " ");
	    });
	}
	public static void filter2(List<String> names, Predicate condition) {
	    for(String name: names)  {
	        if(condition.test(name)) {
	            System.out.println(name + " ");
	        }
	    }
	}

}
