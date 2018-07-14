package Compiler;

import jdk.jfr.Description;

public enum TokenType {

	@Description("保留字类型")
	Word,

	@Description("保留字符类型")
	Character,

	@Description("变量类型")
	Identity,

	@Description("数字类型")
	Number,

	@Description("注释类型	")
	Annotation

}
