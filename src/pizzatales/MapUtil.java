package pizzatales;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

public class MapUtil {

	public static void getRing(int x, int y, int mp, ArrayList<Integer> ring) {
		ring.clear();
		ArrayList<Integer> todiscover = new ArrayList<Integer>();
		todiscover.add(StartingClass.height*x+y);
		ArrayList<Integer> nonobstacles = new ArrayList<Integer>();
		int i = 0;
		int j = 0;
		int size;
		boolean test;
		while (i < mp && !todiscover.isEmpty()) {
			j = 0;
			size = todiscover.size();
			while (j < size) {
				int current = todiscover.get(0);
				int xj = current / StartingClass.height;
				int yj = current % StartingClass.height;
				test = false;
				if (!nonobstacles.contains(current-StartingClass.height) && !todiscover.contains(current-StartingClass.height) && 0 != xj) {
					if (null == StartingClass.map[xj-1][yj]) {
						todiscover.add(current-StartingClass.height);
					} else
						test = true;
				}
				if (!nonobstacles.contains(current+StartingClass.height) && !todiscover.contains(current+StartingClass.height) && StartingClass.width -1 != xj) {
					if (null == StartingClass.map[xj+1][yj]) {
						todiscover.add(current+StartingClass.height);
					} else
						test = true;
				}
				if (!nonobstacles.contains(current-1) && !todiscover.contains(current-1) && 0 != yj) {
					if (null == StartingClass.map[xj][yj-1]) {
						todiscover.add(current-1);
					} else
						test = true;
				}
				if (!nonobstacles.contains(current+1) && !todiscover.contains(current+1) && StartingClass.height-1 != yj) {
					if (null == StartingClass.map[xj][yj+1]) {
						todiscover.add(current+1);
					} else
						test = true;
				}
				if (xj != 0) {
					if (yj != 0 && null != StartingClass.map[xj-1][yj-1])
						test = true;
					if (yj != StartingClass.height-1 && null != StartingClass.map[xj-1][yj+1])
						test = true;
				}
				if (xj != StartingClass.width - 1) {
					if (yj != 0 && null != StartingClass.map[xj+1][yj-1])
						test = true;
					if (yj != StartingClass.height-1 && null != StartingClass.map[xj+1][yj+1])
						test = true;
				}
				if (test)
					ring.add(current);
				todiscover.remove(0);
				nonobstacles.add(current);
				j++;
			}
			i++;
		}
		if (i == mp) {
			nonobstacles.clear();
			ring.clear();
		}
	}
	
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
					if (!TileFactory.isNonDestroyableTile(map[xj-1][yj])) {
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
					if (!TileFactory.isNonDestroyableTile(map[xj+1][yj])) {
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
					if (!TileFactory.isNonDestroyableTile(map[xj][yj-1])) {
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
					if (!TileFactory.isNonDestroyableTile(map[xj][yj+1])) {
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
					} else if (!TileFactory.isTileTypeSupported(map[xj-1][yj])) {
						todiscover.add(current-height);
						area.put(current-height,map[xj-1][yj]);
					}
				}
				if (!area.containsKey(current+height) && !todiscover.contains(current+height) && width -1 != xj) {
					if ('m' == map[xj+1][yj] && hiddentriggers.containsKey(current+height)) {
						hiddentriggers.get(current+height).setHiddenAreaNumber(hiddenareanumber);
					} else if (!TileFactory.isTileTypeSupported(map[xj+1][yj])) {
						todiscover.add(current+height);
						area.put(current+height,map[xj+1][yj]);
					}
				}
				if (!area.containsKey(current-1) && !todiscover.contains(current-1) && 0 != yj) {
					if ('m' == map[xj][yj-1] && hiddentriggers.containsKey(current-1)) {
						hiddentriggers.get(current-1).setHiddenAreaNumber(hiddenareanumber);
					} else if (!TileFactory.isTileTypeSupported(map[xj][yj-1])) {
						todiscover.add(current-1);
						area.put(current-1,map[xj][yj-1]);
					}
				}
				if (!area.containsKey(current+1) && !todiscover.contains(current+1) && height-1 != yj) {
					if ('m' == map[xj][yj+1] && hiddentriggers.containsKey(current+1)) {
						hiddentriggers.get(current+1).setHiddenAreaNumber(hiddenareanumber);
					} else if (!TileFactory.isTileTypeSupported(map[xj][yj+1])) {
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
	/*
	public static void bitmask(Image[][] background, Image path, Image outside, Image[] cornerset) {
		if (cornerset.length != 16)
			return;
		int tmp;
		boolean [][] bitmap = new boolean[StartingClass.width][StartingClass.height];
		for (int i = 0; i < StartingClass.width; i++) {
			for (int j = 0; j < StartingClass.height; j++) {
				if (outside.equals(background[i][j]))
					bitmap[i][j] = true;
			}
		}
		for (int i = 0; i < StartingClass.width; i++) {
			for (int j = 0; j < StartingClass.height; j++) {
				tmp = 0;
				if (path.equals(background[i][j])) {
					if (j > 0 && bitmap[i][j-1])
						tmp++;
					if (j < StartingClass.height-1 && bitmap[i][j+1])
						tmp += 8;
					if (i > 0 && bitmap[i-1][j])
						tmp += 2;
					if (i < StartingClass.width-1 && bitmap[i+1][j])
						tmp += 4;
					background[i][j] = cornerset[tmp];
				}
			}
		}
	}
	Image[][] mask, 
	*/
	
	public static void bitmask(Image[][] background, Image[][][] mask, Image path, Image outside, Image[] cornerset, int height) {
		if (cornerset.length != 16)
			return;
		int tmp;
		//outside.equals(background[i][j])
		for (int i = 0; i < StartingClass.width; i++) {
			for (int j = 0; j < StartingClass.height; j++) {
				tmp = 0;
				if (path.equals(background[i][j])) {
					if (j > 0 && outside == background[i][j-1])
						tmp++;
					if (j < StartingClass.height-1 && outside == background[i][j+1])
						tmp += 8;
					if (i > 0 && outside == background[i-1][j])
						tmp += 2;
					if (i < StartingClass.width-1 && outside == background[i+1][j])
						tmp += 4;
					if (tmp > 0)
						mask[i][j][height] = cornerset[tmp];
				}
			}
		}
	}
	
	public static void bitmask(Image[][] background, Image[][][] mask, Image path, Image outside, Image[] cornerset, int height, int minx, int maxx, int miny, int maxy) {
		if (cornerset.length != 16)
			return;
		int tmp;
		//outside.equals(background[i][j])
		for (int i = minx; i <= maxx; i++) {
			for (int j = miny; j <= maxy; j++) {
				tmp = 0;
				if (path.equals(background[i][j])) {
					if (j > 0 && outside == background[i][j-1])
						tmp++;
					if (j < StartingClass.height-1 && outside == background[i][j+1])
						tmp += 8;
					if (i > 0 && outside == background[i-1][j])
						tmp += 2;
					if (i < StartingClass.width-1 && outside == background[i+1][j])
						tmp += 4;
					if (tmp > 0)
						mask[i][j][height] = cornerset[tmp];
				}
			}
		}
	}
}
