package introduction;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import introduction.TriangleUtils;

public class TriangleUtilsTest {
	private TriangleUtils triangleUtils = new TriangleUtils();

	@Test
	public void testIsTriangle() {

		Map<Object, Boolean> results = new HashMap<Object, Boolean>() {
			{
				put(Arrays.asList(2, 2, 2), true);
				put(Arrays.asList(2, 2, 3), true);
				put(Arrays.asList(1, 2, 3), false);
				put(Arrays.asList(12, 1, 1), false);
				put(Arrays.asList(11, 212, 1), false);

			}
		};

		for (Entry<Object, Boolean> entry : results.entrySet()) {
			List<Integer> params = (List<Integer>) entry.getKey();
			boolean expected = (boolean) entry.getValue();
			boolean actual = triangleUtils.isTriangle(params.get(0),
					params.get(1), params.get(2));
			String message = "Неправильный ответ для " + params;
			assertEquals(message, expected, actual);
		}
	}

	@Test
	public void testGetTriangleArea() {
		double area = triangleUtils.getTriangleArea(3, 3, 3);
		assertEquals(3.897, area, 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsTriangleWithZeroArguments() {
		triangleUtils.isTriangle(0, 0, 0);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsTriangleWithSecondNegativeSide() {
		triangleUtils.isTriangle(1, 0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsTriangleWithThirdNegativeSide() {
		triangleUtils.isTriangle(1, 1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsTriangleWithNegativeArguments() {
		triangleUtils.isTriangle(-1, -2, -3);
	}

}
