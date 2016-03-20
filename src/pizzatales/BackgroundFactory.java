package pizzatales;

import java.awt.Image;

public class BackgroundFactory {

	private final static String isBackground = "12345";
	
	public static Image dirt;

	public static boolean isBackgroundImage(char type) {
		String test = "";
		test += type;
		return isBackground.contains(test);
	}
	
	public static Image getBackground(char typeInt) {
		Image ans = null;
		switch(typeInt) {
		case '1':
			ans = dirt;
			break;
		}
		return ans;
	}
	
}
