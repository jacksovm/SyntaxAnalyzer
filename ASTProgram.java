import java.util.ArrayList;

public class ASTProgram extends ASTNode 
{
	ArrayList<ASTStatement> astStat; 
	
	public ASTProgram() 
	{
		astStat = new ArrayList<ASTStatement>();
	}
	public void ConvertToJava()
	{
		for (ASTStatement i : astStat)
		{
			System.out.print((i).toString());
		}
	}
	public String toString()
	{
		for (ASTStatement i : astStat)
		{
			inJava = inJava + getTabs()+(i).toString();
		}
		return inJava;
	}
}
