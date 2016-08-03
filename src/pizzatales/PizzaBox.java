package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class PizzaBox extends Item {

	public static Image pizzaboxsprite;
	private Image effectSprite;
	
	public PizzaBox(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return true;
	}

	@Override
	protected void doEffect(Player p) {
		ArrayList<Armor> armors = Level.getPizzaBoxArmors(StartingClass.currentlevel);
		for (Armor armor : armors) {
			boolean toadd = true;
			for (Armor playerarmor : StartingClass.playerarmor) {
				toadd = toadd && (playerarmor.getClass() != armor.getClass());
			}
			if (toadd) {
				StartingClass.playerarmor.add(armor);
				effectSprite = armor.getAddSprite();
			}
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
				effectSprite = firearm.getAddSprite();
			}
		}
		ArrayList<Hat> hats = Level.getPizzaBoxHats(StartingClass.currentlevel);
		for (Hat hat : hats) {
			boolean toadd = true;
			for (Hat playerhat : StartingClass.playerhats) {
				toadd = toadd && (playerhat == null || playerhat.getClass() != hat.getClass());
			}
			if (toadd) {
				StartingClass.playerhats.add(hat);
				effectSprite = hat.getAddSprite();
			}
		}
		effectactive = true;
		effectTimer = 180;
		player.isGrinning = 50;
	}

	@Override
	protected Image getSprite() {
		return pizzaboxsprite;
	}

	@Override
	protected Image getEffectSprite() {
		return effectSprite;
	}

	@Override
	protected void doLeavingEffect() {
	}

	@Override
	protected int getEffectCenterX() {
		return centerX;
	}

	@Override
	protected int getEffectCenterY() {
		return centerY;
	}

	@Override
	protected boolean isEffectAbove() {
		return false;
	}

	@Override
	protected boolean canDoEffect(Enemy e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void doEffect(Enemy e) {
		// TODO Auto-generated method stub
		
	}
}
