import java.util.Scanner;

class Pair<A, B> {
	public A a;
	public B b;

	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}
}

public class App {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int containmentCount = 0, overlappingCount = 0;

		while (sc.hasNextLine()) {
			String ln = sc.nextLine();
			Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> pairs = processPairs(ln);
			containmentCount += processContainment(pairs.a, pairs.b);
			overlappingCount += processOverlapping(pairs.a, pairs.b);
		}

		System.out.println("containmentCount: " + containmentCount);
		System.out.println("containmentCount: " + overlappingCount);
	}

	private static Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> processPairs(String ln) {
		String part1 = ln.subSequence(0, ln.lastIndexOf(',')).toString();
		String[] arr1 = part1.split("-");
		String part2 = ln.subSequence(ln.lastIndexOf(',') + 1, ln.length()).toString();
		String[] arr2 = part2.split("-");

		Pair<Integer, Integer> p1 = new Pair<Integer, Integer>(Integer.parseInt(arr1[0]),
				Integer.parseInt(arr1[1]));
		Pair<Integer, Integer> p2 = new Pair<Integer, Integer>(Integer.parseInt(arr2[0]),
				Integer.parseInt(arr2[1]));

		return new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(p1, p2);
	}

	private static int processContainment(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
		if ((p1.a <= p2.a && p1.b >= p2.b) ||
				(p1.a >= p2.a && p1.b <= p2.b))
			return 1;
		return 0;
	}

	private static int processOverlapping(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
		if ((p2.a <= p1.b && p1.b <= p2.b) ||
				(p1.a <= p2.b && p2.b <= p1.b))
			return 1;
		return 0;
	}
}