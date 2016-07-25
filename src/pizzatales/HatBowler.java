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
		/*if (player.getArmor().getDefense() >= player.getArmor().MAXDEF)
			player.getArmor().setDefense(player.getArmor().getDefense()+8);*/
		player.getArmor().MAXDEF += 8;
	}

	@Override
	public String getID() {
		return "HATBOWLER";
	}
}
