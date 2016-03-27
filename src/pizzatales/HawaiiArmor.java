package pizzatales;

import java.awt.Image;

public class HawaiiArmor extends Armor {
	
	public static Image staysprite1, staysprite2, movespriteLeft1, movespriteLeft2, movespriteRight1, movespriteRight2, deathSprite;
	
	public HawaiiArmor() {
		super(9,5.25f);
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
	public Image getMoveSpriteLeft1() {
		return movespriteLeft1;
	}

	@Override
	public Image getMoveSpriteLeft2() {
		return movespriteLeft2;
	}
	
	@Override
	public Image getMoveSpriteRight1() {
		return movespriteRight1;
	}

	@Override
	public Image getMoveSpriteRight2() {
		return movespriteRight2;
	}
	
	@Override
	public Image getDeathSprite() {
		return deathSprite;
	}
}
