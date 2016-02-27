package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class Level {

	public static String getMapName(int level) {
		if (level == -1)
			return "map1.txt";
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
	
	public static Image getHidingImage(int level) {
		Image ans = null;
		switch ((level-1)/4+1) {
		case 1:
			ans = StartingClass.tileTree;
			break;
		case 2:
			ans = StartingClass.tileStalag;
			break;
		}
		return ans;
	}
	
	public static ArrayList<Firearm> getPizzaBoxFirearms(int level) {
		ArrayList<Firearm> firearms = new ArrayList<Firearm>();
		switch (level) {
		case 0:
			firearms.add(new Shotgun());
			break;
		}
		return firearms;
	}
	
	public static ArrayList<Armor> getPizzaBoxArmors(int level) {
		ArrayList<Armor> armors = new ArrayList<Armor>();
		switch(level) {
		case 0:
			armors.add(new CheeseArmor());
			break;
		}
		return armors;
	}
}
