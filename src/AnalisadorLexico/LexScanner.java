package AnalisadorLexico;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LexScanner {
	
	private char[] 	content;
	private int 	estado;
	private int		posicao;
	
	public LexScanner(String filename) {
		try {
			
			String txtConteudo;
			txtConteudo = new String(Files.readAllBytes(Paths.get(filename)),StandardCharsets.UTF_8);
			System.out.println("DEBUG ----------");
			System.out.println(txtConteudo);
			System.out.println("----------------");
			content = txtConteudo.toCharArray();
			posicao = 0;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
				
	}
	
	//Metodo principal da classe LexScanner
	public Token nextToken() {
		char currentChar;
		if (isEOF()) {
			return null;
		}
		estado = 0;
		while (true) {
			currentChar = nextChar();
			
			switch(estado) {
			case 0:
				if(isChar(currentChar)) {
					estado = 1;
					
				}
				else if (isDigit(currentChar)) {
					estado = 3;
					
				}
				else if (isSpace(currentChar)) {
					estado = 0;
					
				}
				else if(isOperator(currentChar)) {
					estado = 5;
				}
				else {
					throw new RuntimeException("Unrecognized SYMBOL");
				}
				break;
			case 1:
				if(isChar(currentChar) || isDigit(currentChar)) {
					estado = 1;
				}
				else {
					estado = 2;
				}
				break;
			case 2:
				Token token = new Token();
				token.setType(Token.TK_IDENTIFIER);
				return token;
					
			}
		}
	}
	
	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}
	
	private boolean isChar(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}
	
	private boolean isOperator(char c) {
		return c =='>' || c == '<' || c == '=' || c == '!';
	}
	
	private boolean isSpace(char c) {
		return c == ' ' || c == '\t' || c == '\n' || c == '\r';
	}
	
	private char nextChar() {
		return content[posicao++];
	}
	
	private boolean isEOF() {
		return posicao == content.length;
	}
	
	private void back() {
		posicao--;
	}
	

}
