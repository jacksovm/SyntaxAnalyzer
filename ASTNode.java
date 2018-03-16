abstract class ASTNode
{
	static int tablvl = 0;
	public String inJava = "";
	
	public ASTNode() {}
	public String toString(){return "";}
	
	public String getTabs()
	{
		String tabs = "";
		if(tablvl > 0)
		{
			for(int i = 0; i < tablvl; i++)
			{
				tabs += "    ";
			}
		}
		return tabs;
	}
}
