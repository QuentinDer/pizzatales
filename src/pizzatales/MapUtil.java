package pizzatales;

import java.util.ArrayList;
import java.util.HashMap;

public class MapUtil {

	public static void getAccessibleArea(int x, int y, int mp, char[][] map, ArrayList<Integer> nonobstacles, HashMap<Integer,EntryDoor> entrydoors, HashMap<Integer,Tile> doors, int arenanumber) {
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
					} else {
						if (1 != xj && entrydoors.containsKey(current) && entrydoors.containsKey(current - height - height) && doors.containsKey(current - height)) {
							entrydoors.get(current).setEntryOut(entrydoors.get(current - height - height));
							entrydoors.get(current - height - height).setEntryOut(entrydoors.get(current));
							entrydoors.get(current).setOut(arenanumber);
							entrydoors.get(current - height - height).setIn(arenanumber);
							entrydoors.get(current - height - height).addDoor(doors.get(current - height));
							int k = 1;
							while(yj + k != height && doors.containsKey(current - height + k)) {
								entrydoors.get(current - height - height).addDoor(doors.get(current - height + k));
								k++;
							}
							k = -1;
							while(yj + k != -1 && doors.containsKey(current - height + k)) {
								entrydoors.get(current - height - height).addDoor(doors.get(current - height + k));
								k--;
							}
						}
					}
				}
				if (!nonobstacles.contains(current+height) && width -1 != xj) {
					if (!Tile.isTileBlocking(map[xj+1][yj])) {
						todiscoverx.add(xj+1);
						todiscovery.add(yj);
					} else {
						if (width - 2 != xj && entrydoors.containsKey(current) && entrydoors.containsKey(current + height + height) && doors.containsKey(current + height)) {
							entrydoors.get(current).setEntryOut(entrydoors.get(current + height + height));
							entrydoors.get(current + height + height).setEntryOut(entrydoors.get(current));
							entrydoors.get(current).setOut(arenanumber);
							entrydoors.get(current + height + height).setIn(arenanumber);
							entrydoors.get(current + height + height).addDoor(doors.get(current + height));
							int k = 1;
							while(yj + k != height && doors.containsKey(current + height + k)) {
								entrydoors.get(current + height + height).addDoor(doors.get(current + height + k));
								k++;
							}
							k = -1;
							while(yj + k != -1 && doors.containsKey(current - height + k)) {
								entrydoors.get(current + height + height).addDoor(doors.get(current + height + k));
								k--;
							}
						}
					}
				}
				if (!nonobstacles.contains(current-1) && 0 != yj) {
					if (!Tile.isTileBlocking(map[xj][yj-1])) {
						todiscoverx.add(xj);
						todiscovery.add(yj-1);
					} else {
						if (1 != yj && entrydoors.containsKey(current) && entrydoors.containsKey(current - 2) && doors.containsKey(current - 1)) {
							entrydoors.get(current).setEntryOut(entrydoors.get(current - 2));
							entrydoors.get(current - 2).setEntryOut(entrydoors.get(current));
							entrydoors.get(current).setOut(arenanumber);
							entrydoors.get(current - 2).setIn(arenanumber);
							entrydoors.get(current - 2).addDoor(doors.get(current - 1));
							int k = 1;
							while(xj + k != width && doors.containsKey(current - height + k)) {
								entrydoors.get(current - 2).addDoor(doors.get(current - 1 + height*k));
								k++;
							}
							k = -1;
							while(xj + k != -1 && doors.containsKey(current - height + k)) {
								entrydoors.get(current - 2).addDoor(doors.get(current - 1 + height*k));
								k--;
							}
						}
					}
				}
				if (!nonobstacles.contains(current+1) && height-1 != yj) {
					if (!Tile.isTileBlocking(map[xj][yj+1])) {
						todiscoverx.add(xj);
						todiscovery.add(yj+1);
					} else {
						if (height - 2 != yj && entrydoors.containsKey(current) && entrydoors.containsKey(current + 2) && doors.containsKey(current + 1)) {
							entrydoors.get(current).setEntryOut(entrydoors.get(current + 2));
							entrydoors.get(current + 2).setEntryOut(entrydoors.get(current));
							entrydoors.get(current).setOut(arenanumber);
							entrydoors.get(current + 2).setIn(arenanumber);
							entrydoors.get(current + 2).addDoor(doors.get(current + 1));
							int k = 1;
							while(xj + k != width && doors.containsKey(current - height + k)) {
								entrydoors.get(current + 2).addDoor(doors.get(current + 1 + height*k));
								k++;
							}
							k = -1;
							while(xj + k != -1 && doors.containsKey(current - height + k)) {
								entrydoors.get(current + 2).addDoor(doors.get(current + 1 + height*k));
								k--;
							}
						}
					}
				}
				todiscoverx.remove(0);
				todiscovery.remove(0);
				nonobstacles.add(current);
				j++;
			}
		}
		if (i == mp) {
			nonobstacles.clear();
		}
	}
	
}
