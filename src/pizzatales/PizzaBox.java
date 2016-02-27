package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class PizzaBox extends Item {

	public static Image pizzaboxsprite;
	
	public PizzaBox(int x, int y, int deltapy, boolean onetimeeffect) {
		super(x, y, deltapy, onetimeeffect);
	}

	@Override
	protected boolean canDoEffect() {
		return true;
	}

	@Override
	protected void doEffect() {
		ArrayList<Armor> armors = Level.getPizzaBoxArmors(StartingClass.currentlevel);
		for (Armor armor : armors) {
			boolean toadd = true;
			for (Armor playerarmor : StartingClass.playerarmor) {
				toadd = toadd && (playerarmor.getClass() != armor.getClass());
			}
			if (toadd)
				StartingClass.playerarmor.add(armor);
		}
		ArrayList<Firearm> firearms = Level.getPizzaBoxFirearms(StartingClass.currentlevel);
		for (Firearm firearm : firearms) {
			boolean toadd = true;
			for (Firearm playerfirearm : StartingClass.playerweapons) {
				toadd = toadd && (playerfirearm.getClass() != firearm.getClass());
			}
			if (toadd) {
				firearm.setHolderProjectiles(player.getProjectiles());
				StartingClass.playerweapons.add(firearm);
			}
		}
	}

	@Override
	protected Image getSprite() {
		return pizzaboxsprite;
	}

	@Override
	protected Image getEffectSprite() {
		return null;
	}

	
	
}
