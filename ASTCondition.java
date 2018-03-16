
public class ASTCondition extends ASTNode
{
	ASTExpression expr1, expr2;
	ASTRelop relop;

	public ASTCondition(ASTExpression e) 
	{
		expr1 = e;
	}
	public void AddRelop(ASTRelop r) 
	{
		relop = r;
	}
	public void AddExp(ASTExpression e) 
	{
		expr2 = e;	
	}
	public String toString()
	{
		return expr1.toString() + " " + relop.toString() + " " + expr2.toString();
	}
}
