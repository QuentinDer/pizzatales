package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class Cutscene {

	private ArrayList<Scene> listScenes;
	private int i;
	
	public Cutscene() {
		listScenes = new ArrayList<Scene>();
		i = 0;
	}
	
	public void addScene(Scene scene) {
		listScenes.add(scene);
	}
	
	public void pass() {
		if (!listScenes.get(i).isFullyRendered())
			listScenes.get(i).fullRender();
		else
			i++;
	}
	
	public void incrementCutsceneViewing() {
		listScenes.get(i).incrementCutsceneViewing();
	}
	
	public String getRenderedText() {
		return listScenes.get(i).getRenderedText();
	}
	
	public Image getPicture() {
		return listScenes.get(i).getPicture();
	}
	
	public boolean isCutsceneFinished() {
		return i>=listScenes.size();
	}
	
	public String getTitle() {
		return listScenes.get(i).getTitle();
	}
	
}
