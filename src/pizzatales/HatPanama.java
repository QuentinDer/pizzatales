package pizzatales;

import java.awt.Image;

public class HatPanama extends Hat {
	
	private Player player = StartingClass.getPlayer();

	public static Image hatsprite;
	
	@Override
	public Image getSprite() {
		return hatsprite;
	}
	
	@Override
	public void effect(){
		if (player.getHealth() >= player.getMaxHealth())
			player.setHealth(player.getHealth()+10);
		player.setMaxHealth(player.getMaxHealth()+10);
	}

	@Override
	public String getID() {
		return "HATPANAMA";
	}
}
