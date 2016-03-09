package pizzatales;

public class ItemFactory {
	private final static String itemslist = "phailoxmubC";
	
	public static boolean isItemSupported(char type) {
		String test = "";
		test += type;
		return itemslist.contains(test);
	}
	
	public static Item getItem(int x, int y, int deltapx, int deltapy, char c, int height) {
		Item i = null;
		switch(c) {
		case 'p':
			i = new PizzaBox(x,y,deltapx,deltapy,true, height);
			break;
		case 'h':
			i = new HealthPotion(x,y,deltapx,deltapy, true, height);
			break;
		case 'a':
			i = new ArmorPotion(x,y, deltapx,deltapy, true, height);
			break;
		case 'i':
			i = new EntryDoor(x,y,deltapx,deltapy, true, height);
			break;
		case 'l':
			i = new Lava(x, y,deltapx,deltapy, false, height);
			break;
		case 'o':
			i = new WaterFlow(x, y,deltapx,deltapy, false, height);
			break;
		case 'x':
			i = new LevelExit(x, y,deltapx,deltapy, true, height);
			break;
		case 'm':
			i = new HiddenTrigger(x, y,deltapx,deltapy, true, height);
			break;
		case 'u':
			i = new WaterPuddle(x, y,deltapx, deltapy, false, height);
			break;
		case 'b':
			i = new WoodBridge(x, y, deltapx, deltapy, false, height);
			break;
		case 'C':
			i = new Carpet(x, y,deltapx, deltapy, false, height);
			break;
		}
		return i;
	}
	
}
