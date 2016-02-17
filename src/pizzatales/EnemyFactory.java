package pizzatales;

public class EnemyFactory {

	private static String acceptedEnemyTypes = "TABPMS";
	
	public static boolean isTileTypeSupported(char type) {
		String test = "";
		test += type;
		return acceptedEnemyTypes.contains(test);
	}
	
	public static Enemy getEnemy(int x, int y, char c) {
		Enemy e = null;
		switch(c) {
		case 'T':
			e = new Tato((x * 50) + 25,(y * 50) + 40);
			break;
		case 'A':
			e = new Aubergine((x * 50) + 25,(y * 50) + 40);
			break;
		case 'B':
			e = new Broccoli((x * 50) + 25,(y * 50) + 40);
			break;
		case 'P':
			e = new Pepper((x * 50) + 25,(y * 50) + 40);
			break;
		case 'M':
			e = new Mushroom((x * 50) + 25,(y * 50) + 40);
			break;
		case 'S':
			e = new SirTomato((x * 50) + 25,(y*50) + 40);
		}
		return e;
	}
	
}
