package pizzatales;

import java.awt.Image;

public class KaleKingFlame extends FlamerFlame{

	public static Image sprite;
	
	private boolean forward;
	
	public KaleKingFlame(int startX, int startY, float vectorX, float vectorY,
			int speed, float dmg, int range) {
		super(startX, startY, vectorX, vectorY, speed, dmg, range);
		forward = true;
	} 
	
	@Override
	public void doOnLimitRange() {
		if (forward) {
			travelleddist = 0.f;
			speedX = -speedX;
			speedY = -speedY;
			forward = false;
		} else
			visible = false;
	}

	@Override
	public Image getSprite(){
		return sprite;
	}
	
}
