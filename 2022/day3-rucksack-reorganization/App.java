import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class App {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int prioritySum1 = 0;
		int prioritySum2 = 0;
		List<String> lnList = new ArrayList<>();
		while (sc.hasNextLine()) {
			String ln = sc.nextLine();
			if (!ln.isBlank()) {
				prioritySum1 += processPriority1(ln);
				lnList.add(ln);
				if (lnList.size() == 3) {
					prioritySum2 += processPriority2(lnList);
					lnList.clear();
				}
			}
		}
		System.out.println("part1 - priority sum: " + prioritySum1);
		System.out.println("part2 - priority sum: " + prioritySum2);
	}

	private static int processPriority1(String ln) {
		int len = ln.length();
		String firstHalf = ln.substring(0, len / 2);
		String secondHalf = ln.substring(len / 2, len);
		char repeating = ' ';
		for (int i = 0; i < firstHalf.length(); i++) {
			for (int j = 0; j < secondHalf.length(); j++) {
				if (firstHalf.charAt(i) == secondHalf.charAt(j)) {
					repeating = firstHalf.charAt(i);
					break;
				}
			}
		}
		return (Character.isLowerCase(repeating))
					? (int) repeating - 96 
					: (int) repeating - 38;
	}

	private static int processPriority2(List<String> lnList) {
		Set<Character> set0 = lnList.get(0).chars()
								.mapToObj(e -> (char) e).collect(Collectors.toSet());
		Set<Character> set1 = lnList.get(1).chars()
								.mapToObj(e -> (char) e).collect(Collectors.toSet());
		Set<Character> set2 = lnList.get(2).chars()
								.mapToObj(e -> (char) e).collect(Collectors.toSet());
		set0.retainAll(set1);
		set0.retainAll(set2);
		char repeating = (char) set0.toArray()[0];
		return (Character.isLowerCase(repeating)) 
					? (int) repeating - 96 
					: (int) repeating - 38;
	}
}