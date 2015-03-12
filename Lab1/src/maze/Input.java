package maze;

import java.util.Scanner;

public class Input {
    Scanner consoleInput;
    public String readConsoleInput(){
        return consoleInput.next();
    }
    Input(){
        consoleInput = new Scanner(System.in);
    }
}
