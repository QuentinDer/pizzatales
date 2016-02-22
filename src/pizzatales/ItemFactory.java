package pizzatales;

public class ItemFactory {
	private final static String itemslist = "phailox";
	
	public static boolean isItemSupported(char type) {
		String test = "";
		test += type;
		return itemslist.contains(test);
	}
	
	public static Item getItem(int x, int y, char c) {
		Item i = null;
		switch(c) {
		case 'p':
			
			break;
		case 'h':
			i = new HealthPotion(x,y, true);
			break;
		case 'a':
			i = new ArmorPotion(x,y, true);
			break;
		case 'i':
			i = new EntryDoor(x,y, true);
			break;
		case 'l':
			i = new Lava(x, y, false);
			break;
		case 'o':
			i = new WaterFlow(x, y, false);
			break;
		case 'x':
			i = new LevelExit(x, y, true);
			break;
		}
		return i;
	}
	
}
