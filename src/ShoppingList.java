import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ShoppingList {

	public static void main(String[] args) {
		String userInput = "";
		String userContinue = "";
		Scanner scan = new Scanner(System.in);
		Map<String, Double> menu = new TreeMap<>();

		menu.put("Apple", 0.99);
		menu.put("Banana", 0.59);
		menu.put("Cauliflower", 1.59);
		menu.put("Dragonfruit", 2.19);
		menu.put("Elderberry", 1.79);
		menu.put("Figs", 2.09);
		menu.put("Grapefruit", 1.99);
		menu.put("Honeydew", 3.49);

		ArrayList<String> cartItems = new ArrayList<String>();
		ArrayList<Double> cartPrices = new ArrayList<Double>();

		
		do {
			boolean validateInput = false;
			while (validateInput == false) {
				getMenu(menu);
				System.out.print("\n" + "What item would you like to order? ");
				userInput = scan.next();
				
				try {
					if (menu.containsKey(userInput)) { // case-sensitive- unsure of how to ignore case for ArrayLists
						validateInput = true;
					} else {
						validateInput = false;
						throw new IllegalArgumentException();
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Sorry, we don't have those. Please try again." + "\n");
				}
			}
			
			cartItems.add(userInput);
			double findPrice = menu.get(userInput);
			cartPrices.add(findPrice);
			
			System.out.println("Adding " + userInput + " to cart at $" + findPrice + "\n");
			System.out.println("Would you like to order anything else (y/n)?");
			
			userContinue = scan.next();

		} while (userContinue.contains("y"));

		System.out.println("Thanks for your order!" + "\n" + "Here's what you got: ");

		getCart(cartItems, cartPrices);
		getAverage(cartPrices);
		getHighest(menu, cartPrices);
		getLowest(menu, cartPrices);
		
	}
	
	public static void getLowest (Map map, ArrayList<Double> d) {
		Double min = Collections.min(d);
		for (Object o : map.keySet()) {
			if (map.get(o).equals(min)) {
				System.out.println("Your least expensive item is " + o + " at " + "$" + min);
			}
		}
	}
	
	public static void getHighest(Map map,ArrayList<Double> d) {
		Double max = Collections.max(d);
		for (Object o:map.keySet()) {
			if (map.get(o).equals(max)) {
				System.out.println("Your most expensive item is " + o + " at " + "$" + max);
			}
		}
	}
	
	public static void getAverage(ArrayList<Double> price) {
		DecimalFormat Currency = new DecimalFormat("$0.00");
		double sum = 0.0;
		double avg = 0.0;
		for (int i = 0; i < price.size(); i++) {
			sum += price.get(i);
			avg = sum / price.size();
		}
		System.out.println("\n" + "Average price per item in order is: " + Currency.format(avg));
	}

	public static void getCart(ArrayList<String> s, ArrayList<Double> d) {
		DecimalFormat Currency = new DecimalFormat("$0.00");
		String f = "%-17.17s %-17.17s %n";
		for (int i = 0; i < s.size(); i++)
			System.out.printf(f, s.get(i), Currency.format(d.get(i)));
	}

	public static void getMenu(Map menu) {
		System.out.println("Welcome to Guenther's Market!" + "\n");
		System.out.printf("%-20s %s", "Item", "Price");
		System.out.println("\n" + "=============================");
		menu.forEach((item, price) -> System.out.printf("%-20s %s\n", item, price));
	}
}
