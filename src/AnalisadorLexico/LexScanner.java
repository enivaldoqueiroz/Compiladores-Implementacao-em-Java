package AnalisadorLexico;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LexScanner {
	
	private char[] content;
	private int estado;
	
	public LexScanner(String filename) {
		try {
			
			String txtConteudo;
			txtConteudo = new String(Files.readAllBytes(Paths.get(filename),StandardCharsets.UTF_8));
			System.out.println("DEBUG ----------");
			System.out.println(txtConteudo);
			System.out.println("----------------");
			content = txtConteudo.toCharArray();
			
		} catch (Exception ex) {
			ex.printStackTrace();
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

}
