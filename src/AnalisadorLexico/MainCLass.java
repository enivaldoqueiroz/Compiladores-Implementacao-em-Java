package AnalisadorLexico;

public class MainCLass {
	public static void main(String[] args) {
		
		try {
								
			LexScanner sc = new LexScanner("D:\\FBUNI\\2021.1\\PROJETOS_DA_FALCULDADE\\Compiladores-Implementacao-em-Java\\bin\\input.eni");
			LexParser pa = new LexParser(sc);
			//Token token = null;
			
			pa.E();
		
			/*do {
				token = sc.nextToken();
				if (token != null) {
					System.out.println(token);
				}
				
			} while (token != null);
			*/
			
			System.out.println("Compilação com sucesso");
		}
		catch(LexException ex) {
			System.out.println("Analise Lexica deu erro " + ex.getMessage());
		}
		catch(LexSyntaxException ex) {
			System.out.println("Analise Sintatica deu erro " + ex.getMessage());
		}
		catch(Exception ex) {
			System.out.println("Generic error - Erro generico!");
			
		}
	}
}
