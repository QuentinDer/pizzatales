package pizzatales;

public class ItemFactory {
	private final static String itemslist = "phailoxm";
	
	public static boolean isItemSupported(char type) {
		String test = "";
		test += type;
		return itemslist.contains(test);
	}
	
	public static Item getItem(int x, int y, int deltapy, char c) {
		Item i = null;
		switch(c) {
		case 'p':
			
			break;
		case 'h':
			i = new HealthPotion(x,y,deltapy, true);
			break;
		case 'a':
			i = new ArmorPotion(x,y,deltapy, true);
			break;
		case 'i':
			i = new EntryDoor(x,y,deltapy, true);
			break;
		case 'l':
			i = new Lava(x, y,deltapy, false);
			break;
		case 'o':
			i = new WaterFlow(x, y,deltapy, false);
			break;
		case 'x':
			i = new LevelExit(x, y,deltapy, true);
			break;
		case 'm':
			i = new HiddenTrigger(x, y,deltapy, true);
			break;
		}
		return i;
	}
	
}
