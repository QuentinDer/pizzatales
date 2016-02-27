package pizzatales;

import java.awt.Image;

public class CheeseArmor extends Armor {
	
	public static Image staysprite1, staysprite2, movesprite1, movesprite2;
	
	public CheeseArmor() {
		super();
		this.MAXDEF = 16;
		this.defense = this.MAXDEF;
		this.speed = 3;		
	}

	@Override
	public Image getStaySprite() {
		return staysprite1;
	}

	@Override
	public Image getStaySprite2() {
		return staysprite2;
	}

	@Override
	public Image getMoveSprite1() {
		return movesprite1;
	}

	@Override
	public Image getMoveSprite2() {
		return movesprite2;
	}
}