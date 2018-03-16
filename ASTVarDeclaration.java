public class  ASTVarDeclaration extends ASTStatement
{
	String var;
	ASTExpression expr;
	
	public ASTVarDeclaration(String s) 
	{
		var = s.toLowerCase();
	}

	public void DecAddExpr(ASTExpression e) 
	{
		expr = e;
	}
	public String toString()
	{
		if (expr == null)
			return "int " + var + ";\n";
		else
			return "int " + var + " = " + expr.toString() + ";\n";	
	}

}
