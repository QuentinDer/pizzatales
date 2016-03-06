package pizzatales;

import java.awt.Image;

public class HatTop extends Hat {

	private Player player = StartingClass.getPlayer();

	public HatTop() {
		deltay = -7;
	}
	
	public static Image hatsprite;
	
	@Override
	public Image getSprite() {
		return hatsprite;
	}
	
	@Override
	public void effect(){
		//player.getArmor().setDefense(player.getArmor().getDefense()+4);
	}
	
}
