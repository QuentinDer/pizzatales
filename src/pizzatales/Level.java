package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Level {

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
	
	public static void bitmask(int level, Image[][] background) {
		switch ((level-1)/4+1) {
		case 1:
			Image[] cornerset = new Image[16];
			cornerset[0] = new ImageIcon(StartingClass.class.getResource("/data/dirt0.png")).getImage();
			cornerset[1] = new ImageIcon(StartingClass.class.getResource("/data/dirt1.png")).getImage();
			cornerset[2] = new ImageIcon(StartingClass.class.getResource("/data/dirt2.png")).getImage();
			cornerset[3] = new ImageIcon(StartingClass.class.getResource("/data/dirt3.png")).getImage();
			cornerset[4] = new ImageIcon(StartingClass.class.getResource("/data/dirt4.png")).getImage();
			cornerset[5] = new ImageIcon(StartingClass.class.getResource("/data/dirt5.png")).getImage();
			cornerset[6] = new ImageIcon(StartingClass.class.getResource("/data/dirt6.png")).getImage();
			cornerset[7] = new ImageIcon(StartingClass.class.getResource("/data/dirt7.png")).getImage();
			cornerset[8] = new ImageIcon(StartingClass.class.getResource("/data/dirt8.png")).getImage();
			cornerset[9] = new ImageIcon(StartingClass.class.getResource("/data/dirt9.png")).getImage();
			cornerset[10] = new ImageIcon(StartingClass.class.getResource("/data/dirt10.png")).getImage();
			cornerset[11] = new ImageIcon(StartingClass.class.getResource("/data/dirt11.png")).getImage();
			cornerset[12] = new ImageIcon(StartingClass.class.getResource("/data/dirt12.png")).getImage();
			cornerset[13] = new ImageIcon(StartingClass.class.getResource("/data/dirt13.png")).getImage();
			cornerset[14] = new ImageIcon(StartingClass.class.getResource("/data/dirt14.png")).getImage();
			cornerset[15] = BackgroundFactory.dirt;
			MapUtil.bitmask(background, BackgroundFactory.dirt, BackgroundFactory.grass, cornerset);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		}
	}
}
