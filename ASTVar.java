
public class ASTVar extends ASTSimpleExp
{
	String expr;

	public ASTVar(String s) 
	{
		expr = s.toLowerCase();
	}
	public String toString()
	{
		return expr;
	}
}
