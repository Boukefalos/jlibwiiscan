package wiiscan;

import com.github.boukefalos.jlibloader.Native;

public class Wiiscan {
	public static void load() {
		Native.load("com.github.boukefalos", "jlibwiiscan", "DelcomDLL");
        Native.binary("com.github.boukefalos", "jlibwiiscan", "sox");
	}
}