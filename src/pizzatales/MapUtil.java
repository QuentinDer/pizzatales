package pizzatales;

import java.util.ArrayList;

public class MapUtil {

	public static void getAccessibleArea(int x, int y, int mp, char[][] map, ArrayList<Integer> nonobstacles, ArrayList<Integer> obstacles) {
		obstacles.clear();
		nonobstacles.clear();
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
				int xj = todiscoverx.get(0);
				int yj = todiscovery.get(0);
				int current = height*xj + yj;
				if (!nonobstacles.contains(current-height) && 0 != xj) {
					if (!Tile.isTileBlocking(map[xj-1][yj])) {
						todiscoverx.add(xj-1);
						todiscovery.add(yj);
					} else
						obstacles.add(current-height);
				}
				if (!nonobstacles.contains(current+height) && width -1 != xj) {
					if (!Tile.isTileBlocking(map[xj+1][yj])) {
						todiscoverx.add(xj+1);
						todiscovery.add(yj);
					} else
						obstacles.add(current+height);
				}
				if (!nonobstacles.contains(current-1) && 0 != yj) {
					if (!Tile.isTileBlocking(map[xj][yj-1])) {
						todiscoverx.add(xj);
						todiscovery.add(yj-1);
					} else
						obstacles.add(current-1);
				}
				if (!nonobstacles.contains(current+1) && height-1 != yj) {
					if (!Tile.isTileBlocking(map[xj][yj+1])) {
						todiscoverx.add(xj);
						todiscovery.add(yj+1);
					} else
						obstacles.add(current+1);
				}
				todiscoverx.remove(0);
				todiscovery.remove(0);
				nonobstacles.add(current);
				j++;
			}
		}
		if (i == mp) {
			nonobstacles.clear();
			obstacles.clear();
		}
	}
	
}
