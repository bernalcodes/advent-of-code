import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class App {
	private static Scanner sc = new Scanner(System.in);
	public static String[][] crateStacks = new String[8][9];
	// public static ArrayList<ArrayList<String>> arrayListMtx = new ArrayList<ArrayList<String>>();
	public static int[] stacksCount = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	public static int[] auxStacksCount = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	public static ArrayList<Stack<String>> stacks = new ArrayList<Stack<String>>();

	public static void main(String[] args) {
		int lnCount = 0;
		while (sc.hasNextLine()) {
			lnCount++;
			String ln = sc.nextLine();
			if (lnCount < 9) {
				parseCrates(ln, lnCount);
				// parseCrates2(ln, lnCount);
			}
			if (lnCount == 10) arrayToStack();
			if (lnCount > 10) parseMovement(ln, lnCount);
		}
		// printMtx();
		// printMtx2();
		// printStacksCount();
		printStackArr();
	}

	private static void parseCrates(String ln, int lnCount) {
		int pos = 0, colCount = 0;
		while ((pos + 2) < ln.length()) {
			String crate = ln.substring(pos, pos + 3);
			if (crate.equals("   ")) crate = "[X]";
			else {
				stacksCount[colCount]++;
				auxStacksCount[colCount]++;
			}
			crateStacks[lnCount - 1][colCount] = crate;
			pos += 4;
			colCount++;
		}
	}

	/* private static void parseCrates2(String ln, int lnCount) {
		int pos = 0, colCount = 0;
		ArrayList<String> row = new ArrayList<String>();
		while ((pos + 2) < ln.length()) {
			String crate = ln.substring(pos, pos + 3);
			if (!crate.equals("   ")) {
				stacksCount[colCount]++;
				auxStacksCount[colCount]++;
			}
			row.add(crate);
			pos += 4;
			colCount++;
		}
		arrayListMtx.add(row);
	} */

	/* private static void parseMovement(String ln, int lnCount) {
		String[] lnArr = ln.split(" ");
		int amount = Integer.parseInt(lnArr[1]);
		int from = Integer.parseInt(lnArr[3]);
		int to = Integer.parseInt(lnArr[5]);
		System.out.println(
			String.format("Line %d: Move %d crates from %d to %d", lnCount, amount, from, to));
		
		if (auxStacksCount[from - 1] >= amount) {
			auxStacksCount[from - 1] -= amount;
			auxStacksCount[to - 1] += amount;
			System.out.println("OK");
		} else {
			System.out.println("NOT OK");
		}
		
		for (int i = 0; i < 9; i++) {
			System.out.print(String.format("[%d] ", auxStacksCount[i]));
		}
		System.out.println();	
		System.out.println();	
	} */

	/* private static void printMtx() {
		System.out.println("Crate stacks:");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(crateStacks[i][j]);
			}
			System.out.println();
		}
	} */

	/* private static void printMtx2() {
		System.out.println("Crate stacks:");
		for (int i = 0; i < arrayListMtx.size(); i++) {
			for (int j = 0; j < arrayListMtx.get(i).size(); j++) {
				if (arrayListMtx.get(i).get(j).equals("   ")) System.out.print("[ ] ");
				else System.out.print(String.format("%s ", arrayListMtx.get(i).get(j)));
			}
			System.out.println();
		}
	} */

	/* private static void printStacksCount() {
		System.out.println("Stacks count:");
		for (int i = 0; i < 9; i++) {
			System.out.print(String.format("[%d] ", stacksCount[i]));
		}
		System.out.println();
	} */

	private static void arrayToStack() {
		for (int i = 0; i < 9; i++) {
			Stack<String> stack = new Stack<String>();
			for (int j = 0; j < 8; ++j) {
				if (crateStacks[7 - j][i].equals("[X]")) continue;
				stack.push(crateStacks[7 - j][i]);
			}
			stacks.add(stack);
		}

		printStackArr();
	}

	private static void printStackArr() {
		System.out.println("Stacks:");
		for (int i = 0; i < stacks.size(); i++) {
			System.out.println(String.format("Stack #%d - Size %d: ", (i + 1), stacks.get(i).size()) + stacks.get(i));
		}
	}

	private static void parseMovement(String ln, int lnCount) {
		String[] lnArr = ln.split(" ");
		int amount = Integer.parseInt(lnArr[1]);
		int from = Integer.parseInt(lnArr[3]);
		int to = Integer.parseInt(lnArr[5]);
		// System.out.println(String.format("Line %d: Move %d crates from %d to %d", lnCount, amount, from, to));
		
		if (stacks.get(from - 1).size() >= amount) {
			for (int i = 0; i < amount; i++) {
				String poppedCrate = stacks.get(from - 1).pop();
				stacks.get(to - 1).push(poppedCrate);
			}
		}
	}
}