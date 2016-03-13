package pizzatales;

public class EnemyFactory {

	private static String acceptedEnemyTypes = "TABPMSWROG";
	
	public static boolean isTileTypeSupported(char type) {
		String test = "";
		test += type;
		return acceptedEnemyTypes.contains(test);
	}
	
	public static Enemy getEnemy(int x, int y, char c, StartingClass app) {
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
			break;
		case 'W':
			e = new MushroomWizard(x*50+25,y*50+40,app);
			break;
		case 'R':
			e = new CarolinaReaper(x*50+25,y*50+40);
			break;
		case 'O':
			e = new Oniough(x*50+25,y*50+40);
			break;
		case 'G':
			e = new Garlnstein(x*50+25,y*50+40);
			break;
		}
		return e;
	}
	
}
