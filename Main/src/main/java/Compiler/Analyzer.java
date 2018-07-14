package Compiler;

import javafx.scene.control.Tab;

import java.io.*;
import java.util.*;

/**
 * 语法分析期
 *
 * @auther SamJ
 * @create 2018 - 06 - 02 10:35
 */
public class Analyzer {

	//region	私有变量

	boolean LL1checked = false;
	boolean isLL1 = false;

	/**
	 * 来自Parser的Tokens表
	 */
	List<Token> Tokens = null;

	/**
	 * 语法集合，需要生成
	 */
	Map<String, List<String>> Grammers = null;

	/**
	 * First集合，需要生成
	 */
	Map<String, List<String>> Firsts = null;

	/**
	 * Follow集合，需要生成
	 */
	Map<String, List<String>> Follows = null;

	/**
	 * 分析表，需要生成
	 */
	Map<String, Map<String, List<String>>> Table = null;

	/**
	 * 终结符表
	 */
	List<String> Ends = null;

	//endregion

	//region	构造方法

	/**
	 * 构造方法，传入来自Parser的Tokens表
	 * @param Tokens
	 */
	Analyzer(List<Token> Tokens){
		if(Tokens != null)
			this.Tokens = Tokens;
	}

	/**
	 * 无参构造方法
	 */
	Analyzer(){
		this(null);
	}

	//endregion


	//region 公有方法

	public void SetToken(List<Token> Tokens){
		this.Tokens = Tokens;
	}

	public void FitTokens(String arg) {

		try {
			if (!LL1checked) {
				throw new Exception("还没有检验是否是LL1文法");
			}
			if (!isLL1) {
				throw new Exception("不是LL1文法，不能使用");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!Grammers.containsKey(arg)) {
			System.out.println("not exist in the grammer");
			return;
		}
		Stack<String> ast = new Stack();
		Stack<Token> ist = new Stack();
		ast.push("$");
		ast.push(arg);
		ist.push(new Token("$", TokenType.Character));
		for (int i = Tokens.size() - 1; i >= 0; i--) {
			ist.push(Tokens.get(i));
		}
		while (!ast.isEmpty()
				|| !ast.isEmpty()) {
			if(ist.peek().tokenValue.equals("$")
					&& !ast.peek().equals("$")){
				ist.push(new Token("empty", TokenType.Word));
			}
			String astStr = "";
			String istStr = "";
			String action = null;
			for (String str : ast) {
				astStr += str + " ";
			}
			for (Token tk : ist) {
				istStr += tk.tokenValue + " ";
			}
			if (Table.containsKey(ast.peek())) {
				List<String> tmp = null;
				if (ist.peek().tokenType.equals(TokenType.Number)
						|| ist.peek().tokenType.equals(TokenType.Identity)) {
					tmp = Table.get(ast.peek()).get(ist.peek().tokenType.name());
				} else {
					tmp = Table.get(ast.peek()).get(ist.peek().tokenValue);
				}
				if (tmp == null
						|| tmp.size() == 0) {
					if(ist.peek().tokenValue.equals("empty")){
						System.out.println("Fit Failed");
						return;
					}else{
						ist.push(new Token("empty", TokenType.Word));
						continue;
					}
				}
				action = ast.peek() + " -> " + tmp.get(0);
				String[] strs = MySplit(tmp.get(0));
				ast.pop();
				for (int i = strs.length - 1; i >= 0; i--) {
					ast.push(strs[i]);
				}
			} else {
				if ((ist.peek().tokenType.name().equals("Number")
						&& ast.peek().equals("Number"))
						||(ist.peek().tokenType.name().equals("Identity")
						&& ast.peek().equals("Identity"))) {
					ist.pop();
					ast.pop();
				} else {
					if (ist.peek().tokenValue.equals(ast.peek())) {
						ist.pop();
						ast.pop();
					} else {
						System.out.println("Fit Failed");
						return;
					}
				}
				if (ast.isEmpty()
						&& ast.isEmpty()) {
					action = "Acc";
				} else {
					action = "Fitted";
				}
			}
			System.out.println(astStr + "\t\t" + istStr + "\t\t" + action);
		}
	}

	/**
	 * 读取文法,并消除左递归、提取左公因子
	 */
	public void GetGrammer(String path){
		File file = null;
		try {
			System.out.println("_________________GetGrammer________________________");
			Grammers = new HashMap<String, List<String>>();
			file = new File(path);
			if(!file.exists()){
				throw new FileNotFoundException("文件不存在");
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String str = null;
			while((str = br.readLine()) != null){
				String[] strs = str.split(" -> ");
				List<String> gras = new ArrayList<String>();
				String[] rights = strs[1].split("\\|");
				for (String tmp: rights) {
					gras.add(tmp.trim());
				}
				Grammers.put(strs[0].split(". ")[1], gras);
			}
			List<String> targets1 = new ArrayList<String>();
			for(String str1 :Grammers.keySet()){
				for(String str2 : Grammers.get(str1)){
					if(str2.startsWith(str1)){
						if(!targets1.contains(str1)){
							targets1.add(str1);
						}
					}
				}

			}

			System.out.println("-----------------------------Recursion--------------------------");
			//处理左递归
			for(String str1 : targets1){
				List<String> list = new ArrayList<String>();
				List<String> list1 = new ArrayList<String>();
				List<String> list2 = new ArrayList<String>();
				for(String str2 : Grammers.get(str1)){
					if(str2.startsWith(str1 + " ")){
						list2.add(str2.split(str1 + " ")[1] + " " + str1 + "2");
					}else{
						list1.add(str2);
					}
				}
				list.add(str1 + "1 " + str1 + "2");
				if(!list2.contains("empty")){
					list2.add("empty");
				}
				Grammers.remove(str1);
				Grammers.put(str1, list);
				Grammers.put(str1 + "1", list1);
				Grammers.put(str1 + "2", list2);
				System.out.println(str1);
			}
			System.out.println("-----------------------------Recursion--------------------------");

			System.out.println("_____________________Reduce_________________________");
			//消除公共左因子
			boolean unfinished = true;
			int counter = 0;
			while(true){
				unfinished = false;
				String handlingStr = null;
				String longest = null;
				for(String str1 : Grammers.keySet()){
					longest = FindTheLongestCommonHead(Grammers.get(str1));
					if(longest != null){
						longest = longest.trim();
						unfinished = true;
						handlingStr = str1;
						break;
					}else{
						continue;
					}
				}
				if(!unfinished){
					break;
				}
				counter ++;
				System.out.println(handlingStr + " " + longest);
				List<String> listO = Grammers.get(handlingStr);
				List<String> listN = new ArrayList<String>();
				List<String> listON = new ArrayList<String>();
				for(String thestr : listO){
					if(thestr.startsWith(longest)){
						if(thestr.equals(longest)
								&& !listN.contains("empty")){
							listN.add("empty");
						}else{
							listN.add(thestr.split(Parser.Turn2Reg(longest))[1].trim());
						}
						thestr = longest.trim() + " " + handlingStr.trim() + "_" + counter;
					}else{
						listON.add(thestr);
					}
				}
				listON.add(longest.trim() + " " + handlingStr.trim()  + "_" + counter);
				Grammers.remove(handlingStr, listO);
				Grammers.put(handlingStr, listON);
				Grammers.put(handlingStr.trim()  + "_" + counter, listN);
				System.out.println(handlingStr.trim() +  "  " + longest.trim());
			}
			System.out.println("_____________________Reduce_________________________");
			for(String str1 : Grammers.keySet()){
				System.out.println(str1 + ":");
				for(String str2 : Grammers.get(str1)){
					System.out.println("\t\t\t\t" + str2);
				}
			}
			System.out.println("_________________GetGrammer________________________");

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得Select集合
	 */
	public boolean JudgeLL1(){
		for(String key : Grammers.keySet()){
			List<String> list = Grammers.get(key);
			for(String str1 : list){
				for(String str2 : list){
					if(!str1.equals(str2)
							&& Firsts.containsKey(str1)
							&& Firsts.containsKey(str2)){
						for(String end1 : Firsts.get(str1)){
							for(String end2 : Firsts.get(str2))
								if(end1.equals(end2)){
									LL1checked = true;
									return false;
								}
						}
					}
				}
			}
		}
		for(String key : Firsts.keySet()) {
			if (Firsts.get(key).contains("empty")) {
				for (String str1 : Firsts.get(key)) {
					for (String str2 : Follows.get(key)) {
						if (str1.equals(str2)) {
							LL1checked = true;
							return false;
						}
					}
				}
			}
		}
		isLL1 = true;
		LL1checked = true;
		return true;
	}

	/**
	 * 获得First集合
	 */
	public void GetFirst(Boolean show){
		System.out.println("--------------GetFirst-----------------");
		Firsts = new HashMap<String, List<String>>();
		Set<String> lefts = Grammers.keySet();
		for(String left : lefts){
			Firsts.put(left, First(left));
			if(show){
				System.out.println(left+ " :");
				System.out.println("\t\t\t\t" + Firsts.get(left));
			}
		}
		System.out.println("--------------GetFirst-----------------");
	}


	/**
	 * 获得Follow集合
	 */
	public void GetFollow(Boolean show){
		System.out.println("--------------GetFollow-----------------");
		Follows = new HashMap<String, List<String>>();
		for(String key : Grammers.keySet()){
			Follows.put(key, Follow(key));
			if(show){
				System.out.println(key+ " :");
				System.out.println("\t\t\t\t" + Follows.get(key));
			}
		}
		System.out.println("--------------GetFollow-----------------");
	}

	/**
	 * 获取分析表
	 */
	public void GetTable(){
		Table = new HashMap<String, Map<String, List<String>>>();
		Ends = new ArrayList<String>();
		System.out.println("--------------GetTable-----------------");
		for(String key : Grammers.keySet()){
			for(String str : Grammers.get(key)){
				String[] units= MySplit(str);
				for(String unit : units){
					if(!Grammers.containsKey(unit)
						&& !Ends.contains(unit)){
						Ends.add(unit);
						if(unit.isEmpty()){
							System.out.println(str + "  !empty from :!" + key);
						}
					}
				}
			}
		}
		for(String key : Grammers.keySet()){
			Map<String, List<String>> cols = new HashMap<String, List<String>>();
			for(String end : Ends){
				cols.put(end,  GetCol(key, end));
				Table.put(key, cols);
			}
		}
		for(String row : Table.keySet()){
			System.out.println(row + " :::");
			for(String col : Table.get(row).keySet()){
				if(!Table.get(row).get(col).isEmpty()){
					System.out.println("\t\t" + col + "::");
					if(Table.get(row).get(col).size() > 1)
					System.out.println("!!!!!!!!!!!!Crash, maybe this is not LL(1)Grammer!!!!!!!!!!!!!!");
					for (String str : Table.get(row).get(col)){
						System.out.println("\t\t\t\t" + str + "\t" + Firsts.get(str));
					}
				}
			}
		}
		System.out.println("--------------GetTable-----------------");
	}

	//endregion

	//region 私有方法

	/**
	 * 获取LL(1)分析表的行
	 * @param row
	 * @param col
	 * @return
	 */
	private List<String> GetCol(String row, String col){
		List<String> list = new ArrayList<String>();
		for(String str : Grammers.get(row)){
			if(First(str).contains(col)){
				list.add(str);
			}
		}
		return list;
	}

	/**
	 * 非终结符的Follow集合
	 * @param arg
	 * @return
	 */
	private List<String> Follow(String arg){
		List<String> list = null;
		if(Grammers.containsKey(arg)){
			if(Follows.keySet().contains(arg)){
				list = Follows.get(arg);
			}else{
				list = new ArrayList<String>();
				Follows.put(arg, list);
			}
			for(String key : Grammers.keySet()){
				for(String right : Grammers.get(key)){
					String[] strs = null;
					strs = MySplit(right);
					for(int i = 0; i < strs.length; i++){
						if(Grammers.containsKey(strs[i])){
							if(strs[i].equals(arg)){
								if(i == 0){
									if(!list.contains("$")){
									list.add("$");
									}
								}
								if(i == strs.length - 1
										|| First(strs[i]).contains("empty")){
									if(Follows.containsKey(key)){
										ConbList(list, Follows.get(key));
									}else{
										ConbList(list, Follow(key));
									}
								}
								if(i < strs.length - 1){
									List<String> tmpList = First(strs[i + 1]);
									if(tmpList.contains("empty")){
										tmpList.remove("empty");
									}
									ConbList(list, tmpList);
								}
							}
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * 表取并集
	 * @param exiList
	 * @param srcList
	 */
	private void ConbList(List<String> exiList, List<String> srcList){
		for(String str : srcList){
			if(!exiList.contains(str)){
				exiList.add(str);
			}
		}
	}

	/**
	 * 找到最长共有头部
	 * @param srclist
	 * @return
	 */
	private String FindTheLongestCommonHead(List<String> srclist){
		List<String[]> list = new ArrayList<String[]>();
		for (String str2 : srclist) {
			list.add(MySplit(str2));
		}
		int counter = -1;
		String res = null;
		for(String[] strs1 : list){
			for(String[] strs2 : list){
				if(strs1.equals(strs2)){
					continue;
				}
				int tmpInt = IndexOfTheLongestCommonHead(strs1, strs2);
				if(counter < tmpInt){
					counter = tmpInt;
					res = "";
					for(int i = 0; i <= counter; i++){
						res += strs1[i] + " ";
					}
				}
			}
		}
		return res;
	}

	/**
	 * 最长公因子下标
	 * @param strs1
	 * @param strs2
	 * @return
	 */
	private int IndexOfTheLongestCommonHead(String[] strs1, String[] strs2){
		int counter = -1;
		for(int i = 0; i < (strs1.length < strs2.length ? strs1.length : strs2.length); i ++){
			if(strs1[i].equals(strs2[i])){
				counter = i;
			}else{
				break;
			}
		}
		return counter;
	}

	/**
	 * 非终结符或者非终结符的First集合
	 */
	private List<String> First(String arg){
		List<String> list = new ArrayList<>();
		String[] args = MySplit(arg);
		for(String tmpstr : args) {
			if (!Grammers.keySet().contains(args[0])) {
				//终结符操作
				list.add(args[0]);
			} else {
				List<String> rights = Grammers.get(args[0]);
				boolean exiEmpty = false;
				for (String str : rights) {
					List<String> tmp = First(str);
					if (tmp.contains("empty")) {
						exiEmpty = true;
					}
					ConbList(list, tmp);
				}
				if(!exiEmpty){
					break;
				}
			}
		}
		return list;
	}

	private String[] MySplit(String arg){
		String args[] = null;

		if(arg.contains(" ")){
			args = arg.split(" ");
		}else{
			args = new String[1];
			args[0] = arg;
		}

		return args;
	}

	//endregion
}
