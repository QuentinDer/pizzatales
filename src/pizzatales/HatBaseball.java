package pizzatales;

import java.awt.Image;

public class HatBaseball extends Hat {
	
	private Player player = StartingClass.getPlayer();
	
	public static Image hatsprite;
	
	@Override
	public Image getSprite() {
		return hatsprite;
	}
	
	@Override
	public void effect(){
		player.getArmor().setSpeed(player.getArmor().getSpeed()+1);
	}

}
