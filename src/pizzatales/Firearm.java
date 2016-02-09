package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public abstract class Firearm {
	
	public Image currentSprite;
	protected int shootingCounter;
	protected int firerate;
	protected ArrayList<Projectile> holderprojectiles;
	
	public void setHolderProjectiles(ArrayList<Projectile> holderprojectiles) {
		this.holderprojectiles = holderprojectiles;
		setSpriteDown();
	}
	
	public abstract void shootUp(int x, int y);
	
	public abstract void shootDown(int x, int y);
	
	public abstract void shootLeft(int x, int y);
	
	public abstract void shootRight(int x, int y);
	
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
}
