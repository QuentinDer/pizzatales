package pizzatales;

import java.awt.Image;

public class HatBowler extends Hat {
	
	private Player player = StartingClass.getPlayer();

	public HatBowler() {
		deltay = -4;
	}
	
	public static Image hatsprite;
	
	@Override
	public Image getSprite() {
		return hatsprite;
	}
	
	@Override
	public void effect(){
		player.getArmor().setDefense(player.getArmor().getDefense()+4);
	}
	
}
