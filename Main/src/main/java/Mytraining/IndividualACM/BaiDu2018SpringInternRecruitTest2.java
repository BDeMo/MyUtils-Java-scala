package Mytraining.IndividualACM;

import java.util.Scanner;

/**
 * 爬山
 * 时间限制：C/C++语言 2000MS；其他语言 4000MS
 * 内存限制：C/C++语言 65536KB；其他语言 589824KB
 * 题目描述：
 * 冬木市西边的园藏山是著名的旅游圣地。从空中俯瞰，园藏山可以看成一个 n * m 的矩阵，我们把行从上往下按 1 到 n 编号，把列从左往右按 1 到 m 编号，那么(i, j)就表示矩阵第 i 行第 j 列的位置。我们用ℎi,j , 表示位置(i, j)的海拔高度。
 *
 *
 *
 * 初始时，Saber 在(sx, sy)这个位置，她想前往更高的地方。每一次她可以选择向上、下、左、右其中一个方向走，但不能走出这个矩阵；同时，作为大不列颠的王，孤傲的 Saber 不愿意走到比她当前所在的位置海拔要低的位置，也就是说在移动的过程中，每一步她都只能向海拔不低于她当前所在的位置的那些位置移动。请你帮忙计算出她所能走到的最高高度。
 *
 * 输入
 * 第一行包含两个整数n,m , 表示矩阵的规模1 ≤n  , m≤ 200
 *
 * 第二行包含两个整数sx,sy 表示初始时 Saber 的位置1 ≤ sx≤n  , 1 ≤ sy≤m
 *
 * 接下来n行每行包含m个数字，0 ≤ ℎi,j ≤109 ，表示位置(i, j)的海拔高度。
 *
 * 输出
 * 输出 Saber 能够移动到的最高高度。
 *
 *
 * 样例输入
 * 2 2
 *
 * 1 1
 *
 * 2 1
 *
 * 1 3
 *
 * 样例输出
 * 2
 *
 *
 * Hint
 * input sample 2
 * 2 3
 * 1 1
 * 1 5 2
 * 0 4 9
 * output sample 2
 * 5
 */
public class BaiDu2018SpringInternRecruitTest2 {
	static int[][] map = null;
	static int n;
	static int m;
	static int startN = 0;
	static int startM = 0;
	static int[][] tmp = null;
	static int max = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		startN = sc.nextInt() - 1;
		startM = sc.nextInt() - 1;
		map = new int[n][m];
		tmp = new int[n][m];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				map[i][j] = sc.nextInt();
				tmp[i][j] = -1;
			}
		}
		tmp[startN][startN] = map[startN][startM];
		chgMax(tmp[startN][startN]);
		search();
		System.out.println(max);
	}
	private static void search(){
		boolean flag = false;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(tmp[i][j] == -1){
					continue;
				}
				if(i > 0
						&& tmp[i][j] < map[i - 1][j]
						&& tmp[i][j] != -1){
					tmp[i - 1][j] = map[i - 1][j];
					tmp[i][j] = -1;
					chgMax(tmp[i - 1][j]);
					flag = true;
				}
				if(j > 0
						&& tmp[i][j] < map[i][j - 1]
						&& tmp[i][j] != -1){
					tmp[i][j - 1] = map[i][j -1];
					tmp[i][j] = -1;
					flag = true;
					chgMax(tmp[i][j - 1]);
				}
				if(j < n - 1
						&& tmp[i][j] < map[i][j + 1]
						&& tmp[i][j] != -1){
					tmp[i][j + 1] = map[i][j + 1];
					tmp[i][j] = -1;
					flag = true;
					chgMax(tmp[i][j + 1]);
				}
				if(i < n - 1
						&& tmp[i][j] < map[i + 1][j]
						&& tmp[i][j] != -1){
					tmp[i + 1][j] = map[i + 1][j];
					tmp[i][j] = -1;
					flag = true;
					chgMax(tmp[i + 1][j]);
				}
			}
		}
		if(flag){
			search();
		}
	}
	private static void chgMax(int m){
		if(m > max){
			max = m;
		}
	}
}
