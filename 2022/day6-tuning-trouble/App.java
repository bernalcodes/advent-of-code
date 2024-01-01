import java.util.LinkedHashSet;
import java.util.Scanner;

public class App {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String ln = sc.nextLine();
		System.out.println(String.format("Part 1 - %d", processString(ln, 4)));
		System.out.println(String.format("Part 2 - %d", processString(ln, 14)));
	}

	private static int processString(String ln, int markerLength) {
		LinkedHashSet<Character> marker = new LinkedHashSet<Character>();
		int count = 0;
		while (count < ln.length() - markerLength) {
			for (char ch : ln.substring(count, count + markerLength).toCharArray()) marker.add(Character.valueOf(ch));
			if (marker.size() == markerLength) break;
			marker.clear();
			count++;
		}
		return count + markerLength;
	}
}