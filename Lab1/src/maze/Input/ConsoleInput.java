package maze.Input;

import java.util.Scanner;

public class ConsoleInput implements Input{
    Scanner consoleInput;
    
    @Override
    public String getInput(){
        return consoleInput.next();
    }

    public ConsoleInput(){
        consoleInput = new Scanner(System.in);
    }

	@Override
	public void addInput(String string) {
		// TODO Auto-generated method stub
		
	}
}
