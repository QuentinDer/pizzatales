package pizzatales;

import java.awt.Image;

public abstract class Armor {
	
	public int MAXDEF;
	public int DEFMAXDEF;
	public float defense;
	public float speed;
	
	public Armor(int maxdef, float speed) {
		MAXDEF = maxdef;
		DEFMAXDEF = maxdef;
		this.speed = speed;
		defense = maxdef;
	}
	
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

	public float getSpeed() {
		return speed;
	}
	
	public abstract Image getStaySprite();
	public abstract Image getStaySprite2();
	public abstract Image getMoveSprite1();
	public abstract Image getMoveSprite2();
}