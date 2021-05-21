package AnalisadorLexico;

public class LexParser {

	private LexScanner scanner; // analizador lexico
	private Token token;		//token atual
	
	/**
	 * OBS.: A Classe LexParser recebe o analisador lexico como parametro
	 * no contrutor pois a cada procedimento invoca-o sob demanda.
	 * 
	 */
	
	public LexParser(LexScanner scanner) {
		this.scanner = scanner;
	}
	
	public void E() {
		T();
		El();
	}
	
	public void El() {
		token = scanner.nextToken();
		if (token != null) {
			OP();
			T();
			El();
			
			/*if(token.getType() == Token.TK_OPERATOR) {
				throw new LexSyntaxException("Operator Expected");
			}*/
		}
	}
	
	public void T() {
		token = scanner.nextToken();
		if (token.getType() != Token.TK_IDENTIFIER && token.getType() != Token.TK_NUMBER) {
			throw new LexSyntaxException("ID or NUMBER Expected!, found "+Token.TK_TEXT[token.getType()]+" ("+token.getText()+") at LINE "+token.getLine()+" and COLUMN "+token.getColumn());
		}
	}
	
	public void OP() {
		if (token.getType() != Token.TK_OPERATOR) {
			throw new LexSyntaxException("Operator expeted, found "+Token.TK_TEXT[token.getType()]+"("+token.getText()+") at LINE "+token.getLine()+" and COLUMN "+token.getColumn());
		}
	}
	
	
	
	
}
