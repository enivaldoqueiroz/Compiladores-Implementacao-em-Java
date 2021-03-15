package AnalisadorLexico;

public class MainCLass {
	public static void main(String[] args) {
		LexScanner sc = new LexScanner("input.eni");
		Token token = null;
		
		do {
			token = sc.nextToken();
			if (token != null) {
				System.out.println(token);
			}
			
		} while (token != null);
	}
}
