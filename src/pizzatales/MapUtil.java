package pizzatales;

import java.util.ArrayList;

public class MapUtil {

	public ArrayList<Integer> getAccessibleArea(int x, int y, int mp, char[][] map) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		int width = map.length;
		int height = map[0].length;
		ArrayList<Integer> todiscoverx = new ArrayList<Integer>();
		ArrayList<Integer> todiscovery = new ArrayList<Integer>();
		todiscoverx.add(x);
		todiscovery.add(y);
		int i = 0;
		int j = 0;
		int size;
		while (i < mp && !todiscoverx.isEmpty()) {
			j = 0;
			size = todiscoverx.size();
			while (j < size) {
				int xj = todiscoverx.get(j);
				int yj = todiscovery.get(j);
				int current = height*xj + yj;
				if (!ans.contains(current-height) && 0 != xj && !Tile.isTileBlocking(map[xj-1][yj])) {
					todiscoverx.add(xj-1);
					todiscovery.add(yj);
				}
				if (!ans.contains(current+height) && width -1 != xj && !Tile.isTileBlocking(map[xj-1][yj])) {
					todiscoverx.add(xj+1);
					todiscovery.add(yj);
				}
				if (!ans.contains(current-1) && 0 != yj && !Tile.isTileBlocking(map[xj-1][yj])) {
					todiscoverx.add(xj);
					todiscovery.add(yj-1);
				}
				if (!ans.contains(current+1) && height-1 != yj && !Tile.isTileBlocking(map[xj-1][yj])) {
					todiscoverx.add(xj);
					todiscovery.add(yj+1);
				}
				todiscoverx.remove(0);
				todiscovery.remove(0);
				ans.add(current);
			}
		}
		return ans;
	}
	
}
