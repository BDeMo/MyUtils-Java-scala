package Mytraining.IndividualACM;

import java.util.Scanner;

/**
 * Huawei Spring 2018-April-10 test for temp worker.
 * 编写”长整数相乘”程序，实现两个任意长度的长整数(正数)相乘，输出结果.
 *
 * 输入描述:
 * 第一行输入数字A的字符串，字符范围（0～9），第二行输入数字B的字符串，字符范围（0～9）。
 * 输出描述:
 * 输出A、B俩数相乘的结果，结果为字符串。
 * 示例1
 * 输入
 * 1234
 * 4321
 * 输出
 * 5332114
 */

public class HuaWei2018SpringInternRecruitTest3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sarg1 = sc.next();
        String sarg2 = sc.next();
        int[] arg1 = new int[sarg1.length()];
        int[] arg2 = new int[sarg2.length()];
        for(int i = 0; i < sarg1.length(); i++){
            arg1[i] = Integer.parseInt(sarg1.charAt(sarg1.length() - 1 - i) + "");
        }
        for(int i = 0; i < sarg2.length(); i++){
            arg2[i] = Integer.parseInt(sarg2.charAt(sarg2.length() - 1 - i) + "");
        }

        long time = System.currentTimeMillis();

        int[] res = new int[arg1.length + arg2.length + 1];
        for(int i : res){
            i = Integer.MIN_VALUE;
        }
        for(int i = 0; i < arg1.length; i++){
            for(int j = 0; j < arg2.length; j++){
                if(res[i+j] == Integer.MIN_VALUE){
                    res[i+j] = 0;
                }
                res[i+j] = arg1[i]*arg2[j]%10 + res[i+j];
                if(res[i+j+1] == Integer.MIN_VALUE){
                    res[i+j+1] = 0;
                }
                if(res[i+j] > 9){
                    res[i+j+1] = res[i+j]/10 + res[i+j+1];
                    res[i+j] %= 10;
                }
                res[i+j+1] = arg1[i]*arg2[j]/10 + res[i+j+1];
            }
        }
        String resstr = "";
        for(int i = 0; i < res.length; i++){
            resstr += res[res.length - 1 - i] + "";
        }
        System.out.println(resstr);
        time = System.currentTimeMillis() - time;
        System.out.println(time);
    }
}
