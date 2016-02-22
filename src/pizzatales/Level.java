package pizzatales;

public class Level {

	public static String getMapName(int level) {
		return "L"+ ((level-1)/4+1) + ((level-1)%4+1)+".txt";
	}
	

	public static String getBackground(int level) {
		String ans = "";
		switch ((level-1)/4+1) {
		case 1:
			ans = "background1.png";
			break;
		case 2:
			ans = "background.png";
			break;
		}
		return ans;
	}
	
	
}
