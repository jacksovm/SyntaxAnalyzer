public class ASTLoop extends ASTStatement
{
	ASTCondition con;
	ASTProgram prog;
	
	public ASTLoop(ASTProgram p) 
	{
		prog = p;
		
	}
	public void addCondition(ASTCondition c) 
	{
		con = c;
	}
	public String toString()
	{
		inJava = "do\n" + getTabs() + "{\n"; 
		tablvl++;
		inJava = inJava + prog.toString();
		tablvl--;
		return  inJava + getTabs() + "}\nwhile (" + con.toString() + ");\n";
	}
}
