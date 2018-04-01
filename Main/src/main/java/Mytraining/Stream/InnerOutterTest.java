package Mytraining.Stream;


import TestTools.ActionHelper;
import TestTools.TimeCalculator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Testing inner and outer interation of stream.
 */
public class InnerOutterTest {
    public static void main(String[] args) {

        long currentTime = 0;

        List<Long> list = new ArrayList<Long>();

        currentTime = System.currentTimeMillis();
        for(int i = 0; i < 67738; i++){
            list.add( (long)i + 1 );
        }
        currentTime = System.currentTimeMillis() - currentTime;
        System.out.println("list.size :" + list.size());
        System.out.println("initializing list time-taking:" + currentTime);

        /**
         * mission for outter interation operating interate, sort, multiply and print.
         */
        currentTime = System.currentTimeMillis();
        List<Long> tmpList = new ArrayList<Long>();
        for (int i = 0; i < list.size(); i++){
            if(list.get(i) % 2 == 0){
                tmpList.add(list.get(i));
            }
        }
        tmpList.sort(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                if((long)o1 >= (long)o2){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        for (Long i :
                tmpList) {
            System.out.print((long)i*(long)i + " ");
        }
        long outterTime = System.currentTimeMillis() - currentTime;
        System.out.println("");

        /**
         * mission for inner interation operating interate, sort, multiply and print.
         */
        currentTime = System.currentTimeMillis();
        list.stream().filter(a -> (long)a % 2 == 0).sorted((o1, o2) -> {
            if((long)o1 >= (long)o2){
                return -1;
            }else{
                return 1;
            }
        }).forEach(i -> {
            System.out.print((long)i*(long)i + " ");
        });
        long innerTime = System.currentTimeMillis() - currentTime;

        System.out.println("");
        System.out.println("list interating of outer operating time-taking:" + outterTime);
        System.out.println("list interating of inner operating time-taking:" + innerTime);
        System.out.println("\nlist interating mission of inner operating with MyLambda time-taking:"
                + TimeCalculator.Calculate(()->{
            list.stream().filter(a -> (long)a % 2 == 0).sorted((o1, o2) -> {
                if((long)o1 >= (long)o2){
                    return -1;
                }else{
                    return 1;
                }
            }).forEach(i -> {
                System.out.print((long)i*(long)i + " ");
            });
        }));
    }
}
