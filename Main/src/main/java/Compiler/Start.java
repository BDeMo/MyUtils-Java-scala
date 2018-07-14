package Compiler;

/**
 * @auther SamJ
 * @create 2018 - 06 - 03 12:57
 */
public class Start {
	public static void main(String[] args) {
		Analyzer anlyr = new Analyzer();
		Parser parser = new Parser();
		parser.ParseFromFile(args[0]);
		anlyr.GetGrammer(args[1]);
		anlyr.GetFirst(true);
		anlyr.GetFollow(false);
		anlyr.GetTable();
		anlyr.SetToken(parser.GetTokens());
		System.out.println("is LL(1) : " + anlyr.JudgeLL1());
		anlyr.FitTokens("B");
	}
}
