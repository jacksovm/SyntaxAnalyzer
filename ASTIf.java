
public class ASTIf extends ASTStatement
{
	ASTCondition con;
	ASTProgram prog;
	
	public ASTIf(ASTCondition c) 
	{
		con = c;		
	}
	public void addStatement(ASTProgram p) 
	{
		prog = p;
	}
	public String toString()
	{
		inJava = "if(" + con.toString() + ")\n" + getTabs() +"{\n";
		tablvl++;
		inJava = inJava + prog.toString();
		tablvl--;
		return inJava + getTabs() +"}\n";
	}
}
