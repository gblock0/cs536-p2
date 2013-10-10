/******************************
 * This is the test driver for cs536 project 2 (fall 2013)
 * It opens a test file on the command line,
 * then calls the scanner generated by JLex to get the next token.
 * The token is displayed (for testing/debugging purposes) and the process is repeated
 * until the end of file token is reached.
 */


import java.io.*;
import java.util.HashMap;

import java_cup.runtime.*;

public class P2 {

  public static void
  main(String args[]) throws java.io.IOException {

	if (args.length != 1) {
       		System.out.println(
			"Error: Input file must be named on command line." );
		System.exit(-1);
    	}

	
    	java.io.FileInputStream yyin = null; 

    	try {
    		yyin = new java.io.FileInputStream(args[0]);
    	} catch (FileNotFoundException notFound){
       		System.out.println ("Error: unable to open input file."); 
		System.exit(-1);
    	}

    // lex is a JLex-generated scanner that will read from yyon
    	Yylex lex = new Yylex(yyin);	
    	System.out.println("Coded by Greg Bammel (906-337-7890) and Greg Block (905-814-9023)");
    	System.out.print ("Begin test of CSX scanner.");
    	System.out.println (" Scanning file "+
        		args[0]+ ":");




	Symbol token = lex.yylex();

/**********************
 * Extend the code in the switch to display all the valid CSX tokens.
 * 
 */
	
	
	
	while ( token.sym != sym.EOF ) {

    		System.out.print( ((CSXToken) token.value).linenum + ":"
				+ ((CSXToken) token.value).colnum + " ");

		switch (token.sym) {
		case sym.rw_BOOL:
		case sym.rw_BREAK:
		case sym.rw_CHAR:
		case sym.rw_CLASS:
		case sym.rw_CONST:
		case sym.rw_CONTINUE:
		case sym.rw_ELSE:
		case sym.rw_FALSE:
		case sym.rw_IF:
		case sym.rw_INT:
		case sym.rw_PRINT:
		case sym.rw_READ:
		case sym.rw_RETURN:
		case sym.rw_TRUE:
		case sym.rw_VOID:
		case sym.rw_WHILE:
		System.out.println("\t"  + ((CSXReservedWord) token.value).r_Word);
		break;	
		case sym.SEMI:
		case sym.COR:
		case sym.EOF:
		case sym.LBRACKET:
		case sym.GT:
		case sym.CAND:
		case sym.LT:
		case sym.MINUS:
		case sym.TIMES:
		case sym.COMMA:
		case sym.LPAREN:
		case sym.RPAREN:
		case sym.GEQ:
		case sym.SLASH:
		case sym.PLUS:
		case sym.LBRACE:
		case sym.RBRACE:
		case sym.LEQ:
		case sym.NOTEQ:
		case sym.ASG:
		case sym.RBRACKET:
		case sym.EQ:
		case sym.NOT:
		case sym.COLON:
		case sym.INCREMENT:
		case sym.DECREMENT:
			System.out.println("\t" + ((CSXOtherTokensToken) token.value).tokeVal);
			break;
		  case sym.INTLIT:
			  	int text = ((CSXIntLitToken) token.value).intValue;
			  	String temp = "";
			  	if(text < 0){
			  		temp = temp + text;
			  		temp = temp.replace("-", "~");
			  	}else
			  	{
			  		temp = text + "";
			  	}
    			System.out.println("\tInteger literal (" +
				temp + ")");
			break;
		  case sym.STRLIT:
			  System.out.println("\tString literal (" + ((CSXStringLitToken) token.value).stringText + ")");
			  break;
		  case sym.IDENTIFIER:
			  System.out.println("\tIdentifier ("  + ((CSXIdentifierToken) token.value).identifierText + ")");
			  break;

		  case sym.CHARLIT:
			  System.out.println("\tChar literal (" + ((CSXStringLitToken) token.value).stringText + ")");
			  break;
		  case -1:
			  System.out.println("\t**ERROR: invalid token (" + ((CSXInvalidToken) token.value).stringText + ")");
			  break;
		  default:
			throw new RuntimeException();
		}

		
			
		
		token = lex.yylex(); // get next token
	}
    	System.out.println("End test of CSX scanner.");
  }

}
