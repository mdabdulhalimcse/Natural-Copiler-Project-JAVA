
package natural.compiler;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


enum Operator {
	
	ADD("+", 1, "add", "plus", "sum","addition","summation") {
		@Override
		public double apply(double operand1, double operand2) {
			return operand1 + operand2;
		}
	}, 
	
	SUBTRACT("-", 2, "subtract", "minus", "less", "sub") {
		@Override
		public double apply(double operand1, double operand2) {
			return operand1 - operand2;
		}
	}, 
	
	MULTIPLY("*", 3, "multiply-by", "times", "mul", "multiple", "multiply") {
		@Override
		public double apply(double operand1, double operand2) {
			return operand1 * operand2;
		}
	}, 
	
	DIVIDE("/", 4, "divide-by", "over", "div", "divide", "by") {
		@Override
		public double apply(double operand1, double operand2) {
			return operand1 / operand2;
		}
	};
	
	
	private static final Map<String, Operator> ToOperator = new HashMap<>(); 
	static {
		Arrays.stream(Operator.values()).forEach(operator -> {
			operator.Onames.stream().forEach(name -> {
				ToOperator.put(name, operator);
			});
		});
	}
	
	
	private final String symbol;
	
	
	private final int precedence;
	
	
	private final Set<String> Onames;
	
	private Operator(String symbol, int precedence, String... Onames) {
		this.symbol = symbol;
		this.precedence = precedence;
		this.Onames = new HashSet<String>();
		this.Onames.add(symbol);
		Collections.addAll(this.Onames, Onames);
	}
	
	@Override
	public String toString() {
		return symbol;
	}

	
	public boolean hasLessPriorityThan(Operator that) {
		return this.precedence < that.precedence;
	}
	
	public abstract double apply(double operand1, double operand2);
	
	
	public static Operator fromName(String operatorNames) {
		return Optional.ofNullable(ToOperator.get(operatorNames)).orElseThrow(() -> new IllegalArgumentException("Not find operator from Onames" + operatorNames));
	}
	
	
	public static boolean isOperator(String token) {
		return ToOperator.containsKey(token);
	}

}