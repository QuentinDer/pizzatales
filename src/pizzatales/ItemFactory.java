package pizzatales;

public class ItemFactory {
	private final static String itemslist = "phailoxmubNI6789";
	
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
		/*case 'C':
			i = new Carpet(x, y,deltapx, deltapy, false, height);
			break;*/
		case 'N':
			i = new SnowBank(x, y,deltapx, deltapy, false, height);
			break;
		case 'I':
			i = new Ice(x, y,deltapx, deltapy, false, height);
			break;
		case '6':
			i = new BoostCheese(x, y,deltapx, deltapy, true, height);
			break;
		case '7':
			i = new BoostBacon(x, y,deltapx, deltapy, true, height);
			break;
		case '8':
			i = new BoostGarlic(x, y,deltapx, deltapy, true, height);
			break;
		case '9':
			i = new BoostBasil(x, y,deltapx, deltapy, true, height);
			break;
		}
		return i;
	}
	
}
