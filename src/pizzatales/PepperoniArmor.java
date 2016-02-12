package pizzatales;

import java.awt.Image;

public class PepperoniArmor extends Armor {
	
	public PepperoniArmor() {
		super();
		this.MAXDEF = 12;
		this.defense = this.MAXDEF;
		this.speed = 4;
		armor1 = "data/pepperoni1.png";
		armor2 = "data/pepperoni2.png";
		armor3 = "data/pepperoni3.png";
		armor4 = "data/pepperoni4.png";
	}
	
	/*
	public void setSpriteStay1(){
		currentSprite = armor1;
	}
	
	public void setSpriteStay2(){
		currentSprite = armor2;
	}
	
	public void setSpriteWalk1(){
		currentSprite = armor3;
	}
	
	public void setSpriteWalk2(){
		currentSprite = armor4;
	}
	*/
}
