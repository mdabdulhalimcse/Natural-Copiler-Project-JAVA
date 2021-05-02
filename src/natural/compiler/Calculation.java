
package natural.compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class Calculation {
	
	private Calculation() {
		super();
	}

	
	public static double performCalculation(String calculation) {
		List<String> infix = getInfix(calculation);
		List<String> postfix = getPostfixFromInfix(infix);
		return evaluatePostfix(postfix);
	}
	
	
	private static List<String> getInfix(String calculation) {
		return Arrays.stream(calculation.split("\\s+")).map(token -> {
			if (Operator.isOperator(token)) {
				return Operator.fromName(token).toString();
			} else if (Number.isNumber(token)) {
				return Number.fromName(token).toString();
			} else {
				throw new IllegalArgumentException("Unable to parse token " + token);
			}
		}).collect(Collectors.toList());
	}

	
	private static List<String> getPostfixFromInfix(List<String> infix ) {
		
        List<String> output = new ArrayList<>();
        Deque<String> stack  = new LinkedList<>();

        for (String token : infix) {
        	
            if (Operator.isOperator(token)) {
            	Operator operator = (Operator) Operator.fromName(token);
            	while (!stack.isEmpty() && operator.hasLessPriorityThan((Operator) Operator.fromName(stack.peek()))) {
                	output.add(stack.pop());
                }
                stack.push(token);
            } else {
                output.add(token);
            }
            
        }

        while (!stack.isEmpty()) {
        	output.add(stack.pop());
        }

        return output;
    }
	
	
	private static double evaluatePostfix(List<String> postfix) {
		
		Deque<String> stack  = new LinkedList<>();
         
	    for (String token : postfix) {
	    	
	        if (Operator.isOperator(token)) {
	        	Operator operator = (Operator) Operator.fromName(token);
	        	double operand2 = Double.parseDouble(stack.pop());  
	            double operand1 = Double.parseDouble(stack.pop());      
	            double result = operator.apply(operand1, operand2);
	            stack.push(String.valueOf(result));              
	        } else {
	        	stack.push(token);
	        }
	        
	    }          
	          
	    return Double.parseDouble(stack.pop());
	}
	
}
