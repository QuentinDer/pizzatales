package pizzatales;

import java.awt.Image;

public abstract class Armor {
	
	public Image currentSprite;
	public int MAXDEF;
	public float defense;
	public int speed;
	
	public void repairArmor(){
		if(defense < MAXDEF) {
			defense++;
		}
	}

	public float getDefense() {
		return defense;
	}

	public void setDefense(float defense) {
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