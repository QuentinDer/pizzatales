package pizzatales;

import java.awt.Image;

public class CheeseArmor extends Armor {
	
	public CheeseArmor() {
		super();
		this.MAXDEF = 16;
		this.defense = this.MAXDEF;
		this.speed = 3;		
		armor1 = "data/cheese1.png";
		armor2 = "data/cheese2.png";
		armor3 = "data/cheese3.png";
		armor4 = "data/cheese4.png";
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