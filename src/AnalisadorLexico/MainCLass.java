package AnalisadorLexico;

public class MainCLass {
	public static void main(String[] args) {
		
		try {
								
			LexScanner sc = new LexScanner("C:\\Users\\eniva\\eclipse-workspace\\Compiladores-Implementacao-em-Java\\src\\input.eni");
			Token token = null;
			
			do {
				token = sc.nextToken();
				if (token != null) {
					System.out.println(token);
				}
				
			} while (token != null);
		}
		catch(LexException ex) {
			System.out.println("Lexical Error " + ex.getMessage());
		}
		catch(Exception ex) {
			System.out.println("Generic error - Erro generico!");
			
		}
	}
}
