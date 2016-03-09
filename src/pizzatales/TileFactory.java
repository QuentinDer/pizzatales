package pizzatales;

public class TileFactory {

	private final static String acceptedTileTypes = "twcsrdekgvyqDHEL";

	public static boolean isTileTypeSupported(char type) {
		String test = "";
		test += type;
		return acceptedTileTypes.contains(test);
	}
	
	public static Tile getTile(int x, int y, char typeInt) {
		Tile ans = new Tile(x,y);
		switch(typeInt) {
		case 't':
			ans.setTileImage(StartingClass.tileTree);
			break;
		case 'w':
			ans.setTileImage(StartingClass.tileWall);
			break;
		case 'c':
			ans.setTileImage(StartingClass.tileCave);
			break;
		case 's':
			ans.setTileImage(StartingClass.tileStalag);
			break;
		case 'r':
			ans.setTileImage(StartingClass.tileCaveRock);
			break;
		case 'd':
			ans.setTileImage(StartingClass.tileGate);
			break;
		case 'e':
			ans.setTileImage(StartingClass.tileCaveExit);
			break;
		case 'k':
			ans.setTileImage(StartingClass.tilePikes);
			break;
		case 'g':
			ans.setTileImage(StartingClass.tileFlag);
			break;
		case 'v':
			ans.setTileImage(StartingClass.tileRock);
			break;
		case 'y':
			ans.setTileImage(StartingClass.tileDecoy);
			break;
		case 'q':
			ans = new Barrel(x,y);
			ans.setTileImage(StartingClass.tileBarrel);
			break;
		case 'D':
			ans.setTileImage(StartingClass.tileCandelabrum);
			break;
		case 'E':
			ans = new Crate(x,y);
			ans.setTileImage(StartingClass.tileCrate);
			break;
		case 'H':
			ans = new Chest(x,y);
			ans.setTileImage(StartingClass.tileChest);
			break;
		case 'L':
			ans.setTileImage(StartingClass.tileBlack);
			break;
		}
		return ans;
	}
	
}
