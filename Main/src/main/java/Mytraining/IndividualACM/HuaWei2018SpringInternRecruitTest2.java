package Mytraining.IndividualACM;

import java.util.Scanner;

/**
 * Huawei Spring 2018-April-10 test for temp worker.
 *给出一组正整数，你从第一个数向最后一个数方向跳跃，每次至少跳跃1格，每个数的值表示你从这个位置可以跳跃的最大长度。计算如何以最少的跳跃次数跳到最后一个数。
 *
 * 输入描述:
 * 第一行表示有多少个数n
 * 第二行开始依次是1到n个数，一个数一行
 * 输出描述:
 * 输出一行，表示最少跳跃的次数。
 * 示例1
 * 输入
 * 7
 * 2
 * 3
 * 2
 * 1
 * 2
 * 1
 * 5
 * 输出
 * 3
 * 说明
 * 7表示接下来要输入7个正整数，从2开始。数字本身代表可以跳跃的最大步长，此时有2种跳法，为2-2-2-5和2-3-2-5都为3步
 */
public class HuaWei2018SpringInternRecruitTest2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][2];
        for(int i = 0; i < n; i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = Integer.MAX_VALUE;
        }
        arr[arr.length - 1][1] = 1;
        for(int i = arr[arr.length - 1][1]; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                if(arr[j][1] == i){
                    if(j == 0){
                        System.out.println(i - 1);
                        return;
                    }
                    for(int k = 0; k < j; k++){
                        if(arr[k][0] + k >= j && arr[k][1] > i + 1){
                            arr[k][1] = i+1;
                        }
                    }
                }
            }
        }
    }
}
