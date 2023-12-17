import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class App {
	private static Scanner sc = new Scanner(System.in);
	public static String[][] crateStacks = new String[8][9];
	public static ArrayList<Stack<String>> stacks1 = new ArrayList<Stack<String>>();
	public static ArrayList<Stack<String>> stacks2 = new ArrayList<Stack<String>>();

	public static void main(String[] args) {
		int lnCount = 0;
		while (sc.hasNextLine()) {
			lnCount++;
			String ln = sc.nextLine();
			if (lnCount < 9) parseCrates(ln, lnCount);
			if (lnCount == 10) arrayToStack();
			if (lnCount > 10) {
				parseMovement1(ln, lnCount);
				parseMovement2(ln, lnCount);
			}
		}
		printSolutions();
	}

	private static void parseCrates(String ln, int lnCount) {
		int pos = 0, colCount = 0;
		while ((pos + 2) < ln.length()) {
			String crate = ln.substring(pos, pos + 3);
			if (crate.equals("   ")) crate = "[X]";
			crateStacks[lnCount - 1][colCount] = crate;
			pos += 4;
			colCount++;
		}
	}

	private static void arrayToStack() {
		for (int i = 0; i < 9; i++) {
			Stack<String> stack1 = new Stack<String>();
			Stack<String> stack2 = new Stack<String>();
			for (int j = 0; j < 8; ++j) {
				if (crateStacks[7 - j][i].equals("[X]")) continue;
				stack1.push(crateStacks[7 - j][i]);
				stack2.push(crateStacks[7 - j][i]);
			}
			stacks1.add(stack1);
			stacks2.add(stack2);
		}
	}

	private static void parseMovement1(String ln, int lnCount) {
		String[] lnArr = ln.split(" ");
		int amount = Integer.parseInt(lnArr[1]);
		int from = Integer.parseInt(lnArr[3]);
		int to = Integer.parseInt(lnArr[5]);
		if (stacks1.get(from - 1).size() >= amount) {
			for (int i = 0; i < amount; i++) {
				String poppedCrate = stacks1.get(from - 1).pop();
				stacks1.get(to - 1).push(poppedCrate);
			}
		}
	}
	
	private static void parseMovement2(String ln, int lnCount) {
		String[] lnArr = ln.split(" ");
		int amount = Integer.parseInt(lnArr[1]);
		int from = Integer.parseInt(lnArr[3]);
		int to = Integer.parseInt(lnArr[5]);
		if (stacks2.get(from - 1).size() >= amount) {
			Stack<String> auxStack = new Stack<String>();
			for (int i = 0; i < amount; i++) {
				auxStack.push(stacks2.get(from - 1).pop());
			}
			for (int i = 0; i < amount; i++) {
				stacks2.get(to - 1).push(auxStack.pop());
			}
		}
	}

	private static void printSolutions() {
		StringBuilder solution1 = new StringBuilder("Solution 1: ");
		stacks1.forEach(stack -> {
			solution1.append(String.format("%s ", stack.pop()));
		});
		System.out.println(solution1.toString());
		StringBuilder solution2 = new StringBuilder("Solution 2: ");
		stacks2.forEach(stack -> {
			solution2.append(String.format("%s ", stack.pop()));
		});
		System.out.println(solution2.toString());
	}
}