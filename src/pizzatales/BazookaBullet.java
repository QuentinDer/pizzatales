package pizzatales;

import java.awt.Image;

public class BazookaBullet extends Projectile {

	public static Image bulletsprite, bulletspriteLeft, bulletspriteRight, bulletspriteUp, bulletspriteDown;
	private int aiming;
	
	public BazookaBullet(int startX, int startY, int vectorX, int vectorY) {
		super(startX, startY, vectorX, vectorY, 5);
		this.range = 450;
		this.size = 30;
		this.halfsize = 15;
		this.damage = 12;
		if (vectorX > 0) {
			aiming = 3;
		} else if (vectorX < 0) {
			aiming = 1;
		} else if (vectorY > 0) {
			aiming = 4;
		} else {
			aiming = 2;
		}
	}
	
	@Override
	public Image getSprite(){
		switch(aiming) {
		case 1:
			return bulletspriteLeft;
		case 2:
			return bulletspriteUp;
		case 3:
			return bulletspriteRight;
		case 4:
			return bulletspriteDown;
		default:
			return null;
		}
	}
}
