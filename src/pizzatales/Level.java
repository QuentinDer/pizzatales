package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class Level {

	public static Image[] dirtset = new Image[16];
	public static Image[] dirtiset = new Image[16];
	public static Image[] mountainset = new Image[16];
	public static Image[] iceset = new Image[16];
	public static Image[] icesetm = new Image[16];
	public static Image[] caveset = new Image[16];
	public static Image[] brickset = new Image[16];
	public static Image[] skyset = new Image[16];
	
	public static Image cutsceneboss1, cutsceneboss2, cutsceneboss3, cutsceneboss4, cutsceneboss5, cutsceneboss6, 
		cutsceneboss7;
	
	public static String getMapName(int level) {
		if (level == -1)
			return "map1.txt";
		return "L"+ ((level-1)/4+1) + ((level-1)%4+1)+".txt";
	}
	
	public static String getClip(int level) {
		String ans  = "";
		switch ((level-1)/4+1) {
		case 1:
			ans = "Grease Monkeys.wav";
			break;
		case 2:
			ans = "Smash and Grab.wav";
			break;
		case 3:
			ans = "Heavy Hitter.wav";
			break;
		case 4:
			ans = "Organ Donor.wav";
			break;
		case 5:
			ans = "Thrashing Around.wav";
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
			MapUtil.bitmask(background, mask, BackgroundFactory.lava, BackgroundFactory.cave, caveset,0);
			MapUtil.bitmask(background, mask, BackgroundFactory.waterflow, BackgroundFactory.cave, caveset,1);
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			MapUtil.bitmask(background, mask, BackgroundFactory.snow, BackgroundFactory.sky, skyset, 0);
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
	
	public static void bitmask(int level, Image[][] background, Image[][][] mask, int minx, int maxx, int miny, int maxy, int phase) {
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
			MapUtil.bitmask(background, mask, BackgroundFactory.lava, BackgroundFactory.cave, caveset,1, minx, maxx, miny, maxy);
			MapUtil.bitmask(background, mask, BackgroundFactory.waterflow, BackgroundFactory.cave, caveset,2, minx, maxx, miny, maxy);
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			
			switch(phase) {
			case 1:
				MapUtil.bitmask(background, mask, BackgroundFactory.snow, BackgroundFactory.sky, skyset, 0, minx, maxx, miny, maxy);
				break;
			case 2:
				MapUtil.bitmask(background, mask, BackgroundFactory.snow, BackgroundFactory.sky, skyset, 1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.dirt, BackgroundFactory.sky, skyset, 1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.mountain, BackgroundFactory.grass, dirtset, 0, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.snow, BackgroundFactory.grass, dirtset, 0, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.dirt, BackgroundFactory.grass, dirtset,0, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.grass, dirtset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.dirt, dirtiset,2, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.grass, dirtset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.dirt, dirtiset,2, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.dirt, BackgroundFactory.snow, iceset,2, minx, maxx, miny, maxy);
				break;
			case 3:
				MapUtil.bitmask(background, mask, BackgroundFactory.dirt, BackgroundFactory.sky, skyset, 1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.dirt, BackgroundFactory.grass, dirtset,0, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.grass, dirtset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.dirt, dirtiset,2, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.grass, dirtset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.dirt, dirtiset,2, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.dirt, BackgroundFactory.snow, iceset,2, minx, maxx, miny, maxy);
				break;
			case 4:
				MapUtil.bitmask(background, mask, BackgroundFactory.dirt, BackgroundFactory.grass, dirtset,0, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.dirt, BackgroundFactory.sky, skyset, 1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.lava, BackgroundFactory.sky, skyset, 1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.cave, BackgroundFactory.grass, dirtset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.cave, BackgroundFactory.dirt, dirtiset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.grass, dirtset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.dirt, dirtiset,2, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.grass, dirtset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.dirt, dirtiset,2, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.cave, caveset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.cave, caveset,2, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.dirt, BackgroundFactory.snow, iceset,2, minx, maxx, miny, maxy);
				break;
			case 5:
				MapUtil.bitmask(background, mask, BackgroundFactory.lava, BackgroundFactory.sky, skyset, 1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.cave, caveset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.cave, caveset,2, minx, maxx, miny, maxy);
				break;
			case 6:
				MapUtil.bitmask(background, mask, BackgroundFactory.lava, BackgroundFactory.sky, skyset, 1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.cave, BackgroundFactory.dirt, dirtiset,0, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.cave, caveset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.cave, caveset,2, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.dirt, dirtiset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.dirt, dirtiset,2, minx, maxx, miny, maxy);
				break;
			case 7:
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.dirt, dirtiset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.dirt, dirtiset,2, minx, maxx, miny, maxy);
				break;
			case 8:
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.dirt, dirtiset,2, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.dirt, dirtiset,2, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.brick, brickset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.brick, brickset,2, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.carpet, BackgroundFactory.sky, skyset, 1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.brick, BackgroundFactory.dirt, dirtiset,0, minx, maxx, miny, maxy);
				break;
			case 9:
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.brick, brickset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.brick, brickset,2, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.carpet, BackgroundFactory.sky, skyset, 1, minx, maxx, miny, maxy);
				break;
			case 10:
				MapUtil.bitmask(background, mask, BackgroundFactory.mountain, BackgroundFactory.brick, brickset,0, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.brick, brickset,1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, SummonedIce.healingice, BackgroundFactory.brick, brickset,2, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.carpet, BackgroundFactory.sky, skyset, 1, minx, maxx, miny, maxy);
				MapUtil.bitmask(background, mask, BackgroundFactory.snow, BackgroundFactory.sky, skyset, 0, minx, maxx, miny, maxy);
				break;
			case 11:
				MapUtil.bitmask(background, mask, BackgroundFactory.snow, BackgroundFactory.sky, skyset, 0, minx, maxx, miny, maxy);
				break;
			}
			MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.sky, skyset, 0, minx, maxx, miny, maxy);
			
			MapUtil.bitmask(background, mask, BackgroundFactory.mountain, BackgroundFactory.snow, mountainset,0, minx, maxx, miny, maxy);
			
			MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.mountain, icesetm,1, minx, maxx, miny, maxy);
			MapUtil.bitmask(background, mask, BackgroundFactory.ice, BackgroundFactory.snow, iceset,2, minx, maxx, miny, maxy);
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
	
	public static Cutscene getArenaCutscene(int level) {
		Cutscene cut = new Cutscene();
		switch ((level-1)/4+1) {
		case 1:	
			Scene sirtomato1 = new Scene("\"Huh?\"",3,"Sir Tomato",cutsceneboss1,true,true);
			Scene sirtomato2 = new Scene("\"How did you get here, you filth?!\"",3,"Sir Tomato",cutsceneboss1,false,false);
			Scene sirtomato3 = new Scene("\"Oh, no matter, this will take but a minute, and you’ll join the rest of your kind.\"",3,"Sir Tomato",cutsceneboss1,false,false);
			cut.addScene(sirtomato1);
			cut.addScene(sirtomato2);
			cut.addScene(sirtomato3);
			break;
		case 2:
			Scene mushroomwizard1 = new Scene("\"I have seen your travels, young Pizza. You have fought bravely to get here.\"",3,"Mushroom Wizard",cutsceneboss2,true,true);
			Scene mushroomwizard2 = new Scene("\"You have caught everyone by surprise, we didn’t expect much of a fight back.\"",3,"Mushroom Wizard",cutsceneboss2,false,false);
			Scene mushroomwizard3 = new Scene("\"But the surprise is over now. You won’t leave this cave alive.\"",3,"Mushroom Wizard",cutsceneboss2,false,false);
			cut.addScene(mushroomwizard1);
			cut.addScene(mushroomwizard2);
			cut.addScene(mushroomwizard3);
			break;
		case 3:
			Scene reaper1 = new Scene("\"So you’re the one who’s been giving my troops shit, eh?\"",3,"Carolina Reaper",cutsceneboss3,true,true);
			Scene reaper2 = new Scene("\"Your efforts are about to go up in flames!\"",3,"Carolina Reaper",cutsceneboss4,false,false);
			Scene reaper3 = new Scene("\"Heh heh heh…\"",3,"Carolina Reaper",cutsceneboss3,false,false);
			Scene reaper4 = new Scene("\"You get it?\"",3,"Carolina Reaper",cutsceneboss3,false,false);
			Scene reaper5 = new Scene("\"Cause I’m going to burn you up!\"",3,"Carolina Reaper",cutsceneboss4,false,false);
			Scene reaper6 = new Scene("\"Heh heh heh…\"",3,"Carolina Reaper",cutsceneboss3,false,false);
			cut.addScene(reaper1);
			cut.addScene(reaper2);
			cut.addScene(reaper3);
			cut.addScene(reaper4);
			cut.addScene(reaper5);
			cut.addScene(reaper6);
			break;
		case 4:
			Scene ongarl = new Scene("\"The King isn’t here young slice, so I guess you’re stuck with us.\"",3,"Garlnstein",cutsceneboss5,true,true);
			Scene ongar2 = new Scene("\"Yeah, you’ll never find the King in the mountain !\"",3,"Oniough",cutsceneboss6,true,true);
			Scene ongar3 = new Scene("\"…Oni…\"",3,"Garlnstein",cutsceneboss5,false,false);
			Scene ongar4 = new Scene("\"Oh, I said it? Ah damn it! Well, let’s just kill the pizza Garlie.\"",3,"Oniough",cutsceneboss6,false,false);
			Scene ongar5 = new Scene("\"…*sigh* … fine, let’s get this over with.\"",3,"Garlnstein",cutsceneboss5,false,false);
			cut.addScene(ongarl);
			cut.addScene(ongar2);
			cut.addScene(ongar3);
			cut.addScene(ongar4);
			cut.addScene(ongar5);
			break;
		case 5:
			Scene king1 = new Scene("\"You are too late, little slice.\"",3,"Kale King",cutsceneboss7,true,true);
			Scene king2 = new Scene("\"With the artifact I have become immensely powerful.\"",3,"Kale King",cutsceneboss7,false,false);
			Scene king3 = new Scene("\"You fools! You had no clue of the power emanating from it.\"",3,"Kale King",cutsceneboss7,false,false);
			Scene king4 = new Scene("\"Now you will witness the power you no longer possess!\"",3,"Kale King",cutsceneboss7,false,false);
			cut.addScene(king1);
			cut.addScene(king2);
			cut.addScene(king3);
			cut.addScene(king4);
			break;
		}
		return cut;
	}
}
