package Mytraining.IndividualACM;

import java.util.Scanner;

/**
 * 阴阳师
 * 时间限制：C/C++语言 2000MS；其他语言 4000MS
 * 内存限制：C/C++语言 65536KB；其他语言 589824KB
 * 题目描述：
 * 阴阳师是一个很肝的游戏，它最肝的地方在于肝狗粮。在阴阳师中，式神是分星级的，初始时你得到的式神都是二星的。若你想将一只二星式神进化到三星，则需要将两只二星式神作为狗粮喂给它。若你想将一只三星式神进化到四星，则需要将三只三星式神作为狗粮喂给它。一般地，若你想将一只 x 星的式神进化到 x+1 星，则需要将 x 只 x 星的式神作为狗粮喂给它。现在你想得到一只 n 星的式神，请你计算一下你要准备多少只二星的式神？
 *
 * 输入
 * 第一行包含一个整数 。2 ≤n ≤ 30
 *
 * 输出
 * 输出一个整数，表示你需要准备的二星式神数量。
 *
 *
 * 样例输入
 * 2
 *
 * 样例输出
 * 1
 *
 *
 * Hint
 * Input Sample 2
 * 4
 * Output Sample 2
 * 12
 */
public class BaiDu2018SpringInternRecruitTest3 {
	private static int[] rec = new int[31];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		rec[2] = 1;
		int i = sc.nextInt();
		System.out.println(getN(i));
	}
	private static int getN(int n){
		if(rec[n] <= 0){
			rec[n] = getN(n - 1) * n;
		}
		return rec[n];
	}
}
