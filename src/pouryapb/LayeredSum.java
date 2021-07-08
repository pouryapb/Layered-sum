package pouryapb;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Logger;

public class LayeredSum {

	private static final Logger LOGGER = Logger.getLogger(LayeredSum.class.getName());

	public static void main(String[] args) {

		var sc = new Scanner(System.in);

		String s = sc.nextLine();

		sc.close();

		var ls = new LayeredSum();

		ls.layeredSum(ls.spliter(s).iterator());

	}

	/**
	 * layeredSum gets an Iterator of braces and numbers and starts summing numbers
	 * from deep inside to outside
	 * 
	 * @param list - an Iterator of a list of numbers
	 * @return sum of numbers before '}' character recursively!
	 */
	private int layeredSum(Iterator<String> list) {

		var res = 0;

		while (list.hasNext()) {

			String s = list.next();

			// if next element is a numbers add to result
			try {
				res += Integer.parseInt(s);

			} catch (Exception e) {
				// not a number!
				// '{' means another list to get sum of its elements
				if (s.equals("{"))
					res += layeredSum(list);
				// the end of the list
				// printing sum of lists elements and returning it to previous task(?!)
				if (s.equals("}")) {
					LOGGER.info(String.valueOf(res));
					return res;
				}
			}
		}

		// JAVA doesn't let me remove this!!
		return -1;
	}

	/**
	 * splinter separates characters in a string the way i need to process on
	 * 
	 * @param s - an string contains of numbers and ['{', '}', ',', ' '] characters
	 * @return
	 */
	private LinkedList<String> spliter(String s) {

		var res = new LinkedList<String>();

		s = s.replace(" ", "");
		String st = s;
		var j = 0;

		// separating every element with space
		for (var i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))
					|| (Character.isDigit(s.charAt(i)) && !Character.isDigit(s.charAt(i + 1)))) {
				st = st.substring(0, i + j + 1) + " " + s.substring(i + 1, s.length());
				j++;
			}
		}

		// removing ',' character and duplicated spaces and extra space at the end
		st = st.replace(",", "");
		st = st.replace("  ", " ");
		st = st.substring(0, st.length() - 1);

		// making an array of it
		String[] temp = st.split(" ");

		// making a linked list of that array
		Collections.addAll(res, temp);

		return res;
	}
}
