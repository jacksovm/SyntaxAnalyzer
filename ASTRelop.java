
public class ASTRelop extends ASTNode
{
	String relop;
	
	public ASTRelop() {}
	public void addRelop(String s)
	{
		relop = s;
	}
	public String toString()
	{
		return relop;
	}
}
