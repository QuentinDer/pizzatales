package pizzatales;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ResourcesManager {

	
	static public void loadResources() {

		// Image Setups
		
		//Player.hurtSound = StartingClass.class.getResource("/data/ugh.wav");

		CheeseArmor.staysprite1 = new ImageIcon(StartingClass.class.getResource("/data/cheese1.png")).getImage();
		CheeseArmor.staysprite2 = new ImageIcon(StartingClass.class.getResource("/data/cheese2.png")).getImage();
		CheeseArmor.movespriteLeft1 = new ImageIcon(StartingClass.class.getResource("/data/cheeseLeft1.png")).getImage();
		CheeseArmor.movespriteLeft2 = new ImageIcon(StartingClass.class.getResource("/data/cheeseLeft2.png")).getImage();
		CheeseArmor.movespriteRight1 = new ImageIcon(StartingClass.class.getResource("/data/cheeseRight1.png")).getImage();
		CheeseArmor.movespriteRight2 = new ImageIcon(StartingClass.class.getResource("/data/cheeseRight2.png")).getImage();
		CheeseArmor.deathSprite = new ImageIcon(StartingClass.class.getResource("/data/cheeseDead.png")).getImage();
		CheeseArmor.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addcheese.png")).getImage();
		ChicagoArmor.staysprite1 = new ImageIcon(StartingClass.class.getResource("/data/chicago1.png")).getImage();
		ChicagoArmor.staysprite2 = new ImageIcon(StartingClass.class.getResource("/data/chicago2.png")).getImage();
		ChicagoArmor.movespriteLeft1 = new ImageIcon(StartingClass.class.getResource("/data/chicagoLeft1.png")).getImage();
		ChicagoArmor.movespriteLeft2 = new ImageIcon(StartingClass.class.getResource("/data/chicagoLeft2.png")).getImage();
		ChicagoArmor.movespriteRight1 = new ImageIcon(StartingClass.class.getResource("/data/chicagoRight1.png")).getImage();
		ChicagoArmor.movespriteRight2 = new ImageIcon(StartingClass.class.getResource("/data/chicagoRight2.png")).getImage();
		ChicagoArmor.deathSprite = new ImageIcon(StartingClass.class.getResource("/data/chicagoDead.png")).getImage();
		ChicagoArmor.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addchicago.png")).getImage();
		HawaiiArmor.staysprite1 = new ImageIcon(StartingClass.class.getResource("/data/hawaii1.png")).getImage();
		HawaiiArmor.staysprite2 = new ImageIcon(StartingClass.class.getResource("/data/hawaii2.png")).getImage();
		HawaiiArmor.movespriteLeft1 = new ImageIcon(StartingClass.class.getResource("/data/hawaiiLeft1.png")).getImage();
		HawaiiArmor.movespriteLeft2 = new ImageIcon(StartingClass.class.getResource("/data/hawaiiLeft2.png")).getImage();
		HawaiiArmor.movespriteRight1 = new ImageIcon(StartingClass.class.getResource("/data/hawaiiRight1.png")).getImage();
		HawaiiArmor.movespriteRight2 = new ImageIcon(StartingClass.class.getResource("/data/hawaiiRight2.png")).getImage();
		HawaiiArmor.deathSprite = new ImageIcon(StartingClass.class.getResource("/data/hawaiiDead.png")).getImage();
		HawaiiArmor.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addhawaii.png")).getImage();
		MargheritaArmor.staysprite1 = new ImageIcon(StartingClass.class.getResource("/data/margherita1.png")).getImage();
		MargheritaArmor.staysprite2 = new ImageIcon(StartingClass.class.getResource("/data/margherita2.png")).getImage();
		MargheritaArmor.movespriteLeft1 = new ImageIcon(StartingClass.class.getResource("/data/margheritaLeft1.png")).getImage();
		MargheritaArmor.movespriteLeft2 = new ImageIcon(StartingClass.class.getResource("/data/margheritaLeft2.png")).getImage();
		MargheritaArmor.movespriteRight1 = new ImageIcon(StartingClass.class.getResource("/data/margheritaRight1.png")).getImage();
		MargheritaArmor.movespriteRight2 = new ImageIcon(StartingClass.class.getResource("/data/margheritaRight2.png")).getImage();
		MargheritaArmor.deathSprite = new ImageIcon(StartingClass.class.getResource("/data/margheritaDead.png")).getImage();
		MargheritaArmor.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addmargherita.png")).getImage();
		PepperoniArmor.staysprite1 = new ImageIcon(StartingClass.class.getResource("/data/pepperoni1.png")).getImage();
		PepperoniArmor.staysprite2 = new ImageIcon(StartingClass.class.getResource("/data/pepperoni2.png")).getImage();
		PepperoniArmor.movespriteLeft1 = new ImageIcon(StartingClass.class.getResource("/data/pepperoniLeft1.png")).getImage();
		PepperoniArmor.movespriteLeft2 = new ImageIcon(StartingClass.class.getResource("/data/pepperoniLeft2.png")).getImage();
		PepperoniArmor.movespriteRight1 = new ImageIcon(StartingClass.class.getResource("/data/pepperoniRight1.png")).getImage();
		PepperoniArmor.movespriteRight2 = new ImageIcon(StartingClass.class.getResource("/data/pepperoniRight2.png")).getImage();
		PepperoniArmor.deathSprite = new ImageIcon(StartingClass.class.getResource("/data/pepperoniDead.png")).getImage();

		StartingClass.tileTree = new ImageIcon(StartingClass.class.getResource("/data/tree.png")).getImage();
		//tileGrass = new ImageIcon(StartingClass.class.getResource("/data/grass.png")).getImage();
		StartingClass.tileWall = new ImageIcon(StartingClass.class.getResource("/data/wall.png")).getImage();
		StartingClass.tileCave = new ImageIcon(StartingClass.class.getResource("/data/cave.png")).getImage();
		StartingClass.tileStalag = new ImageIcon(StartingClass.class.getResource("/data/stalagmites.png")).getImage();
		StartingClass.tileCaveRock = new ImageIcon(StartingClass.class.getResource("/data/caverock.png")).getImage();
		StartingClass.tileGate = new ImageIcon(StartingClass.class.getResource("/data/gate.png")).getImage();
		StartingClass.tileCaveExit = new ImageIcon(StartingClass.class.getResource("/data/caveexit.png")).getImage();
		StartingClass.tileRock = new ImageIcon(StartingClass.class.getResource("/data/rock.png")).getImage();
		StartingClass.tileDecoy = new ImageIcon(StartingClass.class.getResource("/data/decoy.png")).getImage();
		StartingClass.tilePikes = new ImageIcon(StartingClass.class.getResource("/data/pikes.png")).getImage();
		StartingClass.tileFlag = new ImageIcon(StartingClass.class.getResource("/data/flag.png")).getImage();
		StartingClass.tileBarrel = new ImageIcon(StartingClass.class.getResource("/data/barrel.png")).getImage();
		StartingClass.tileCandelabrum = new ImageIcon(StartingClass.class.getResource("/data/candelabrum.png")).getImage();
		StartingClass.tileCrate = new ImageIcon(StartingClass.class.getResource("/data/crate.png")).getImage();
		StartingClass.tileChest = new ImageIcon(StartingClass.class.getResource("/data/chest.png")).getImage();
		StartingClass.tileBlack = new ImageIcon(StartingClass.class.getResource("/data/blacktile.png")).getImage();
		StartingClass.tileChestOpen = new ImageIcon(StartingClass.class.getResource("/data/chestopen.png")).getImage();
		StartingClass.tilePineTree = new ImageIcon(StartingClass.class.getResource("/data/pinetree.png")).getImage();
		StartingClass.tileSnowRock = new ImageIcon(StartingClass.class.getResource("/data/snowrock.png")).getImage();
		StartingClass.tileMudWall = new ImageIcon(StartingClass.class.getResource("/data/mudwall.png")).getImage();
		BackgroundFactory.sky = new ImageIcon(StartingClass.class.getResource("/data/sky.png")).getImage();
		
		BackgroundFactory.grass = new ImageIcon(StartingClass.class.getResource("/data/grass.png")).getImage();
		BackgroundFactory.cave = new ImageIcon(StartingClass.class.getResource("/data/cavefloor.png")).getImage();
		BackgroundFactory.dirt = new ImageIcon(StartingClass.class.getResource("/data/dirt.png")).getImage();
		BackgroundFactory.brick = new ImageIcon(StartingClass.class.getResource("/data/bricktile.png")).getImage();
		BackgroundFactory.mountain = new ImageIcon(StartingClass.class.getResource("/data/mountainfloor.png")).getImage();
		BackgroundFactory.lava = new ImageIcon(StartingClass.class.getResource("/data/puddlelava.png")).getImage();
		
		for (int i = 1; i < 16; i++)
			Level.dirtset[i] = new ImageIcon(StartingClass.class.getResource("/data/dirt"+i+".png")).getImage();
		for (int i = 1; i < 16; i++)
			Level.dirtiset[i] = new ImageIcon(StartingClass.class.getResource("/data/dirti"+i+".png")).getImage();
		
		for (int i = 1; i < 16; i++)
			Level.mountainset[i] = new ImageIcon(StartingClass.class.getResource("/data/mountainfloor"+i+".png")).getImage();
		
		for (int i = 1; i < 16; i++)
			Level.iceset[i] = new ImageIcon(StartingClass.class.getResource("/data/ice"+i+".png")).getImage();
		for (int i = 1; i < 16; i++)
			Level.icesetm[i] = new ImageIcon(StartingClass.class.getResource("/data/icem"+i+".png")).getImage();
		
		for (int i = 1; i < 16; i++)
			Level.caveset[i] = new ImageIcon(StartingClass.class.getResource("/data/cave"+i+".png")).getImage();
		
		for (int i = 1; i < 16; i++)
			Level.brickset[i] = new ImageIcon(StartingClass.class.getResource("/data/brick"+i+".png")).getImage();
		
		Level.skyset[1] = new ImageIcon(StartingClass.class.getResource("/data/skyset1.png")).getImage();
		Level.skyset[3] = new ImageIcon(StartingClass.class.getResource("/data/skyset3.png")).getImage();
		Level.skyset[5] = new ImageIcon(StartingClass.class.getResource("/data/skyset5.png")).getImage();
		
		StartingClass.blooddrop = new ImageIcon(StartingClass.class.getResource("/data/blooddrop.png")).getImage();
		Gun.leftSprite = new ImageIcon(StartingClass.class.getResource("/data/pistol1.png")).getImage();
		Gun.rightSprite = new ImageIcon(StartingClass.class.getResource("/data/pistol2.png")).getImage();
		Gun.upSprite = new ImageIcon(StartingClass.class.getResource("/data/pistol4.png")).getImage();
		Gun.downSprite = new ImageIcon(StartingClass.class.getResource("/data/pistol3.png")).getImage();
		Gun.leftDownSprite = new ImageIcon(StartingClass.class.getResource("/data/pistol7.png")).getImage();
		Gun.leftUpSprite = new ImageIcon(StartingClass.class.getResource("/data/pistol8.png")).getImage();
		Gun.rightDownSprite = new ImageIcon(StartingClass.class.getResource("/data/pistol6.png")).getImage();
		Gun.rightUpSprite = new ImageIcon(StartingClass.class.getResource("/data/pistol5.png")).getImage();
		//Gun.url = StartingClass.class.getResource("/data/pistol.wav");
		Bullet.bulletsprite = new ImageIcon(StartingClass.class.getResource("/data/pistolprojectile.png")).getImage();
		Shotgun.leftSprite = new ImageIcon(StartingClass.class.getResource("/data/shotgun1.png")).getImage();
		Shotgun.rightSprite = new ImageIcon(StartingClass.class.getResource("/data/shotgun2.png")).getImage();
		Shotgun.upSprite = new ImageIcon(StartingClass.class.getResource("/data/shotgun4.png")).getImage();
		Shotgun.downSprite = new ImageIcon(StartingClass.class.getResource("/data/shotgun3.png")).getImage();
		Shotgun.leftDownSprite = new ImageIcon(StartingClass.class.getResource("/data/shotgun7.png")).getImage();
		Shotgun.leftUpSprite = new ImageIcon(StartingClass.class.getResource("/data/shotgun8.png")).getImage();
		Shotgun.rightDownSprite = new ImageIcon(StartingClass.class.getResource("/data/shotgun6.png")).getImage();
		Shotgun.rightUpSprite = new ImageIcon(StartingClass.class.getResource("/data/shotgun5.png")).getImage();
		//Shotgun.url = StartingClass.class.getResource("/data/shotgun.wav");
		Shotgun.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addshotgun.png")).getImage();
		ShotgunBullet.bulletsprite = new ImageIcon(StartingClass.class.getResource("/data/shotgunprojectile.png")).getImage();
		Rifle.leftSprite = new ImageIcon(StartingClass.class.getResource("/data/rifle1.png")).getImage();
		Rifle.rightSprite = new ImageIcon(StartingClass.class.getResource("/data/rifle2.png")).getImage();
		Rifle.upSprite = new ImageIcon(StartingClass.class.getResource("/data/rifle4.png")).getImage();
		Rifle.leftDownSprite = new ImageIcon(StartingClass.class.getResource("/data/rifle7.png")).getImage();
		Rifle.leftUpSprite = new ImageIcon(StartingClass.class.getResource("/data/rifle8.png")).getImage();
		Rifle.rightDownSprite = new ImageIcon(StartingClass.class.getResource("/data/rifle6.png")).getImage();
		Rifle.rightUpSprite = new ImageIcon(StartingClass.class.getResource("/data/rifle5.png")).getImage();
		//Rifle.url = StartingClass.class.getResource("/data/rifle.wav");
		Rifle.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addrifle.png")).getImage();
		Rifle.downSprite = new ImageIcon(StartingClass.class.getResource("/data/rifle3.png")).getImage();
		RifleBullet.bulletsprite = new ImageIcon(StartingClass.class.getResource("/data/rifleprojectile.png")).getImage();
		Flamer.leftSprite = new ImageIcon(StartingClass.class.getResource("/data/flamer1.png")).getImage();
		Flamer.rightSprite = new ImageIcon(StartingClass.class.getResource("/data/flamer2.png")).getImage();
		Flamer.downSprite = new ImageIcon(StartingClass.class.getResource("/data/flamer3.png")).getImage();
		Flamer.upSprite = new ImageIcon(StartingClass.class.getResource("/data/flamer4.png")).getImage();
		Flamer.leftDownSprite = new ImageIcon(StartingClass.class.getResource("/data/flamer7.png")).getImage();
		Flamer.leftUpSprite = new ImageIcon(StartingClass.class.getResource("/data/flamer8.png")).getImage();
		Flamer.rightDownSprite = new ImageIcon(StartingClass.class.getResource("/data/flamer6.png")).getImage();
		Flamer.rightUpSprite = new ImageIcon(StartingClass.class.getResource("/data/flamer5.png")).getImage();
		//Flamer.url = StartingClass.class.getResource("/data/flamer.wav");
		Flamer.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addflamer.png")).getImage();
		FlamerFlame.bulletsprite = new ImageIcon(StartingClass.class.getResource("/data/flamerprojectile.png")).getImage();
		Rocket.leftSprite = new ImageIcon(StartingClass.class.getResource("/data/rocket1.png")).getImage();
		Rocket.rightSprite = new ImageIcon(StartingClass.class.getResource("/data/rocket2.png")).getImage();
		Rocket.downSprite = new ImageIcon(StartingClass.class.getResource("/data/rocket3.png")).getImage();
		Rocket.upSprite = new ImageIcon(StartingClass.class.getResource("/data/rocket4.png")).getImage();
		//Rocket.url = StartingClass.class.getResource("/data/rocket.wav");
		Rocket.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addrocket.png")).getImage();
		Rocket.leftSpriteUp = new ImageIcon(StartingClass.class.getResource("/data/rocket8.png")).getImage();
		Rocket.rightUpSprite = new ImageIcon(StartingClass.class.getResource("/data/rocket5.png")).getImage();
		Rocket.leftDownSprite = new ImageIcon(StartingClass.class.getResource("/data/rocket7.png")).getImage();
		Rocket.rightDownSprite = new ImageIcon(StartingClass.class.getResource("/data/rocket6.png")).getImage();
		BazookaBullet.bulletspriteLeft = new ImageIcon(StartingClass.class.getResource("/data/rocketprojectileleft.png"))
				.getImage();
		BazookaBullet.bulletspriteRight = new ImageIcon(StartingClass.class.getResource("/data/rocketprojectileright.png"))
				.getImage();
		BazookaBullet.bulletspriteUp = new ImageIcon(StartingClass.class.getResource("/data/rocketprojectileup.png")).getImage();
		BazookaBullet.bulletspriteDown = new ImageIcon(StartingClass.class.getResource("/data/rocketprojectiledown.png"))
				.getImage();
		BazookaBullet.bulletleftdown = new ImageIcon(StartingClass.class.getResource("/data/rocketprojectiledownleft.png")).getImage();
		BazookaBullet.bulletrightdown = new ImageIcon(StartingClass.class.getResource("/data/rocketprojectiledownright.png")).getImage();
		BazookaBullet.bulletrightup = new ImageIcon(StartingClass.class.getResource("/data/rocketprojectileupright.png")).getImage();
		BazookaBullet.bulletleftup = new ImageIcon(StartingClass.class.getResource("/data/rocketprojectileupleft.png")).getImage();
		Smg.leftSprite = new ImageIcon(StartingClass.class.getResource("/data/smg1.png")).getImage();
		Smg.rightSprite = new ImageIcon(StartingClass.class.getResource("/data/smg2.png")).getImage();
		Smg.downSprite = new ImageIcon(StartingClass.class.getResource("/data/smg3.png")).getImage();
		Smg.upSprite = new ImageIcon(StartingClass.class.getResource("/data/smg4.png")).getImage();
		Smg.leftDownSprite = new ImageIcon(StartingClass.class.getResource("/data/smg7.png")).getImage();
		Smg.leftUpSprite = new ImageIcon(StartingClass.class.getResource("/data/smg8.png")).getImage();
		Smg.rightDownSprite = new ImageIcon(StartingClass.class.getResource("/data/smg6.png")).getImage();
		Smg.rightUpSprite = new ImageIcon(StartingClass.class.getResource("/data/smg5.png")).getImage();
		//Smg.url = StartingClass.class.getResource("/data/smg.wav");
		Smg.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addsmg.png")).getImage();
		SmgBullet.bulletsprite = new ImageIcon(StartingClass.class.getResource("/data/smgprojectile.png")).getImage();
		TomatoProjectile.tomatoprojectilesprite = new ImageIcon(StartingClass.class.getResource("/data/sirtomatoprojectile.png"))
				.getImage();
		MushroomWizardBall.greenball = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardgreenball.png"))
				.getImage();
		MushroomWizardBall.yellowball = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardyellowball.png"))
				.getImage();
		MushroomWizardBall.redball = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardredball.png"))
				.getImage();
		MushroomWizardBall.blueball = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardblueball.png"))
				.getImage();
		ReaperBarrelProjectile.sprite = new ImageIcon(StartingClass.class.getResource("/data/barrelprojectile.png")).getImage();
		OnioughProjectile.sprite = new ImageIcon(StartingClass.class.getResource("/data/onioughProjectile.png")).getImage();
		
		for (int i = 0; i < 32; i++)
			IceBolt.boltset[i] = new ImageIcon(StartingClass.class.getResource("/data/icebolt"+i+".png")).getImage();
		KaleKingBall.sprite = new ImageIcon(StartingClass.class.getResource("/data/kalekingBall.png")).getImage();
		KaleKingFlame.sprite = new ImageIcon(StartingClass.class.getResource("/data/darkflame.png")).getImage();

		Tato.staySprite = new ImageIcon(StartingClass.class.getResource("/data/tato1.png")).getImage();
		Tato.move1Sprite = new ImageIcon(StartingClass.class.getResource("/data/tato2.png")).getImage();
		Tato.move2Sprite = new ImageIcon(StartingClass.class.getResource("/data/tato3.png")).getImage();
		Tato.dieSprite = new ImageIcon(StartingClass.class.getResource("/data/tatoDie.png")).getImage();
		Tato.gibsSprite = new ImageIcon(StartingClass.class.getResource("/data/tatogibs.png")).getImage();
		Aubergine.staySprite = new ImageIcon(StartingClass.class.getResource("/data/aubergine1.png")).getImage();
		Aubergine.move1Sprite = new ImageIcon(StartingClass.class.getResource("/data/aubergine2.png")).getImage();
		Aubergine.move2Sprite = new ImageIcon(StartingClass.class.getResource("/data/aubergine3.png")).getImage();
		Aubergine.dieSprite = new ImageIcon(StartingClass.class.getResource("/data/auberginedead.png")).getImage();
		Aubergine.gibsSprite = new ImageIcon(StartingClass.class.getResource("/data/auberginegibs.png")).getImage();
		Broccoli.staySprite = new ImageIcon(StartingClass.class.getResource("/data/broccoli1.png")).getImage();
		Broccoli.move1Sprite = new ImageIcon(StartingClass.class.getResource("/data/broccoli2.png")).getImage();
		Broccoli.move2Sprite = new ImageIcon(StartingClass.class.getResource("/data/broccoli3.png")).getImage();
		Broccoli.dieSprite = new ImageIcon(StartingClass.class.getResource("/data/broccolidead.png")).getImage();
		Broccoli.gibsSprite = new ImageIcon(StartingClass.class.getResource("/data/broccoligibs.png")).getImage();
		Pepper.staySprite = new ImageIcon(StartingClass.class.getResource("/data/pepperLeft1.png")).getImage();
		Pepper.move1Sprite = new ImageIcon(StartingClass.class.getResource("/data/pepperLeft2.png")).getImage();
		Pepper.move2Sprite = new ImageIcon(StartingClass.class.getResource("/data/pepperLeft3.png")).getImage();
		Pepper.staySpriteRight = new ImageIcon(StartingClass.class.getResource("/data/pepperRight1.png")).getImage();
		Pepper.move1SpriteRight = new ImageIcon(StartingClass.class.getResource("/data/pepperRight2.png")).getImage();
		Pepper.move2SpriteRight = new ImageIcon(StartingClass.class.getResource("/data/pepperRight3.png")).getImage();
		Pepper.dieSprite = new ImageIcon(StartingClass.class.getResource("/data/pepperdead.png")).getImage();
		Pepper.gibsSprite = new ImageIcon(StartingClass.class.getResource("/data/peppergibs.png")).getImage();
		Mushroom.staySprite = new ImageIcon(StartingClass.class.getResource("/data/shroom1.png")).getImage();
		Mushroom.move1Sprite = new ImageIcon(StartingClass.class.getResource("/data/shroom2.png")).getImage();
		Mushroom.move2Sprite = new ImageIcon(StartingClass.class.getResource("/data/shroom3.png")).getImage();
		Mushroom.dieSprite = new ImageIcon(StartingClass.class.getResource("/data/shroomdead.png")).getImage();
		Mushroom.gibsSprite = new ImageIcon(StartingClass.class.getResource("/data/shroomgibs.png")).getImage();
		SirTomato.staySprite = new ImageIcon(StartingClass.class.getResource("/data/sirtomatoleft1.png")).getImage();
		SirTomato.move1Sprite = new ImageIcon(StartingClass.class.getResource("/data/sirtomatoleft2.png")).getImage();
		SirTomato.move2Sprite = new ImageIcon(StartingClass.class.getResource("/data/sirtomatoleft3.png")).getImage();
		SirTomato.staySpriteRight = new ImageIcon(StartingClass.class.getResource("/data/sirtomatoright1.png")).getImage();
		SirTomato.move1SpriteRight = new ImageIcon(StartingClass.class.getResource("/data/sirtomatoright2.png")).getImage();
		SirTomato.move2SpriteRight = new ImageIcon(StartingClass.class.getResource("/data/sirtomatoright3.png")).getImage();
		SirTomato.dieSprite = new ImageIcon(StartingClass.class.getResource("/data/sirtomatodead.png")).getImage();
		SirTomato.sirtomatothrowleft = new ImageIcon(StartingClass.class.getResource("/data/sirtomatothrowleft.png")).getImage();
		SirTomato.sirtomatothrowright = new ImageIcon(StartingClass.class.getResource("/data/sirtomatothrowright.png"))
				.getImage();
		SirTomato.dashSpriteLeft = new ImageIcon(StartingClass.class.getResource("/data/sirtomatodashleft.png")).getImage();
		SirTomato.dashSpriteRight = new ImageIcon(StartingClass.class.getResource("/data/sirtomatodashright.png")).getImage();
		SirTomato.slashSpriteLeft = new ImageIcon(StartingClass.class.getResource("/data/sirtomatoswipeleft.png")).getImage();
		SirTomato.slashSpriteRight = new ImageIcon(StartingClass.class.getResource("/data/sirtomatoswiperight.png")).getImage();
		SirTomato.dashLeftWindup = new ImageIcon(StartingClass.class.getResource("/data/sirtomatodashleftwindup.png")).getImage();
		SirTomato.dashRightWindup = new ImageIcon(StartingClass.class.getResource("/data/sirtomatodashrightwindup.png")).getImage();
		SirTomato.swipeLeftWindup = new ImageIcon(StartingClass.class.getResource("/data/sirtomatoswipeleftwindup.png")).getImage();
		SirTomato.swipeRightWindup = new ImageIcon(StartingClass.class.getResource("/data/sirtomatoswiperightwindup.png")).getImage();
		SirTomato.intermediateDieSprite = new ImageIcon(StartingClass.class.getResource("/data/sirtomatodying.png")).getImage();
		MushroomWizard.staySprite = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardleft1.png")).getImage();
		MushroomWizard.move1Sprite = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardleft2.png")).getImage();
		MushroomWizard.move2Sprite = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardleft3.png")).getImage();
		MushroomWizard.staySpriteRight = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardright1.png"))
				.getImage();
		MushroomWizard.move1SpriteRight = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardright2.png"))
				.getImage();
		MushroomWizard.move2SpriteRight = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardright3.png"))
				.getImage();
		MushroomWizard.dieSprite = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizarddead.png")).getImage();
		MushroomWizard.swipeLeft = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardswipeleft.png"))
				.getImage();
		MushroomWizard.swipeRight = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardswiperight.png"))
				.getImage();
		MushroomWizard.swipeDown = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardswipedown.png"))
				.getImage();
		MushroomWizard.swipeUp = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardswipeup.png")).getImage();
		MushroomWizard.shooting = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardshooting.png")).getImage();
		MushroomWizard.summoning = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardlavasummon.png"))
				.getImage();
		MushroomWizard.intermediateDieSprite = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizarddying.png")).getImage();
		MushroomWizard.swipeWindup = new ImageIcon(StartingClass.class.getResource("/data/mushroomwizardswipewindup.png")).getImage();
		CarolinaReaper.staySprite = new ImageIcon(StartingClass.class.getResource("/data/reaper.png")).getImage();
		CarolinaReaper.move1Sprite = new ImageIcon(StartingClass.class.getResource("/data/reaperwalk1.png")).getImage();
		CarolinaReaper.move2Sprite = new ImageIcon(StartingClass.class.getResource("/data/reaperwalk2.png")).getImage();
		CarolinaReaper.staySpriteOnFire = new ImageIcon(StartingClass.class.getResource("/data/reaperwalkfire1.png")).getImage();
		CarolinaReaper.move1SpriteOnFire = new ImageIcon(StartingClass.class.getResource("/data/reaperwalkfire2.png")).getImage();
		CarolinaReaper.move2SpriteOnFire = new ImageIcon(StartingClass.class.getResource("/data/reaperwalkfire3.png")).getImage();
		CarolinaReaper.firering = new ImageIcon(StartingClass.class.getResource("/data/reaperfirering.png")).getImage();
		CarolinaReaper.fireringOnFire = new ImageIcon(StartingClass.class.getResource("/data/reaperfirestreamwindup.png")).getImage();
		CarolinaReaper.streamleft = new ImageIcon(StartingClass.class.getResource("/data/reaperfirestreamleft.png")).getImage();
		CarolinaReaper.streamright = new ImageIcon(StartingClass.class.getResource("/data/reaperfirestreamright.png"))
				.getImage();
		CarolinaReaper.streamup = new ImageIcon(StartingClass.class.getResource("/data/reaperfirestreamup.png")).getImage();
		CarolinaReaper.streamdown = new ImageIcon(StartingClass.class.getResource("/data/reaperfirestreamdown.png")).getImage();
		CarolinaReaper.dieSprite = new ImageIcon(StartingClass.class.getResource("/data/reaperdead.png")).getImage();
		CarolinaReaper.intermediateDieSprite = new ImageIcon(StartingClass.class.getResource("/data/reaperdead1.png")).getImage();
		Oniough.staySprite = new ImageIcon(StartingClass.class.getResource("/data/oniough1.png")).getImage();
		Oniough.move1Sprite = new ImageIcon(StartingClass.class.getResource("/data/onioughWalk1.png")).getImage();
		Oniough.move2Sprite = new ImageIcon(StartingClass.class.getResource("/data/onioughWalk2.png")).getImage();
		Oniough.onioughStomp1 = new ImageIcon(StartingClass.class.getResource("/data/onioughStomp1.png")).getImage();
		Oniough.onioughStomp2 = new ImageIcon(StartingClass.class.getResource("/data/onioughStomp2.png")).getImage();
		Oniough.dieSprite = new ImageIcon(StartingClass.class.getResource("/data/onioughdead.png")).getImage();
		Oniough.intermediateDieSprite = new ImageIcon(StartingClass.class.getResource("/data/onioughdying.png")).getImage();
		
		Oniough.onioughShootDown = new ImageIcon(StartingClass.class.getResource("/data/onioughShootDown.png")).getImage();
		Oniough.onioughShootLeft = new ImageIcon(StartingClass.class.getResource("/data/onioughShootLeft.png")).getImage();
		Oniough.onioughShootRight = new ImageIcon(StartingClass.class.getResource("/data/onioughShootRight.png")).getImage();
		Oniough.onioughShootUp = new ImageIcon(StartingClass.class.getResource("/data/onioughShootUp.png")).getImage();
		Oniough.onioughShootLeftDown = new ImageIcon(StartingClass.class.getResource("/data/onioughShootDownLeft.png")).getImage();
		Oniough.onioughShootLeftUp = new ImageIcon(StartingClass.class.getResource("/data/onioughShootUpLeft.png")).getImage();
		Oniough.onioughShootRightDown = new ImageIcon(StartingClass.class.getResource("/data/onioughShootDownRight.png")).getImage();
		Oniough.onioughShootRightUp = new ImageIcon(StartingClass.class.getResource("/data/onioughShootUpRight.png")).getImage();
		
		Garlnstein.staySprite = new ImageIcon(StartingClass.class.getResource("/data/garlnstein.png")).getImage();
		Garlnstein.move1Sprite = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinWalk1.png")).getImage();
		Garlnstein.move2Sprite = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinWalk2.png")).getImage();
		Garlnstein.dieSprite = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinDead.png")).getImage();
		Garlnstein.slashDown = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinSwipeDown.png")).getImage();
		Garlnstein.slashUp = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinSwipeUp.png")).getImage();
		Garlnstein.slashRight = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinSwipeRight.png")).getImage();
		Garlnstein.slashLeft = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinSwipeLeft.png")).getImage();
		Garlnstein.dashDown = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinDashDown.png")).getImage();
		Garlnstein.dashUp = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinDashUp.png")).getImage();
		Garlnstein.dashRight = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinDashRight.png")).getImage();
		Garlnstein.dashLeft = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinDashLeft.png")).getImage();
		Garlnstein.dashBlinking = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinReadyToDash.png")).getImage();
		Garlnstein.cloning1 = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinCloning1.png")).getImage();
		Garlnstein.cloning2 = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinCloning2.png")).getImage();
		Garlnstein.cloning3 = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinCloning3.png")).getImage();
		Garlnstein.intermediateDieSprite = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinDying.png")).getImage();
		Garlnstein.slashWindupDown = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinSwipeDownWindUp.png")).getImage();
		Garlnstein.slashWindupUp = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinSwipeUpWindUp.png")).getImage();
		Garlnstein.slashWindupRight = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinSwipeRightWindUp.png")).getImage();
		Garlnstein.slashWindupLeft = new ImageIcon(StartingClass.class.getResource("/data/garlnsteinSwipeLeftWindUp.png")).getImage();
		KaleKing.staySprite = new ImageIcon(StartingClass.class.getResource("/data/kaleking.png")).getImage();
		KaleKing.move1Sprite = new ImageIcon(StartingClass.class.getResource("/data/kalekingWalk1.png")).getImage();
		KaleKing.move2Sprite = new ImageIcon(StartingClass.class.getResource("/data/kalekingWalk2.png")).getImage();
		KaleKing.dieSprite = new ImageIcon(StartingClass.class.getResource("/data/kalekingDead.png")).getImage();
		KaleKing.swipeDown = new ImageIcon(StartingClass.class.getResource("/data/kalekingSwipeDown.png")).getImage();
		KaleKing.swipeLeft = new ImageIcon(StartingClass.class.getResource("/data/kalekingSwipeLeft.png")).getImage();
		KaleKing.swipeRight = new ImageIcon(StartingClass.class.getResource("/data/kalekingSwipeRight.png")).getImage();
		KaleKing.swipeUp = new ImageIcon(StartingClass.class.getResource("/data/kalekingSwipeUp.png")).getImage();
		KaleKing.boltsfiringsprite = new ImageIcon(StartingClass.class.getResource("/data/kalekingMagicCryoBeam.png")).getImage();
		KaleKing.phaseanim.add(new ImageIcon(StartingClass.class.getResource("/data/kalekingPhase1.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(StartingClass.class.getResource("/data/kalekingPhase2.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(StartingClass.class.getResource("/data/kalekingPhase3.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(StartingClass.class.getResource("/data/kalekingPhase4.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(StartingClass.class.getResource("/data/kalekingPhase5.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(StartingClass.class.getResource("/data/kalekingPhase6.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(StartingClass.class.getResource("/data/kalekingPhase7.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(StartingClass.class.getResource("/data/kalekingPhase8.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(StartingClass.class.getResource("/data/kalekingPhase9.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(StartingClass.class.getResource("/data/kalekingPhase10.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(StartingClass.class.getResource("/data/kalekingPhase11.png")).getImage());
		KaleKing.dashSpriteLeft = new ImageIcon(StartingClass.class.getResource("/data/kalekingThrustLeft.png")).getImage();
		KaleKing.dashSpriteRight = new ImageIcon(StartingClass.class.getResource("/data/kalekingThrustRight.png")).getImage();
		KaleKing.hulkSprite = new ImageIcon(StartingClass.class.getResource("/data/hulk.png")).getImage();
		KaleKing.hulkMove1 = new ImageIcon(StartingClass.class.getResource("/data/hulkWalk1.png")).getImage();
		KaleKing.hulkMove2 = new ImageIcon(StartingClass.class.getResource("/data/hulkWalk2.png")).getImage();
		KaleKing.hulkSwipeDown = new ImageIcon(StartingClass.class.getResource("/data/hulkSwipeDown.png")).getImage();
		KaleKing.hulkSwipeLeft = new ImageIcon(StartingClass.class.getResource("/data/hulkSwipeLeft.png")).getImage();
		KaleKing.hulkSwipeRight = new ImageIcon(StartingClass.class.getResource("/data/hulkSwipeRight.png")).getImage();
		KaleKing.hulkSwipeUp = new ImageIcon(StartingClass.class.getResource("/data/hulkSwipeUp.png")).getImage();
		KaleKing.blinkingSprite = new ImageIcon(StartingClass.class.getResource("/data/kalekingMagicAoE.png")).getImage();
		KaleKing.intermediateDieSprite = new ImageIcon(StartingClass.class.getResource("/data/kalekingDying.png")).getImage();
		KaleKing.swipeDownWindup = new ImageIcon(StartingClass.class.getResource("/data/kalekingSwipeDownWindUp.png")).getImage();
		KaleKing.swipeLeftWindup = new ImageIcon(StartingClass.class.getResource("/data/kalekingSwipeLeftWindUp.png")).getImage();
		KaleKing.swipeRightWindup = new ImageIcon(StartingClass.class.getResource("/data/kalekingSwipeRightWindUp.png")).getImage();
		KaleKing.swipeUpWindup = new ImageIcon(StartingClass.class.getResource("/data/kalekingSwipeUpWindUp.png")).getImage();
		KaleKing.hulkSwipeDownWindup = new ImageIcon(StartingClass.class.getResource("/data/hulkSwipeDownWindup.png")).getImage();
		KaleKing.hulkSwipeLeftWindup = new ImageIcon(StartingClass.class.getResource("/data/hulkSwipeLeftWindup.png")).getImage();
		KaleKing.hulkSwipeRightWindup = new ImageIcon(StartingClass.class.getResource("/data/hulkSwipeRightWindup.png")).getImage();
		KaleKing.hulkSwipeUpWindup = new ImageIcon(StartingClass.class.getResource("/data/hulkSwipeUpWindup.png")).getImage();
		KaleKing.dashSpriteLeftWindup = new ImageIcon(StartingClass.class.getResource("/data/kalekingThrustLeftWindUp.png")).getImage();
		KaleKing.dashSpriteRightWindup = new ImageIcon(StartingClass.class.getResource("/data/kalekingThrustRightWindUp.png")).getImage();
		KaleKing.kaleKingStomp1 = new ImageIcon(StartingClass.class.getResource("/data/kalekingSmashWindUp.png")).getImage();
		KaleKing.kaleKingStomp2 = new ImageIcon(StartingClass.class.getResource("/data/kalekingSmash.png")).getImage();

		BazookaBulletExplosion.bazookaexplosionsprite = new ImageIcon(
				StartingClass.class.getResource("/data/bazookaexplosion.png")).getImage();
		BazookaBulletExplosion.sound = StartingClass.class.getResource("/data/explosion.wav");
		TomatoProjectileExplosion.tomatoexplosionsprite = new ImageIcon(
				StartingClass.class.getResource("/data/sirtomatoprojectileexplosion.png")).getImage();
		BarrelExplosion.explosionsprite = new ImageIcon(StartingClass.class.getResource("/data/barrelexplosion.png")).getImage();
		BarrelExplosion.sound = StartingClass.class.getResource("/data/explosion.wav");
		
		ArmorPotion.armorpotionsprite = new ImageIcon(StartingClass.class.getResource("/data/armor.png")).getImage();
		ArmorPotion.armorpotioneffectsprite = new ImageIcon(StartingClass.class.getResource("/data/armoreffect.png")).getImage();
		ArmorPotion.armorpotioneffectsprite2 = new ImageIcon(StartingClass.class.getResource("/data/armoreffect_2.png")).getImage();
		ArmorPotion.armorpotioneffectsprite3 = new ImageIcon(StartingClass.class.getResource("/data/armoreffect_3.png")).getImage();
		ArmorPotion.armorpotioneffectsprite4 = new ImageIcon(StartingClass.class.getResource("/data/armoreffect_4.png")).getImage();
		HealthPotion.healthpotionsprite = new ImageIcon(StartingClass.class.getResource("/data/health.png")).getImage();
		HealthPotion.healthpotioneffectsprite = new ImageIcon(StartingClass.class.getResource("/data/healtheffect.png")).getImage();
		HealthPotion.healthpotioneffectsprite2 = new ImageIcon(StartingClass.class.getResource("/data/healtheffect_2.png")).getImage();
		HealthPotion.healthpotioneffectsprite3 = new ImageIcon(StartingClass.class.getResource("/data/healtheffect_3.png")).getImage();
		HealthPotion.healthpotioneffectsprite4 = new ImageIcon(StartingClass.class.getResource("/data/healtheffect_4.png")).getImage();
		Lava.lavaeffectsprite = new ImageIcon(StartingClass.class.getResource("/data/lavaeffect.png")).getImage();
		BackgroundFactory.waterflow = new ImageIcon(StartingClass.class.getResource("/data/waterflow.png")).getImage();
		WaterFlow.watereffectsprite = new ImageIcon(StartingClass.class.getResource("/data/watereffect.png")).getImage();
		WaterPuddle.sprite = new ImageIcon(StartingClass.class.getResource("/data/puddle.png")).getImage();
		WoodBridge.sprite = new ImageIcon(StartingClass.class.getResource("/data/woodbridge.png")).getImage();
		PizzaBox.pizzaboxsprite = new ImageIcon(StartingClass.class.getResource("/data/pizzabox.png")).getImage();
		//Carpet.sprite = new ImageIcon(StartingClass.class.getResource("/data/carpet.png")).getImage();
		BackgroundFactory.carpet = new ImageIcon(StartingClass.class.getResource("/data/carpet.png")).getImage();
		CrateOpen.sprite = new ImageIcon(StartingClass.class.getResource("/data/crateopen.png")).getImage();
		ReaperBlinkingItem.sprite = new ImageIcon(StartingClass.class.getResource("/data/reaperblinkingpos.png")).getImage();
		ReaperTrap.pizzatrap = new ImageIcon(StartingClass.class.getResource("/data/pizzatrap.png")).getImage();
		ReaperTrap.potiontrap = new ImageIcon(StartingClass.class.getResource("/data/potiontrap.png")).getImage();
		ReaperTrap.potiontrap1 = new ImageIcon(StartingClass.class.getResource("/data/potiontrap1.png")).getImage();
		BackgroundFactory.snow = new ImageIcon(StartingClass.class.getResource("/data/snowbank.png")).getImage();
		SnowBank.snoweffectsprite = new ImageIcon(StartingClass.class.getResource("/data/snowbankeffect.png")).getImage();
		BackgroundFactory.ice = new ImageIcon(StartingClass.class.getResource("/data/ice.png")).getImage();
		BoostCheese.boostsprite = new ImageIcon(StartingClass.class.getResource("/data/cheese.png")).getImage();
		BoostCheese.boosteffectsprite = new ImageIcon(StartingClass.class.getResource("/data/cheeseeffect.png")).getImage();
		BoostBacon.boostsprite = new ImageIcon(StartingClass.class.getResource("/data/bacon.png")).getImage();
		BoostBacon.boosteffectsprite = new ImageIcon(StartingClass.class.getResource("/data/baconeffect.png")).getImage();
		BoostBasil.boostsprite = new ImageIcon(StartingClass.class.getResource("/data/basil.png")).getImage();
		BoostBasil.boosteffectsprite = new ImageIcon(StartingClass.class.getResource("/data/basileffect.png")).getImage();
		BoostGarlic.boostsprite = new ImageIcon(StartingClass.class.getResource("/data/garlicbread.png")).getImage();
		BoostGarlic.boosteffectsprite = new ImageIcon(StartingClass.class.getResource("/data/garlicbreadeffect.png")).getImage();
		LevelExit.sprite = new ImageIcon(StartingClass.class.getResource("/data/finishline.png")).getImage();
		SummonedIce.healingice = new ImageIcon(StartingClass.class.getResource("/data/iceheal.png")).getImage();
		FakeItemForFrozen.frozen = new ImageIcon(StartingClass.class.getResource("/data/frozeneffect.png")).getImage();
		DarkIce.ready  = new ImageIcon(StartingClass.class.getResource("/data/darkiceready.png")).getImage();
		DarkIce.strike  = new ImageIcon(StartingClass.class.getResource("/data/darkice.png")).getImage();
		KaleKingBlinkingItem.sprite = new ImageIcon(StartingClass.class.getResource("/data/kingkaleblinking.png")).getImage();
		HolySauce.sprite = new ImageIcon(StartingClass.class.getResource("/data/HolySauce.png")).getImage();
		
		HatBaseball.hatsprite = new ImageIcon(StartingClass.class.getResource("/data/hatbaseball.png")).getImage();
		HatBaseball.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addhatbaseball.png")).getImage();
		HatBowler.hatsprite = new ImageIcon(StartingClass.class.getResource("/data/hatbowler.png")).getImage();
		HatBowler.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addhatbowler.png")).getImage();
		HatFedora.hatsprite = new ImageIcon(StartingClass.class.getResource("/data/hatfedora.png")).getImage();
		HatFedora.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addhatfedora.png")).getImage();
		HatPanama.hatsprite = new ImageIcon(StartingClass.class.getResource("/data/hatpanama.png")).getImage();
		HatPanama.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addhatpanama.png")).getImage();
		HatSherlock.hatsprite = new ImageIcon(StartingClass.class.getResource("/data/hatsherlock.png")).getImage();
		HatSherlock.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addhatsherlock.png")).getImage();
		HatTop.hatsprite = new ImageIcon(StartingClass.class.getResource("/data/hattop.png")).getImage();
		HatTop.addSprite = new ImageIcon(StartingClass.class.getResource("/data/addhattop.png")).getImage();

		StartingClass.grinningsprite = new ImageIcon(StartingClass.class.getResource("/data/grin.png")).getImage();
		StartingClass.cutscene1 = new ImageIcon(StartingClass.class.getResource("/data/Cutscene1.png")).getImage();
		Level.cutsceneboss1 = new ImageIcon(StartingClass.class.getResource("/data/Cutscene2.png")).getImage();
		Level.cutsceneboss2 = new ImageIcon(StartingClass.class.getResource("/data/Cutscene3.png")).getImage();
		Level.cutsceneboss3 = new ImageIcon(StartingClass.class.getResource("/data/Cutscene4.png")).getImage();
		Level.cutsceneboss4 = new ImageIcon(StartingClass.class.getResource("/data/Cutscene5.png")).getImage();
		Level.cutsceneboss5 = new ImageIcon(StartingClass.class.getResource("/data/Cutscene6.png")).getImage();
		Level.cutsceneboss6 = new ImageIcon(StartingClass.class.getResource("/data/Cutscene7.png")).getImage();
		Level.cutsceneboss7 = new ImageIcon(StartingClass.class.getResource("/data/Cutscene8.png")).getImage();
		StartingClass.cutsceneboss8 = new ImageIcon(StartingClass.class.getResource("/data/Cutscene9.png")).getImage();
		StartingClass.cutsceneboss9 = new ImageIcon(StartingClass.class.getResource("/data/Cutscene10.png")).getImage();
	}
	
}
