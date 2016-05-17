package pizzatales;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public abstract class Firearm {
	
	public Image currentSprite;
	protected int shootingCounter;
	protected int firerate;
	protected float projectiledmg;
	protected int range;
	protected int speed;
	private int defrange, defspeed, deffirerate;
	private float defprojdmg;
	protected ArrayList<Projectile> holderprojectiles;
	public int deltapx;
	public int deltapy;
	public Clip sound;
	public static URL url;
	public String weaponName;
	
	public Firearm(float projdmg, int range, int speed, int firerate) {
		projectiledmg = projdmg;
		defprojdmg = projdmg;
		this.range = range;
		defrange = range;
		this.speed = speed;
		defspeed = speed;
		this.firerate = firerate;
		deffirerate = firerate;
		this.deltapx = -31;
		this.deltapy = -31;
	}
	
	public Firearm(int deltapx, int deltapy) {
		this.deltapx = deltapx;
		this.deltapy = deltapy;
	}
	
	public void initState() {
		projectiledmg = defprojdmg;
		range = defrange;
		firerate = deffirerate;
		speed = defspeed;
	}
	
	public void setHolderProjectiles(ArrayList<Projectile> holderprojectiles) {
		this.holderprojectiles = holderprojectiles;
		setSpriteDown();
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public abstract void shootUp(int x, int y);
	
	public abstract void shootDown(int x, int y);
	
	public abstract void shootLeft(int x, int y);
	
	public abstract void shootRight(int x, int y);
	
	public abstract void shootUpLeft(int x, int y);
	public abstract void shootUpRight(int x, int y);
	public abstract void shootDownLeft(int x, int y);
	public abstract void shootDownRight(int x, int y);
	
	public abstract void setSpriteLeft();
	public abstract void setSpriteRight();
	public abstract void setSpriteUp();
	public abstract void setSpriteDown();
	
	public int getFireRate() {
		return firerate;
	}
	
	public void setFireRate(int firerate) {
		this.firerate = firerate;
	}

	public void increaseShootingCounter() {
		if (!isReady2Fire()) {
			shootingCounter++;
			if (shootingCounter == 1000)
				shootingCounter = 0;
		}
	}
	
	public boolean isReady2Fire() {
		return (shootingCounter % firerate == 0);
	}

	public float getProjectiledmg() {
		return projectiledmg;
	}

	public void setProjectiledmg(float projectiledmg) {
		this.projectiledmg = projectiledmg;
	}
	
	public int getRange(){
		return range;
	}
	
	public void setGunSound(URL url){
		this.url = url;
	}
}
