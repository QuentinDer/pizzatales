package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class Level {

	public static Image[] dirtset = new Image[16];
	public static Image[] mountainset = new Image[16];
	public static Image[] iceset = new Image[16];
	public static Image[] icesetm = new Image[16];
	
	public static String getMapName(int level) {
		if (level == -1)
			return "map1.txt";
		return "L"+ ((level-1)/4+1) + ((level-1)%4+1)+".txt";
	}
	
	public static String getClip(int level) {
		String ans  = "";
		switch ((level-1)/4+1) {
		case 1:
			ans = "Soundtrack1.wav";
			break;
		case 2:
			ans = "Soundtrack2.wav";
			break;
		case 3:
			ans = "Soundtrack3.wav";
			break;
		}
		return ans;
	}

	public static String getBackground(int level) {
		String ans = "";
		switch ((level-1)/4+1) {
		case 1:
			//ans = "background1.png";
			ans = "grass.png";
			break;
		case 2:
			ans = "cavefloor.png";
			break;
		case 3:
			ans = "dirt.png";
			break;
		case 4:
			ans = "bricktile.png";
			break;
		case 5:
			ans = "mountainfloor.png";
			break;
		case 15:
			ans = "mountainfloor.png";
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
		case 5:
			ans = StartingClass.tilePineTree;
		}
		return ans;
	}
	
	public static ArrayList<Firearm> getPizzaBoxFirearms(int level) {
		ArrayList<Firearm> firearms = new ArrayList<Firearm>();
		switch (level) {
		case 1:
			firearms.add(new Shotgun());
			break;
		case 5:
			firearms.add(new Rifle());
			break;
		case 10:
			firearms.add(new Smg());
			break;
		case 14:
			firearms.add(new Rocket());
			break;
		case 17:
			firearms.add(new Flamer());
			break;
		}
		return firearms;
	}
	
	public static ArrayList<Armor> getPizzaBoxArmors(int level) {
		ArrayList<Armor> armors = new ArrayList<Armor>();
		switch(level) {
		case 4:
			armors.add(new CheeseArmor());
			break;
		case 8:
			armors.add(new HawaiiArmor());
			break;
		case 12:
			armors.add(new ChicagoArmor());
			break;
		case 16:
			armors.add(new MargheritaArmor());
			break;
		}
		return armors;
	}
	
	public static ArrayList<Hat> getPizzaBoxHats(int level) {
		ArrayList<Hat> hats = new ArrayList<Hat>();
		switch(level) {
		case 2:
			hats.add(new HatBowler());
			break;
		case 6:
			hats.add(new HatBaseball());
			break;
		case 11:
			hats.add(new HatFedora());
			break;
		case 13:
			hats.add(new HatSherlock());
			break;
		case 15:
			hats.add(new HatPanama());
			break;
		case 19:
			hats.add(new HatTop());
			break;
		}
		return hats;
	}
	
	public static void bitmask(int level, Image[][] background, Image[][][] mask) {
		//Image[] cornerset = new Image[16];
		for (int i = 0; i < StartingClass.width; i++) {
			for (int j = 0; j < StartingClass.height; j++) {
				for (int h = 0; h < 3; h++) {
					mask[i][j][h] = null;
				}
			}
		}
		switch ((level-1)/4+1) {
		case 1:
			MapUtil.bitmask(background, mask, BackgroundFactory.dirt, BackgroundFactory.grass, dirtset,0);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			MapUtil.bitmask(background, mask, BackgroundFactory.mountain, BackgroundFactory.snow, mountainset,0);
			MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.mountain, icesetm,1);
			MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.snow, iceset,2);
			break;
		case 15:
			MapUtil.bitmask(background, mask, BackgroundFactory.mountain, BackgroundFactory.snow, mountainset,0);
			MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.mountain, icesetm,1);
			MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.snow, iceset,2);
			break;
		}
	}
	
	public static void bitmask(int level, Image[][] background, Image[][][] mask, int minx, int maxx, int miny, int maxy) {
		//Image[] cornerset = new Image[16];
		for (int i = minx; i <= maxx; i++) {
			for (int j = miny; j <= maxy; j++) {
				for (int h = 0; h < 3; h++) {
					mask[i][j][h] = null;
				}
			}
		}
		switch ((level-1)/4+1) {
		case 1:
			MapUtil.bitmask(background, mask, BackgroundFactory.dirt, BackgroundFactory.grass, dirtset,0, minx, maxx, miny, maxy);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			MapUtil.bitmask(background, mask, BackgroundFactory.mountain, BackgroundFactory.snow, mountainset,0, minx, maxx, miny, maxy);
			MapUtil.bitmask(background, mask, BackgroundFactory.dirt, BackgroundFactory.grass, dirtset,0, minx, maxx, miny, maxy);
			/*MapUtil.bitmask(background, mask, BackgroundFactory.mountain, BackgroundFactory.grass, dirtset, 0, minx, maxx, miny, maxy);
			MapUtil.bitmask(background, mask, BackgroundFactory.snow, BackgroundFactory.grass, dirtset, 0, minx, maxx, miny, maxy);*/
			MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.grass, dirtset,1, minx, maxx, miny, maxy);
			MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.mountain, icesetm,1, minx, maxx, miny, maxy);
			MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.snow, iceset,2, minx, maxx, miny, maxy);
			MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.grass, dirtset,1, minx, maxx, miny, maxy);
			MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.mountain, icesetm,1, minx, maxx, miny, maxy);
			MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.snow, iceset,2, minx, maxx, miny, maxy);
			break;
		case 15:
			MapUtil.bitmask(background, mask, BackgroundFactory.mountain, BackgroundFactory.snow, mountainset,0, minx, maxx, miny, maxy);
			MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.mountain, icesetm,1, minx, maxx, miny, maxy);
			MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.snow, iceset,2, minx, maxx, miny, maxy);
			break;
		}
	}
}
