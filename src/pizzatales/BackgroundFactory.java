package pizzatales;

import java.awt.Image;

public class BackgroundFactory {

	private final static String isBackground = "12345NIlo";
	
	public static Image grass, cave, dirt, brick, mountain, snow, ice, lava, waterflow;

	public static boolean isBackgroundImage(char type) {
		String test = "";
		test += type;
		return isBackground.contains(test);
	}
	
	public static Image getBackground(char typeInt) {
		Image ans = null;
		switch(typeInt) {
		case '1':
			ans = grass;
			break;
		case '2':
			ans = cave;
			break;
		case '3':
			ans = dirt;
			break;
		case '4':
			ans = brick;
			break;
		case '5':
			ans = mountain;
			break;
		case 'N':
			ans = snow;
			break;
		case 'I':
			ans = ice;
			break;
		case 'l':
			ans = lava;
			break;
		case 'o':
			ans = waterflow;
			break;
		}
		return ans;
	}
	
}
