package pizzatales;

public class ItemFactory {
	private final static String itemslist = "pha";
	
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
		case 'a':
			i = new HealthPotion(x,y);
			break;
		case 'h':
			i = new ArmorPotion(x,y);
			break;
		}
		return i;
	}
	
}
