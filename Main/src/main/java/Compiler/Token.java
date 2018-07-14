package Compiler;

public class Token {

	//region 私有变量

	public TokenType tokenType;
	public String tokenValue;

	//endregion

	//region 构造方法以及重载方法

	Token(String str, TokenType type){
		this.tokenType = type;
		this.tokenValue = str;
	}

	@Override
	public String toString() {
		return tokenType + " : " + tokenValue + "\n";
	}
	//endregion
}
