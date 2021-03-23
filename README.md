# Compiladores Implementacao em Java - Analisador Léxico

### Diagrama de transição do Automato Não Deterministico do Analizador Léxico
![](https://github.com/enivaldoqueiroz/Compiladores-Implementacao-em-Java/blob/main/src/Imagens/IMG002.png)

### Descrições dos Tokens

	IDENTIFICADORES
	(a..z)(A...Z|0..9|a...z)*
	NUMEROS
	(0...9)+
	PONTUACAO - COMENTARIO
	@
	OP_RELACIONAL
	> | >= | < | == | !=
	OP_ATRIBUICAO
	=

	Obs.:
	a..z	-> Intervalo da letra 'a' ate a letra 'z'
	*		-> 0 ou mais repetições
	+		-> 1 ou mais repetições	

### Metodo principal da classe LexScanner. Implementação baseada na transição de estado

	public Token nextToken() {
		char currentChar;
		Token token;
		String term="";
		//Se for fim de arquivo 
		if (isEOF()) {
			return null;
		}
		
		estado = 0;
		while (true) {
			currentChar = nextChar();
			
			
			switch(estado) {
			case 0:
												
				if(isChar(currentChar)) {
					term += currentChar;
					estado = 1;
					
				}
				else if (isDigit(currentChar)) {
					estado = 2;
					term += currentChar;
				}

				else if (isSpace(currentChar)) {
					estado = 0;
					
				}
				else if (isOperator(currentChar)) {
					term += currentChar;
					token = new Token();
					token.setType(Token.TK_OPERATOR);
					token.setText(term);
					return token;
				}
				else {
					throw new LexException("Unrecognized SYMBOL - SÍMBOLO não reconhecido");
				}
				break;
			case 1:
				if(isChar(currentChar) || isDigit(currentChar)) {
					estado = 1;
					term += currentChar;
				}
				else if (isSpace(currentChar) || isOperator(currentChar) || isEOF(currentChar)){
					if (!isEOF(currentChar))
						back();
					token = new Token();
					token.setType(Token.TK_IDENTIFIER);
					token.setText(term);
					return token;
				}
				else {
					throw new LexException("Malformed Identifier - Identificador Malformado");
				}
				break;
			case 2:											
				if (isDigit(currentChar) || currentChar == '.') {
					estado = 2;
					term += currentChar;
				}
					
				else if (!isChar(currentChar) || isEOF(currentChar)) {
						if (!isEOF(currentChar))
							back();
						token = new Token();
						token.setType(Token.TK_NUMBER);
						token.setText(term);
						return token;
				}
				else {
						throw new LexException("Unrecognized Number");
				}
				break;
				
### Funções utiltarias da classe LexScanner para reconhecimento dos caracterias especiais 

	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}
	
	private boolean isChar(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}
	
	private boolean isOperator(char c) {
		return c == '>' || c == '<' || c == '=' || c == '!' || c == '+' || c == '-' || c == '*' || c == '/' || c=='(' || c==')';
	}
	
	private boolean isSpace(char c) {
		return c == ' ' || c == '\t' || c == '\n' || c == '\r';
	}
	
	private char nextChar() {
		if (isEOF()) {
			return '\0';
		}
		return content[posicao++];
	}

### Validando a sequência de tokens para o exemplo de expressão aritmética 42 + (675 * 31) - 20925
![](https://github.com/enivaldoqueiroz/Compiladores-Implementacao-em-Java/blob/main/src/Imagens/IMG001.png)
