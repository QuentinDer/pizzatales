package pizzatales;

import java.util.ArrayList;
import java.util.HashMap;

public class MapUtil {

	public static void getAccessibleArea(int x, int y, int mp, char[][] map, ArrayList<Integer> nonobstacles, HashMap<Integer,EntryDoor> entrydoors, HashMap<Integer,Tile> doors, int arenanumber) {
		nonobstacles.clear();
		int width = map.length;
		int height = map[0].length;
		ArrayList<Integer> todiscover = new ArrayList<Integer>();
		todiscover.add(height*x+y);
		int i = 0;
		int j = 0;
		int size;
		while (i < mp && !todiscover.isEmpty()) {
			j = 0;
			size = todiscover.size();
			while (j < size) {
				int current = todiscover.get(0);
				int xj = current / height;
				int yj = current % height;
				if (!nonobstacles.contains(current-height) && !todiscover.contains(current-height) && 0 != xj) {
					if (!Tile.isTileBlocking(map[xj-1][yj])) {
						todiscover.add(current-height);
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
				if (!nonobstacles.contains(current+height) && !todiscover.contains(current+height) && width -1 != xj) {
					if (!Tile.isTileBlocking(map[xj+1][yj])) {
						todiscover.add(current+height);
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
				if (!nonobstacles.contains(current-1) && !todiscover.contains(current-1) && 0 != yj) {
					if (!Tile.isTileBlocking(map[xj][yj-1])) {
						todiscover.add(current-1);
					} else {
						if (1 != yj && entrydoors.containsKey(current) && entrydoors.containsKey(current - 2) && doors.containsKey(current - 1)) {
							entrydoors.get(current).setEntryOut(entrydoors.get(current - 2));
							entrydoors.get(current - 2).setEntryOut(entrydoors.get(current));
							entrydoors.get(current).setOut(arenanumber);
							entrydoors.get(current - 2).setIn(arenanumber);
							entrydoors.get(current - 2).addDoor(doors.get(current - 1));
							int k = 1;
							/*String ss = "doors: ";
							for (Integer intt : doors.keySet())
								ss += intt + " ";*/
							
							while(xj + k != width && doors.containsKey(current - 1 + k*height)) {
								entrydoors.get(current - 2).addDoor(doors.get(current - 1 + height*k));
								k++;
							}
							k = -1;
							while(xj + k != -1 && doors.containsKey(current - 1 + k*height)) {
								entrydoors.get(current - 2).addDoor(doors.get(current - 1 + height*k));
								k--;
							}
						}
					}
				}
				if (!nonobstacles.contains(current+1) && !todiscover.contains(current+1) && height-1 != yj) {
					if (!Tile.isTileBlocking(map[xj][yj+1])) {
						todiscover.add(current+1);
					} else {
						if (height - 2 != yj && entrydoors.containsKey(current) && entrydoors.containsKey(current + 2) && doors.containsKey(current + 1)) {
							entrydoors.get(current).setEntryOut(entrydoors.get(current + 2));
							entrydoors.get(current + 2).setEntryOut(entrydoors.get(current));
							entrydoors.get(current).setOut(arenanumber);
							entrydoors.get(current + 2).setIn(arenanumber);
							entrydoors.get(current + 2).addDoor(doors.get(current + 1));
							int k = 1;
							while(xj + k != width && doors.containsKey(current + 1 + k * height)) {
								entrydoors.get(current + 2).addDoor(doors.get(current + 1 + height*k));
								k++;
							}
							k = -1;
							while(xj + k != -1 && doors.containsKey(current + 1 + k * height)) {
								entrydoors.get(current + 2).addDoor(doors.get(current + 1 + height*k));
								k--;
							}
						}
					}
				}
				todiscover.remove(0);
				nonobstacles.add(current);
				j++;
			}
			i++;
		}
		if (i == mp) {
			nonobstacles.clear();
		}
	}
	
	public static void getHiddenAccesibleArea(int x, int y, int mp, char[][] map, HashMap<Integer,Character> area, HashMap<Integer,HiddenTrigger> hiddentriggers, int hiddenareanumber) {
		area.clear();
		int width = map.length;
		int height = map[0].length;
		ArrayList<Integer> todiscover = new ArrayList<Integer>();
		todiscover.add(height*x+y);
		area.put(height*x+y, map[x][y]);
		int i = 0;
		int j = 0;
		int size;
		while (i < mp && !todiscover.isEmpty()) {
			j = 0;
			size = todiscover.size();
			while (j < size) {
				int current = todiscover.get(0);
				int xj = current / height;
				int yj = current % height;
				if (!area.containsKey(current-height) && !todiscover.contains(current-height) && 0 != xj) {
					if ('m' == map[xj-1][yj] && hiddentriggers.containsKey(current-height)) {
						hiddentriggers.get(current-height).setHiddenAreaNumber(hiddenareanumber);
					} else if (!Tile.isTileBlocking(map[xj-1][yj])) {
						todiscover.add(current-height);
						area.put(current-height,map[xj-1][yj]);
					}
				}
				if (!area.containsKey(current+height) && !todiscover.contains(current+height) && width -1 != xj) {
					if ('m' == map[xj+1][yj] && hiddentriggers.containsKey(current+height)) {
						hiddentriggers.get(current+height).setHiddenAreaNumber(hiddenareanumber);
					} else if (!Tile.isTileBlocking(map[xj+1][yj])) {
						todiscover.add(current+height);
						area.put(current+height,map[xj+1][yj]);
					}
				}
				if (!area.containsKey(current-1) && !todiscover.contains(current-1) && 0 != yj) {
					if ('m' == map[xj][yj-1] && hiddentriggers.containsKey(current-1)) {
						hiddentriggers.get(current-1).setHiddenAreaNumber(hiddenareanumber);
					} else if (!Tile.isTileBlocking(map[xj][yj-1])) {
						todiscover.add(current-1);
						area.put(current-1,map[xj][yj-1]);
					}
				}
				if (!area.containsKey(current+1) && !todiscover.contains(current+1) && height-1 != yj) {
					if ('m' == map[xj][yj+1] && hiddentriggers.containsKey(current+1)) {
						hiddentriggers.get(current+1).setHiddenAreaNumber(hiddenareanumber);
					} else if (!Tile.isTileBlocking(map[xj][yj+1])) {
						todiscover.add(current+1);
						area.put(current+1,map[xj][yj+1]);
					}
				}
				todiscover.remove(0);
				j++;
			}
			i++;
		}
		if (i == mp) {
			area.clear();
		}
	}
	
}
