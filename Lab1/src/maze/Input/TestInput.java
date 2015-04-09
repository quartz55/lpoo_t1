package maze.Input;

public class TestInput implements Input{
	String[] inputArray;
	int currentIndex = 0;
	
	@Override
	public String getInput() {
		if(currentIndex >= inputArray.length) return "q";
		return inputArray[currentIndex++];
	}

	public TestInput(String[] inputArray){
		this.inputArray = inputArray;
	}

	@Override
	public void addInput(String string) {
		// TODO Auto-generated method stub
		
	}


}
