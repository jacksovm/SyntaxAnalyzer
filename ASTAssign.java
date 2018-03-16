
public class ASTAssign extends ASTStatement
{
	ASTExpression expr;
	String varName;
	
	public ASTAssign(String s) 
	{
		varName = s.toLowerCase();
	}
	public void AddExp(ASTExpression e) 
	{
		expr = e;
	}
	public String toString()
	{
		return varName + " = " + expr.toString() + ";\n";
	}
}
