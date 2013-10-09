/*  This is a JLex specification for a small subset of CSX tokens.
    Expand it to handle all CSX tokens as part of your solution for project 2 */
import java_cup.runtime.*;
import java.util.HashMap;
class CSXToken {
	int linenum;
	int colnum;
	CSXToken(int line,int col){
		linenum=line;colnum=col;};
}
class CSXComment extends CSXToken{
	String comment;
	CSXComment(String val,int line,int col){
		super(line,col);comment=val;}; 
}
class CSXBlockComment extends CSXToken{
	String blockComment;
	CSXBlockComment(String val,int line,int col){
		super(line,col);blockComment=val;}; 
}
class CSXReservedWords{
	HashMap<String, Integer> rWords;
	CSXReservedWords(){
		String r_word;
		rWords = new HashMap<String, Integer>(){{
			put("bool", sym.rw_BOOL);
			put("break", sym.rw_BREAK);
			put("char", sym.rw_CHAR);
			put("class", sym.rw_CLASS);
			put("const", sym.rw_CONST);
			put("continue", sym.rw_CONTINUE);
			put("else", sym.rw_ELSE);
			put("false", sym.rw_FALSE);
			put("if", sym.rw_IF);
			put("int", sym.rw_INT);
			put("print", sym.rw_PRINT);
			put("read", sym.rw_READ);
			put("return", sym.rw_RETURN);
			put("true", sym.rw_TRUE);
			put("void", sym.rw_VOID);
			put("while", sym.rw_WHILE);
		}};
		}
	}
class CSXOtherTokensTokens{
	HashMap<String, Integer> otherTokens;
	CSXOtherTokensTokens(){
		otherTokens = new HashMap<String, Integer>(){{
			put(";", sym.SEMI);
			put("[", sym.LBRACKET);
			put("GT", sym.GT);
			put("LT", sym.LT);
			put("-", sym.MINUS);
			put("*", sym.TIMES);
			put(",", sym.COMMA);
			put("(", sym.LPAREN);
			put(")", sym.RPAREN);
			put(">=", sym.GEQ);
			put("/", sym.SLASH);
			put("+", sym.PLUS);
			put("}", sym.LBRACE);
			put("{", sym.RBRACE);
			put("<=", sym.LEQ);
			put("!=", sym.NOTEQ);		
			put("]", sym.RBRACKET);
			put("==", sym.EQ);
			put("!", sym.NOT);
			put(":", sym.COLON);
			put("++", sym.INCREMENT);
			put("--", sym.DECREMENT);	
			put("&&", sym.CAND);
			put("||", sym.COR);
			put("=", sym.ASG);
		}};
		}
}
class CSXOtherTokensToken extends CSXToken { 
	String tokeVal;
	CSXOtherTokensToken(String val,int line,int col){
		super(line,col);tokeVal=val;}; 
}
class CSXIntLitToken extends CSXToken {
	int intValue;
	CSXIntLitToken(int val,int line,int col){
		super(line,col);intValue=val;};
}
class CSXIdentifierToken extends CSXToken {
	String identifierText;
	CSXIdentifierToken(String text,int line,int col){
		super(line,col);identifierText=text;};
}
class CSXCharLitToken extends CSXToken {
	char charValue;
	CSXCharLitToken(char val,int line,int col){
		super(line,col);charValue=val;};
}
class CSXReservedWord extends CSXToken {
	String r_Word;
	CSXReservedWord(String val,int line,int col){
		super(line,col);r_Word=val;};
}
class CSXStringLitToken extends CSXToken {
	String stringText; // Full text of string literal,
                          //  including quotes & escapes
	CSXStringLitToken(String text,int line,int col){
		super(line,col);
		stringText=text;
	};
}
// This class is used to track line and column numbers
// Please feel free to change or extend it
class Pos {
	static int  linenum = 1; /* maintain this as line number current
                                 token was scanned on */
	static int  colnum = 1; /* maintain this as column number current
                                 token began at */
	static int  line = 1; /* maintain this as line number after
					scanning current token  */
	static int  col = 1; /* maintain this as column number after
					scanning current token  */
	static void setpos() { // set starting position for current token
		linenum = line;
		colnum = col;
	}
}


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final char YYEOF = '\uFFFF';
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private char yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_start () {
		++yy_buffer_start;
	}
	private void yy_pushback () {
		--yy_buffer_end;
	}
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int yy_acpt[] = {
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_END,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NOT_ACCEPT
	};
	private int yy_cmap[] = {
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 1, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		2, 3, 4, 5, 0, 0, 6, 0,
		7, 7, 8, 9, 8, 10, 0, 11,
		12, 12, 12, 12, 12, 12, 12, 12,
		12, 12, 8, 8, 3, 13, 3, 0,
		0, 14, 14, 14, 14, 14, 14, 14,
		14, 14, 14, 14, 14, 14, 14, 14,
		14, 14, 14, 14, 14, 14, 14, 14,
		14, 14, 14, 8, 0, 8, 0, 0,
		0, 14, 14, 14, 14, 14, 14, 14,
		14, 14, 14, 14, 14, 14, 14, 14,
		14, 14, 14, 14, 14, 14, 14, 14,
		14, 14, 14, 8, 15, 8, 0, 0
		
	};
	private int yy_rmap[] = {
		0, 1, 1, 2, 3, 4, 1, 5,
		1, 1, 5, 4, 6, 1, 7, 8,
		9, 10, 11, 12, 13, 14 
	};
	private int yy_nxt[][] = {
		{ -1, 1, 2, 3, 10, 12, 14, 11,
			13, 15, 17, 19, 4, 3, 5, 16
			
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1
			
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, 6, -1, -1
			
		},
		{ -1, -1, -1, -1, -1, -1, -1, 5,
			-1, -1, -1, -1, 4, -1, 5, -1
			
		},
		{ -1, -1, -1, -1, -1, -1, -1, 5,
			-1, -1, -1, -1, 5, -1, 5, -1
			
		},
		{ 10, 10, 10, 10, 7, 10, 10, 10,
			10, 10, 10, 10, 10, 10, 10, 10
			
		},
		{ -1, -1, -1, -1, -1, 18, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1
			
		},
		{ -1, -1, -1, -1, -1, -1, 6, -1,
			-1, -1, -1, -1, -1, -1, -1, -1
			
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, 6, -1, -1, -1, -1, -1, -1
			
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, 6
			
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, 6, -1, -1, -1, -1, -1
			
		},
		{ 18, 18, 18, 18, 18, 21, 18, 18,
			18, 18, 18, 18, 18, 18, 18, 18
			
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, 20, -1, -1, -1, -1
			
		},
		{ 20, 8, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20
			
		},
		{ -1, -1, -1, -1, -1, 9, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1
			
		}
	};
	public Symbol yylex ()
		throws java.io.IOException {
		char yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			if (YYEOF != yy_lookahead) {
				yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YYEOF == yy_lookahead && true == yy_initial) {

return new Symbol(sym.EOF, new  CSXToken(0,0));
				}
				else if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_to_mark();
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_pushback();
					}
					if (0 != (YY_START & yy_anchor)) {
						yy_move_start();
					}
					switch (yy_last_accept_state) {
					case 1:
						{Pos.line +=1; Pos.col = 1;}
					case -2:
						break;
					case 2:
						{Pos.col +=1;}
					case -3:
						break;
					case 3:
						{
			Pos.setpos(); 
		  Pos.col += yytext().length();
		   CSXOtherTokensTokens oToken = new CSXOtherTokensTokens();
		   String text = yytext();
		   return new Symbol(oToken.otherTokens.get(text),
				new CSXOtherTokensToken(yytext(), Pos.linenum, Pos.colnum));  		
		  }
					case -4:
						break;
					case 4:
						{// This def doesn't check for overflow -- be sure to update it
		  Pos.setpos(); Pos.col += yytext().length();
		  return new Symbol(sym.INTLIT,
				new CSXIntLitToken(
					new Integer(yytext()).intValue(),
		                    	Pos.linenum,Pos.colnum));}
					case -5:
						break;
					case 5:
						{// This def doesn't check for overflow -- be sure to update it
		  Pos.setpos(); Pos.col += yytext().length();
		  	String yytextVal = yytext();
		  	CSXReservedWords rWords = new CSXReservedWords();
		  	if(rWords.rWords.get(yytextVal) != null){
		  		return new Symbol(rWords.rWords.get(yytextVal),
				new CSXReservedWord(yytext(), Pos.linenum, Pos.colnum));
		  	} 			  	
		  	return new Symbol(sym.IDENTIFIER,
			new CSXIdentifierToken(yytext(), Pos.linenum, Pos.colnum));		  		
			}
					case -6:
						break;
					case 6:
						{
			Pos.setpos(); 
		  Pos.col += yytext().length();
		   CSXOtherTokensTokens oToken = new CSXOtherTokensTokens();
		   String text = yytext();
		   return new Symbol(oToken.otherTokens.get(text),
				new CSXOtherTokensToken(yytext(), Pos.linenum, Pos.colnum));  		
		  }
					case -7:
						break;
					case 7:
						{
 Pos.setpos(); Pos.col += yytext().length();
 return new Symbol(sym.STRLIT,
				new CSXStringLitToken(yytext(),Pos.linenum,Pos.colnum));
}
					case -8:
						break;
					case 8:
						{  Pos.setpos(); 
			Pos.line += 1; 
			Pos.col = 1;}
					case -9:
						break;
					case 9:
						{ 
			Pos.setpos(); 
			Pos.col =1; 	
			Pos.line = yytext().split("\n").length;
}
					case -10:
						break;
					case 11:
						{
			Pos.setpos(); 
		  Pos.col += yytext().length();
		   CSXOtherTokensTokens oToken = new CSXOtherTokensTokens();
		   String text = yytext();
		   return new Symbol(oToken.otherTokens.get(text),
				new CSXOtherTokensToken(yytext(), Pos.linenum, Pos.colnum));  		
		  }
					case -11:
						break;
					case 13:
						{
			Pos.setpos(); 
		  Pos.col += yytext().length();
		   CSXOtherTokensTokens oToken = new CSXOtherTokensTokens();
		   String text = yytext();
		   return new Symbol(oToken.otherTokens.get(text),
				new CSXOtherTokensToken(yytext(), Pos.linenum, Pos.colnum));  		
		  }
					case -12:
						break;
					case 15:
						{
			Pos.setpos(); 
		  Pos.col += yytext().length();
		   CSXOtherTokensTokens oToken = new CSXOtherTokensTokens();
		   String text = yytext();
		   return new Symbol(oToken.otherTokens.get(text),
				new CSXOtherTokensToken(yytext(), Pos.linenum, Pos.colnum));  		
		  }
					case -13:
						break;
					case 17:
						{
			Pos.setpos(); 
		  Pos.col += yytext().length();
		   CSXOtherTokensTokens oToken = new CSXOtherTokensTokens();
		   String text = yytext();
		   return new Symbol(oToken.otherTokens.get(text),
				new CSXOtherTokensToken(yytext(), Pos.linenum, Pos.colnum));  		
		  }
					case -14:
						break;
					case 19:
						{
			Pos.setpos(); 
		  Pos.col += yytext().length();
		   CSXOtherTokensTokens oToken = new CSXOtherTokensTokens();
		   String text = yytext();
		   return new Symbol(oToken.otherTokens.get(text),
				new CSXOtherTokensToken(yytext(), Pos.linenum, Pos.colnum));  		
		  }
					case -15:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
					}
				}
			}
		}
	}
}
