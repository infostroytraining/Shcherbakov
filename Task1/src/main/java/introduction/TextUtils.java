package introduction;

/**
 * Проверка орфографии
 * 
 * Некоторые люди не обращают внимание на орфографию. Например, не пишут новое
 * предложение с заглавной буквы. Или не ставят пробел после знаков препинания.
 * 
 * Ваша задача: исправить их ошибки.
 * 
 * Что нужно сделать:
 * 
 * 1. Каждое новое предложение должно начинаться с заглавной буквы. 2. После
 * знаков препинания (точка и запятая) должны быть пробелы.
 */
public class TextUtils {
	public String correctText(String text) {
		int pos = 0;
		boolean capitalize = true;
		StringBuilder sb = new StringBuilder(text);
		while (pos < sb.length()) {
			if (sb.charAt(pos) == '.') {
				capitalize = true;
			} else if (capitalize && !Character.isWhitespace(sb.charAt(pos))) {
				sb.setCharAt(pos, Character.toUpperCase(sb.charAt(pos)));
				capitalize = false;
			}

			pos++;
		}
		String result = sb.toString().replaceAll("[,.](?=\\S)", "$0 ");
		if (Character.isLetter(result.charAt(result.length() - 1))) {
			result = result.concat(".");
		}
		return result;
	}
}
