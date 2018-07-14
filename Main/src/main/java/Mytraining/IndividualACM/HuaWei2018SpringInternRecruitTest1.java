package Mytraining.IndividualACM;

import javafx.util.Pair;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Huawei Spring 2018-April-10 test for temp worker.
 *
 * 给你一个原始字符串，根据该字符串内每个字符出现的次数，按照ASCII码递增顺序重新调整输出。
 *举例！假设原始字符串为：
 * eeefgghhh
 * 则每种字符出现的次数分别是：
 * (1).eee        3次
 * (2).f          1次
 * (3).gg         2次
 * (4).hhh        3次
 * 重排输出后的字符串如下：
 * efghegheh
 * 编写程序，实现上述功能。
 * 【温馨提示】
 * (1).原始字符串中仅可能出现“数字”和“字母”；
 * (2).请注意区分字母大小写。
 * 
 * 输入描述:
 * eeefgghhh
 * 输出描述:
 * efghegheh
 * 示例1
 * 输入
 * eeefgghhh
 * 输出
 * efghegheh
 */
public class HuaWei2018SpringInternRecruitTest1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String arr = sc.nextLine();
        char[] tmp = arr.toCharArray();
        List<Pair<Character, Integer>> list = new ArrayList<Pair<Character, Integer>>();
        int number = 0;
        int[] record = new int[arr.length()];
        for (Character b: tmp) {
            if(hasCharacter(list, b)){
                Pair<Character, Integer> pair = findPairCharacter(list, b);
                record[pair.getValue()]++;
            }else{
                list.add(new Pair<Character, Integer>(b,number));
                record[number] = 1;
                number++;
            }
        }
        list.sort((p1, p2)->{
            if(p1.getKey() > p2.getKey()){
               return 1;
            }else if(p1.getKey() == p2.getKey()){
                return 0;
            }else{
                return -1;
            }
        });
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        while(flag){
            flag = false;
            for ( Pair<Character, Integer> pair: list) {
                if(record[pair.getValue()] > 0){
                    sb.append(pair.getKey());
                    record[pair.getValue()]--;
                    flag = true;
                }
            }
        }
        System.out.println(sb);
    }
    private static boolean hasCharacter(List<Pair<Character, Integer>> list, Character b){
        for ( Pair<Character, Integer> pair: list) {
            if(pair.getKey().equals(b)){
            return true;
            }
        }
        return false;
    }

    private static Pair<Character, Integer> findPairCharacter(List<Pair<Character, Integer>> list, Character b){
        for ( Pair<Character, Integer> pair: list) {
            if(pair.getKey().equals(b)){
                return pair;
            }
        }
        return null;
    }

    private static Pair<Character, Integer> findPairInteger(List<Pair<Character, Integer>> list, Integer i){
        for ( Pair<Character, Integer> pair: list) {
            pair.getValue().equals(i);
            return pair;
        }
        return null;
    }
}
