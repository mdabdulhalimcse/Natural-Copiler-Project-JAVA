
package natural.compiler;


import java.text.DecimalFormat;
import java.util.Scanner;




public class NaturalCompiler {
	
	public static void main(String[] args) {
           
		System.out.println("Enter the natural Language: ");
		String calculation = Input();
		double result = Calculation.performCalculation(calculation);
		System.out.format("Result: %s\n\n", formatResult(result));
	}
	
	private static String Input() {
		try (Scanner scanner = new Scanner(System.in)) {
			return scanner.nextLine();
		}
	}
	
	private static String formatResult(double result) {
		return new DecimalFormat("0.##").format(result);
	}
	
}


