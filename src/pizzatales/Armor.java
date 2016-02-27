package pizzatales;

import java.awt.Image;

public abstract class Armor {
	
	public Image currentSprite;
	public int MAXDEF;
	public int defense;
	public int speed;
	
	public void repairArmor(){
		if(defense < MAXDEF) {
			defense++;
		}
	}

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
	
	public abstract Image getStaySprite();
	public abstract Image getStaySprite2();
	public abstract Image getMoveSprite1();
	public abstract Image getMoveSprite2();
}