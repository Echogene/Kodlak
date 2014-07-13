package util;

/**
 * @author Steven Weston
 */
public class IOUtils {

	/**
	 * Obtained from http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
}
