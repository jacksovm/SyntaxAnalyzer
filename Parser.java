public class Parser extends Object
{

	private Chario chario;
	private Scanner scanner;
	private Token token;	

	public Parser(Chario c, Scanner s)
	{
		chario = c;
		scanner = s;
		this.token = scanner.nextToken();
	}

	public void reset()
	{
		scanner.reset();
		this.token = scanner.nextToken();
	}

	private void accept(int expected, String errorMessage)
	{
		if (this.token.code != expected)
			fatalError(errorMessage);
		this.token = scanner.nextToken();
	}

	private void fatalError(String errorMessage)
	{
		chario.putError(errorMessage);
		throw new RuntimeException("Fatal error");
	}

	public void parse()
	{
		ASTProgram prgm = statement_list();
		prgm.ConvertToJava();
	}

	/*---------------------------------------------------------------
	  statement_list ::= statement { statement }
	----------------------------------------------------------------*/
	private ASTProgram statement_list()
	{
		ASTProgram astProg = new ASTProgram();
		astProg.astStat.add(statement()); 
		
		while(this.token.code != Token.EOF &&
				this.token.code != Token.UNTIL &&
				this.token.code != Token.ENDIF)
		{
			astProg.astStat.add(statement()); 
		}
		return astProg;
	}

	/*---------------------------------------------------------------
	  statement ->  var-decl
					print-statement
	          		if-statement 
		   			loop-statement
		   			assign-statement
	----------------------------------------------------------------*/
	private ASTStatement statement()
	{
		ASTStatement astStat = null;
		
		switch(this.token.code)
		{
		 case Token.DECLARE:
			astStat = var_decl();
			break;
			
		 case Token.PRINT:
			 astStat = print_statement();
			break;
			
		 case Token.IF:
			 astStat = if_statement();
			break;
			
		 case Token.LOOP:
			 astStat = loop_statement();
			break;
			
		 case Token.LET:
			 astStat = assign_statement();
			break;
			
		 case Token.EOF:
			 break;
			 
		default:
			fatalError("Invalid Statement. Token code: " + this.token.code);
		}
		return astStat;
	}

	/*---------------------------------------------------------------
	  var-decl ::= DECLARE var AS type [= expression]
	----------------------------------------------------------------*/
	private ASTVarDeclaration var_decl()
	{
		accept(Token.DECLARE, "'DECLARE' expected");
		
		ASTVarDeclaration vrdc = new ASTVarDeclaration(token.string);
		
		accept(Token.VAR, "'VAR' expected");
		accept(Token.AS, "'AS' expected");
		accept(Token.INTEGER, "'INTEGER' expected");
		
		if(this.token.code == Token.ASSIGN)
		{
			accept(Token.ASSIGN, "'=' expected");
			vrdc.DecAddExpr(expression());
		}
		return vrdc;
		
	}

	/*---------------------------------------------------------------
	  print-statement ::= PRINT expression
	----------------------------------------------------------------*/
	private ASTPrint print_statement()
	{
		ASTPrint astPrint;
		
		accept(Token.PRINT, "'PRINT' expected");
		astPrint = new ASTPrint(expression());
		
		return astPrint;
		
		
	}

	/*---------------------------------------------------------------------------
	  if-statement ::= IF condition THEN statement_list ENDIF
	-----------------------------------------------------------------------------*/
	private ASTIf if_statement()
	{
		ASTIf astIf;
		
		accept(Token.IF, "'IF' expected");
		astIf = new ASTIf(condition());
		
		accept(Token.THEN, "'THEN' expected");
		astIf.addStatement(statement_list());
		
		accept(Token.ENDIF, "'ENDIF expected");
		
		return astIf;
	}
	
	/*-------------------------------------------------------------------
	  loop-statement ::= LOOP statement_list UNTIL condition
	---------------------------------------------------------------------*/
	private ASTLoop loop_statement()
	{
		ASTLoop astLoop;
		
		accept(Token.LOOP, "'LOOP' expected");
		astLoop = new ASTLoop(statement_list());
		
		accept(Token.UNTIL, "'UNTIL' expected");
		astLoop.addCondition(condition());
				
		return astLoop;
	}

	/*---------------------------------------------------------------
	  assign-statement ::= LET var = expression
	----------------------------------------------------------------*/
	private ASTAssign assign_statement()
	{
		accept(Token.LET, "'LET' expected");
		ASTAssign astAssign = new ASTAssign(token.string);
		accept(Token.VAR, "Variable expected");
		accept(Token.ASSIGN, "Assignment '=' expected");
		astAssign.AddExp(expression());
		return astAssign;
	}
	
	/*---------------------------------------------------------------
	  condition ::= expression relop expression
	----------------------------------------------------------------*/
	private ASTCondition condition()
	{
		ASTCondition astCon;
		astCon = new ASTCondition(expression());
		astCon.AddRelop(relop());
		astCon.AddExp(expression());
		return astCon;
	}
	
	/*-----------------------------------------------------------------------------------
	  expression ::= simple-expression | simple-expression ( + | - | * | / | % ) expression 
	------------------------------------------------------------------------------------*/
	private ASTExpression expression()
	{
		ASTExpression astEx;
		ASTSimpleExp simple;
		ASTMathExp astMath;
		
		simple = simple_expression();
		
	
		if(this.token.code == Token.DIV ||
				this.token.code == Token.MINUS ||
				this.token.code == Token.MOD ||
				this.token.code == Token.TIMES ||
				this.token.code == Token.PLUS)
		{
			astMath = new ASTMathExp(token.string);
			astMath.addSimple(simple);
			
			switch (this.token.code)
			{
			case Token.DIV:
				accept(Token.DIV, "'DIV' expected");
				break;
				
			case Token.PLUS:
				accept(Token.PLUS, "'PLUS' expected");
				break;
				
			case Token.MINUS:
				accept(Token.MINUS, "'MINUS' expected");
				break;
				
			case Token.TIMES:
				accept(Token.TIMES, "'TIMES' expected");
				break;
				
			case Token.MOD:
				accept(Token.MOD, "'MOD' expected");
				break;
				
			default:
				fatalError("opperator expected");
			}
			
			if(this.token.code == Token.VAR ||
					this.token.code == Token.NUMBER)
			{
				astMath.AddEx(expression());
			}
			astEx = astMath;
		}
		else
		{
			astEx = simple;
		}
		
		return astEx;
	}

	/*---------------------------------------------------------------
	  simple-expression ::= var | number
	----------------------------------------------------------------*/
	private ASTSimpleExp simple_expression()
	{
		ASTSimpleExp smlex = null;
		if(this.token.code == Token.VAR)
		{
			smlex = new ASTVar(token.string);
			this.token = scanner.nextToken();
		}
		else if (this.token.code == Token.NUMBER)
		{
			smlex = new ASTNum(token.integer);
			this.token = scanner.nextToken();
		}
		else
		{
			fatalError("'VAR' or 'NUMBER' expected");
		}
		return smlex;
	}
	
	/*---------------------------------------------------------------
	  relop ::= < [>|=] | > [ = ] | = =
	----------------------------------------------------------------*/
	private ASTRelop relop()
	{
		ASTRelop astRlp = new ASTRelop();
		
		switch (this.token.code)
		{
		case Token.LT:
			astRlp.addRelop("<");
			accept(Token.LT, "'<' expected");
			break;
			
		case Token.GT:
			astRlp.addRelop(">");
			accept(Token.GT, "'>' expected");
			break;
			
		case Token.EQ:
			astRlp.addRelop("==");
			accept(Token.EQ, "'==' expected");
			break;
			
		case Token.NOTEQ:
			astRlp.addRelop("<>");
			accept(Token.NOTEQ, "'<>' expected");
			break;
			
		case Token.LTE:
			astRlp.addRelop("<=");
			accept(Token.LTE, "'<=' expected");
			break;
			
		case Token.GTE:
			astRlp.addRelop(">=");
			accept(Token.GTE, "'>=' expected");
			break;
			
		default:
			fatalError("Error in relop");
		}
		return astRlp;
	}
}
