package bookshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScannerUtils {
	private static BufferedReader br;
	static {
	   br=new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static String next() throws IOException    {
		return br.readLine();
	}
	public static double nextDouble()  {
		try {
			return Double.parseDouble(br.readLine());
		} catch (NumberFormatException e) {
			return -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			return -2;
		}
	}
	public static int nextInt() {
		try {
			return Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			return -2;
		}
	}
	public static void close() throws IOException {
		br.close();
	}
}
