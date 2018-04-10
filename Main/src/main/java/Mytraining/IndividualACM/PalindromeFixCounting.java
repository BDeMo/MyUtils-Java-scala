package Mytraining.IndividualACM;

import java.util.Scanner;

public class PalindromeFixCounting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in );
        while(sc.hasNext()){
            int t = sc.nextInt();
            while(t>0){
                t--;
                String s = sc.next();
    //				long n = (long)sc.nextInt();
                long n = Long.parseLong(s);
                if(n %2 == 1){
                    System.out.println("No");
                    continue;
                }
                long tmp = n;
                while(tmp%2 == 0){
                    tmp = tmp/2;
                }
                System.out.println(tmp + " " + n/tmp);
            }
        }
    }
}
