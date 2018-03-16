
public class ASTPrint extends ASTStatement
{
	ASTExpression expr;
	public ASTPrint(ASTExpression e) 
	{
		expr = e;
	}
	public String toString()
	{
		return "System.out.println(" + expr.toString() + ");\n";
	}
}
