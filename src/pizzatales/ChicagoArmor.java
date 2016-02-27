package pizzatales;

import java.awt.Image;

public class ChicagoArmor extends Armor {

	public static Image staysprite1, staysprite2, movesprite1, movesprite2;
	
	public ChicagoArmor() {
		super();
		this.MAXDEF = 20;
		this.defense = this.MAXDEF;
		this.speed = 2;
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
