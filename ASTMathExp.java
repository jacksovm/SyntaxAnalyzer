public class ASTMathExp extends ASTExpression
{
	String op;
	ASTSimpleExp simEx;
	ASTExpression expr;
	
	public ASTMathExp(String s) 
	{
		op = s;
	}
	public void addSimple(ASTSimpleExp s) 
	{
		simEx = s;
		
	}
	public void AddEx(ASTExpression e) 
	{
		expr = e;
	}
	public String toString()
	{
		return simEx.toString() + " " + op + " " + expr.toString();
	}
}