package wiiscan;

import com.github.boukefalos.jlibloader.Native;

public class Wiiscan {
	public static String load() {
		Native.load("com.github.boukefalos", "libwiiuse");
		Native.load("com.github.boukefalos", "jlibwiiscan", "DelcomDLL");
        return Native.binary("com.github.boukefalos", "jlibwiiscan", "sox");
	}

	public static void main(String[] args) {
		System.out.println(Wiiscan.load());
	}
}