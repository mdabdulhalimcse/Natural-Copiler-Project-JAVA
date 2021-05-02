
package natural.compiler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


enum Number {
	ZERO("zero", 0), ONE("one", 1), TWO("two", 2), THREE("three", 3), FOUR("four", 4), FIVE("five", 5), SIX("six", 6), SEVEN("seven", 7), EIGHT("eight", 8), NINE("nine", 9), TEN("ten", 10),ZERO0("0", 0), ONE1("1", 1), TWO2("2", 2), THREE3("3", 3), FOUR4("4", 4), FIVE5("5", 5), SIX6("6", 6), SEVEN7("7", 7), EIGHT8("8", 8), NINE9("9", 9),TEN10("10", 10),ELEVENT("11", 11), TWELVE("12", 12), THRIRTEEN("13", 13), FOURTEEN("14", 14), FIFTEEN("15", 15), SIXTEEN("16", 16), SEVENTEEN("17", 17), EIGHTEEN("18", 18), NINETEEN("19", 19), TWENTY("20", 20);
	
	
	private static final Map<String, Number> nameToNumber = new HashMap<>();
	static {
		Arrays.stream(Number.values()).forEach(number -> {
			nameToNumber.put(number.name, number);
		});
	}
	
	
	private final String name;
	
	
	private final int value;

	private Number(String name, int value) {
		this.name = name;
		this.value = value;
	}		
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}

	
	public static Number fromName(String numberName) {
		return Optional.ofNullable(nameToNumber.get(numberName)).orElseThrow(() -> new IllegalArgumentException("Not find number from name" + numberName));
	}
	
	
	public static boolean isNumber(String token) {
		return nameToNumber.containsKey(token);
	}
	
}