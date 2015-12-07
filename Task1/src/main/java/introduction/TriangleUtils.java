package introduction;

public class TriangleUtils {

	/**
	 * Задача о треугольнике
	 * 
	 * Вам даны длинны трех отрезков: a, b, c. Нужно вернуть true, если они
	 * могут быть сторонами треугольника и false, если не могут.
	 * 
	 */

	public boolean isTriangle(int a, int b, int c)
			throws IllegalArgumentException {
		check(a, b, c);
		return (a < c + b && c < a + b && b < a + c);
	}

	/**
	 * Вам даны длинны трех сторон треугольника: a, b, c. Необходимо вычислить
	 * площадь треугольника.
	 */

	public double getTriangleArea(int a, int b, int c)
			throws IllegalArgumentException {
		isTriangle(a, b, c);
		double area;
		double semiperimeter = (a + b + c) / 2.0;
		area = Math.sqrt(semiperimeter * (semiperimeter - a)
				* (semiperimeter - b) * (semiperimeter - c));
		return area;
	}

	private static void check(int a, int b, int c) {
		if (a <= 0 || b <= 0 || c <= 0) {
			throw new IllegalArgumentException(
					"triangle side should be positive");
		}
	}
}
