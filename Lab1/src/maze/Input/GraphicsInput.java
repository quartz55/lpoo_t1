package maze.Input;

import java.util.LinkedList;

public class GraphicsInput implements Input{
	
	LinkedList<String> inputQueue;
	
	public GraphicsInput(){
		this.inputQueue = new LinkedList<String>();
	}

	@Override
	public String getInput() {
		if(inputQueue.isEmpty()) return "";
		return inputQueue.poll();
	}
	
	public void addInput(String newInput){
		inputQueue.add(newInput);
	}

}
