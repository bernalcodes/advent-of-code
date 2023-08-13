import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class App {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		ArrayList<Integer> calorieCount = new ArrayList<Integer>();
		Integer maxCalorieCount = 0, currentCalories = 0;
		while (sc.hasNextLine()) {
			String ln = sc.nextLine();
			if (!ln.isBlank())
				currentCalories += Integer.parseInt(ln);
			else {
				if (maxCalorieCount < currentCalories)
					maxCalorieCount = currentCalories;
				calorieCount.add(currentCalories);
				currentCalories = 0;
			}
		}
		Collections.sort(calorieCount, Comparator.reverseOrder());
		Integer topThree = calorieCount.get(0) + calorieCount.get(1) + calorieCount.get(2);
		System.out.println(String.format("part 1: max calorie count = %d", maxCalorieCount));
		System.out.println(String.format("part 2: total calorie count for top three = %d", topThree));
	}
}
