import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

// Emily Leones
// Section D

public class EuclideanAlgorithm {

	private class combo {
		private int r;
		private int c1;
		private int f1;
		private int c2;
		private int f2;

		private combo(int a, int b, int c, int d, int e) {
			r = a;
			c1 = b;
			f1 = c;
			c2 = d;
			f2 = e;
		}
	}

	private LinkedList<combo> combos = new LinkedList<combo>();

	public int gcd(int n1, int n2) {
		int a = Math.max(n1, n2);
		int b = Math.min(n1, n2);
		if (a % b == 0) {
			System.out.println(a + " = " + a / b + "(" + b + ")" + " + " + a % b);
			return b;
		} else {
			combo combo = new combo(a % b, 1, a, -(a / b), b);
			System.out.println(a + " = " + a / b + "(" + b + ")" + " + " + a % b);
			combos.add(combo);
			return gcd(b, a % b);
		}
	}

	public void linCom(int gcd) {
		if (combos.size() == 1) {
			return;
		} else {
			combo combo1 = combos.removeLast();
			combo combo2 = combos.removeLast();
			combo comboAdd = new combo(gcd, combo1.c2 * combo2.c1, combo2.f1, combo1.c1 + (combo1.c2 * combo2.c2), combo1.f1);
			combos.add(comboAdd);
			linCom(gcd);
		}
	}

	public static void main(String[] blargs) {
		// Random rand = new Random();
		// int n1 = rand.nextInt(90000) + 10000;
		// int n2 = rand.nextInt(90000) + 10000;

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter first integer: ");
		int n1 = keyboard.nextInt();
		System.out.println("Enter second integer:");
		int n2 = keyboard.nextInt();
		keyboard.close();
		EuclideanAlgorithm ea = new EuclideanAlgorithm();

		System.out.println("\nApplying Euclidean Algorithm to: (" + n1 + ", " + n2 + ")");
		int gcd = ea.gcd(n1, n2);
		System.out.println("\ngcd(" + n1 + ", " + n2 + ") = " + gcd + "\n");

		ea.linCom(gcd);
		combo combo = ea.combos.removeFirst();
		System.out.println("Linear Combination:");
		System.out.println(combo.r + " = " + combo.c1 + "(" + combo.f1 + ") + " + "(" + combo.c2 + "(" + combo.f2 + "))");
	}

}
