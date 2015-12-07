package introduction;

public class MathUtils {

	/**
	 * Returns the greatest common divider of given two numbers
	 * 
	 * @param firstNumber
	 *            - positive number
	 * @param secondNumber
	 *            - positive number
	 * @return greatest common divider of two numbers
	 */
	public int getGreatestCommonDivider(int firstNumber, int secondNumber) {
		if (firstNumber <= 0 || secondNumber <= 0) {
			throw new IllegalArgumentException(
					"Invalid Input: one of the numbers is less than or equal to 0");
		}
		int first = firstNumber;
		int second = secondNumber;
		int residue = 0;
		do {
			residue = first % second;
			first = second;
			second = residue;
		} while (residue != 0);
		return first;
	}

	/**
	 * Returns sum of digits of the given number
	 * 
	 * @param number
	 *            - positive number
	 * @return the sum of digits of the given number
	 */
	public int getSumOfDigits(int number) {
		if (number <= 0) {
			throw new IllegalArgumentException("number should be positive");
		}
		int sum = 0;
		int x = number;

		while (x != 0) {
			sum = sum + (x % 10);
			x /= 10;
		}
		return sum;
	}

	/**
	 * Checks if the given number is prime or not
	 * 
	 * @param number
	 * @return true - if number is prime, if not return false
	 */
	public boolean isPrime(int number) {
		if (number < 2) {
			return false;
		}
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns sum of row: 1! - 2! + 3! - 4! + 5! - ... + n!
	 * 
	 * @param n
	 *            - positive number
	 */
	public int getSumOfRow(int n) {
		int result = 0;
		int ret = 1;
		int k = 1;
		for (int i = 1; i <= n; i++) {
			ret = ret * i * k;
			result += ret;
			k = -1;
		}
		return result;
	}

	/**
	 * Returns Fibonacci series of a specified length
	 * 
	 * @param length
	 *            - the length of the Fibonacci series
	 * @return array filled with Fibonacci series
	 */
	public int[] getFibonacciSeries(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("lenght should be positive");
		}
		int[] arr = new int[length];
		arr[0] = 0;
		if (length > 1) {
			arr[1] = 1;
		}

		for (int i = 2; i < length; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		return arr;
	}

	/**
	 * Returns array with prime numbers
	 * 
	 * @param length
	 *            - the length of prime numbers series
	 * @return array filled with prime numbers
	 */
	public int[] getPrimeSeries(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("lenght should be positive");
		}
		int[] arr = new int[length];
		int cell = 0;
		int i = 2;
		while (cell != length) {
			if (isPrime(i)) {
				arr[cell++] = i;
			}
			i++;
		}
		return arr;
	}
}
