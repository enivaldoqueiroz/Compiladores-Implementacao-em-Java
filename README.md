# Compiladores-Implementacao-em-Java
Compiladores Implementação em Java

	'//Metodo principal da classe LexScanner
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
				break;'

### Validando a sequência de tokens para o exemplo de expressão aritmética 42 + (675 * 31) - 20925
![](https://github.com/enivaldoqueiroz/Compiladores-Implementacao-em-Java/blob/main/src/Imagens/IMG001.png)
