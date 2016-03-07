package pizzatales;

import java.awt.Image;

public abstract class Hat {

	public int deltay;
	
	public abstract Image getSprite();
	
	public abstract void effect();
	public abstract void undoEffect();
	
	public abstract void effectWeapon();
	public abstract void undoEffectWeapon();
	
	public abstract void effectArmor();
	public abstract void undoEffectArmor();
}
