import java.util.Scanner;

/**
 * rock(1) - paper(2) - scissors(3)
 * part2: X(loss) - Y(draw) - Z(win)
 */

public class App {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		int user1 = 0, user2 = 0;
		while (sc.hasNextLine()) {
			String ln = sc.nextLine();
			int col1 = (int) ln.charAt(0) - 64;
			int col2 = (int) ln.charAt(2) - 87;
			user1 += processScore1(col1, col2);
			user2 += processScore2(col1, col2);
		}
		System.out.println(String.format("part 1: user score = %d", user1));
		System.out.println(String.format("part 2: user score = %d", user2));
	}

	private static int processScore1(int col1, int col2) {
		int user = col2;
		int result = col2 - col1;
		if (result == 0)
			user += 3;
		if (result == 1 || result == -2)
			user += 6;
		return user;
	}

	private static int processScore2(int col1, int opt) {
		int col2 = 0;
		switch (opt) {
			case 1:
				if (col1 == 1)
					col2 = 3;
				if (col1 == 2)
					col2 = 1;
				if (col1 == 3)
					col2 = 2;
				break;
			case 2:
				col2 = col1;
				break;
			case 3:
				if (col1 == 1)
					col2 = 2;
				if (col1 == 2)
					col2 = 3;
				if (col1 == 3)
					col2 = 1;
				break;
		}
		return processScore1(col1, col2);
	}
}
