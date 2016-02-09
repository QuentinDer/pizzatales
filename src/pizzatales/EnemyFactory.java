package pizzatales;

public class EnemyFactory {

	private static String acceptedEnemyTypes = "TABPM";
	
	public static boolean isTileTypeSupported(char type) {
		String test = "";
		test += type;
		return acceptedEnemyTypes.contains(test);
	}
	
	public static Enemy getEnemy(int x, int y, char c, int difficultylevel) {
		Enemy e = null;
		switch(c) {
		case 'T':
			e = new Tato((x * 50) + 25,(y * 50) + 25, difficultylevel);
			break;
		case 'A':
			e = new Aubergine((x * 50) + 25,(y * 50) + 25, difficultylevel);
			break;
		case 'B':
			e = new Broccoli((x * 50) + 25,(y * 50) + 25, difficultylevel);
			break;
		case 'P':
			e = new Pepper((x * 50) + 25,(y * 50) + 25, difficultylevel);
			break;
		case 'M':
			e = new Mushroom((x * 50) + 25,(y * 50) + 25, difficultylevel);
			break;
		}
		return e;
	}
	
}
