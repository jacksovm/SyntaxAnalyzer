
public class ASTNum extends ASTSimpleExp
{
	String expr;
	public ASTNum(Integer i) 
	{
		expr = i.toString();
	}
	public String toString()
	{
		return expr;
	}

}
