package Compiler;

import jdk.jfr.Description;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	//region 全局变量

	@Description("保留字")
	public static final String[] ReservedWord =
			{"if", "else", "int", "return", "void", "while"};

	@Description("保留字符")
	public static final String[] ReservedCharacter =
			{"+", "-", "*", "/", "<=", "<", ">=", ">", "==", "!=", "=",
				";", ",", "(", ")", "[", "]", "{", "}", "/*","*/"};

	@Description("空白符")
	public static final String[] Blank =
			{" ", "\t", "\n"};

	@Description("正则需转义字符")
	public static final String[] ExcArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };

	@Description("Token字符串表")
	private List StrTokens = null;

	@Description("Token表")
	private List<Token> Tokens = null;

	//endregion

	//region Main


	//endregion

	//region 公共方法

	public List<Token> GetTokens(){
		return this.Tokens;
	}

	/**
	 * 公共加载开始分析
	 * @param arg
	 */
	public void ParseFromFile(String arg) {
		File file = null;
		try {
			file = new File(arg);
			if(!file.exists()){
				throw new FileNotFoundException("文件不存在");
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String str = "";
			String sourceCode = "";
			while((str = br.readLine()) != null){
				sourceCode += str + "\n";
			}
			br.close();
			System.out.println("SC:\n" + sourceCode);
			System.out.println("starting......\n");
			StartParse(sourceCode);
			System.out.println(Tokens);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//endregion

	//region 私有方法

	/**
	 * @Description:词法分析开始
	 * @param str
	 */
	private void StartParse(String str){
		StrTokens = new ArrayList<String>();
		StrTokens.add(str);

		SeparateByStrs(ReservedCharacter, StrTokens);
		SeparateByStrs(Blank, StrTokens);
		ClearBlank(StrTokens);
		FixStrs(StrTokens);
		try {
			Tokens = Strs2Tokens(StrTokens);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:字符串转到Token类
	 * @param strs
	 * @return
	 * @throws Exception
	 */
	private List<Token> Strs2Tokens(List<String> strs)throws Exception{
		List<Token> tokens = new ArrayList<Token>();

		for (String str : strs) {
			if(isIn(ReservedCharacter, str)){
				tokens.add(new Token(str, TokenType.Character));
				continue;
			}
			if(isIn(ReservedWord, str)){
				tokens.add(new Token(str, TokenType.Word));
				continue;
			}
			if(str.matches("[0-9]+")){
				tokens.add(new Token(str, TokenType.Number));
				continue;
			}
			if(str.matches("[a-zA-Z]+")){
				tokens.add(new Token(str, TokenType.Identity));
				continue;
			}
			if(str.startsWith("/*")
					|| str.endsWith("*/")){
				tokens.add(new Token(str, TokenType.Annotation));
				continue;
			}
			throw new Exception("不能识别不在字符集中的字符:" + str);
		}

		return tokens;
	}

	/**
	 * @Description:修正
	 * @param list
	 */
	private void FixStrs(List<String> list){
			Boolean isAnnotation = false;

			for(int i = 0; i < list.size() - 1;){
				String str = list.get(i);
				String next = list.get(i + 1);

				if(str.equals("/")
						&& next.equals("*")){
					isAnnotation = true;
				}
				if(isAnnotation
						||((str.equals("<")
							|| str.equals(">")
							|| str.equals("=")
							|| str.equals("!"))
							&& next.equals("="))){
					list.remove(i);
					list.remove(i);
					list.add(i, str + next);
					i--;
				}
				if(str.endsWith("*")
						&& next.equals("/")){
					isAnnotation = false;
				}
				i++;
			}
		}

	/**
	 * @Description:字符串数组strs中是否存在字符串str
	 * @param strs
	 * @param str
	 * @return
	 */
	private boolean isIn(String[] strs, String str){
		for (String tmp : strs) {
			if (str.equals(tmp)){
				return true;
			}
		}
		return false;
	}

	/**
	 * @Description:去除注释
	 * @param str
	 * @return a string gotten rid of annotation like \/**\/
	 */
	private String ClearAnnotation(String str){
		return str.replaceAll("\\/\\*(.*?)\\*\\/", "\0");
	}

	/**
	 * @Description:去除空白符
	 * @param list
	 */
	private void ClearBlank(List<String> list){
			for (String blank : Blank) {
			while(list.contains(blank)){
				list.remove(blank);
			}
			}
		}

	/**
	 * @Description:按字符串数组切分
	 * @param strArr
	 * @param tokens
	 */
	private void SeparateByStrs(String[] strArr, List<String> tokens){
			for (String str: strArr) {
				SeparateByStr(str, tokens);
			}
		}

	/**
	 * @Description:按字符串切分
	 * @param target
	 * @param list
	 */
	private void SeparateByStr(String target, List<String> list){
			for(int i = 0; i < list.size();){
				String str = list.get(i);
				list.remove(i);
				String[] strs = MySplit(Turn2Reg(target), str,null);
				int offCounter = 0;

				for (int j = 0; j < strs.length; j++) {
					list.add(i + offCounter, strs[j]);
					offCounter++;
				}

				i += offCounter;
			}
		}

		/**
		 * @Description: 转义正则特殊字符 （$()*+.[]?\^{},|）
		 * @param word
		 * @return
		 */
		public static String Turn2Reg(String word) {
			if (word != null
					&&!word.isEmpty()) {
				for (String key : ExcArr) {
					if (word.contains(key)) {
						word = word.replace(key, "\\" + key);
					}
				}
			}

			return word;
		}

	/**
	 * @Description:保留分隔符分割
	 * @param regex
	 * @param str
	 * @param list
	 * @return
	 */
		private String[] MySplit(String regex, String str, List<String> list){
			if(list == null){
				list = new ArrayList<String>();
			}

			Matcher m = Pattern.compile("([\\s\\S]*?)(" + regex + ")([\\s\\S]*)").matcher(str);
			if(m.find()){
				list.add(m.group(1));
				list.add(m.group(2));
				MySplit(regex, m.group(3), list);
			}else{
				list.add(str);
			}
			list.remove("");
			String[] result = new String[list.size()];
			return list.toArray(result);
		}
	//endregion

}
