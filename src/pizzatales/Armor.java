package pizzatales;

import java.awt.Image;

public abstract class Armor {
	
	public Image currentSprite;
	public String armor1, armor2, armor3, armor4;
	public int MAXDEF;
	public int defense;
	public int speed;
	
	public void repairArmor(){
		if(defense < MAXDEF) {
			defense++;
		}
	}
	
	public void damageArmor(){
		if(defense > 0){
			defense--;
		}
	}
	
	/*public abstract void setSpriteStay1();
	public abstract void setSpriteStay2();
	public abstract void setSpriteWalk1();
	public abstract void setSpriteWalk2();*/

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
}