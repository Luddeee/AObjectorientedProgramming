package Project;

public class MineSweeper {
    public static void restart(){
        //This will restart the whole game and all it's content with the settings currently equiped
        System.out.println("restarted"); //testing var
    }

    public static void load(){
        //Loads and starts the game, if game is already running the game instead restarts using the restart method and then calls itself to begin again
        System.out.println("Loaded"); //Testing var
    }

    public static void main(String[] args) {
        new grid();
        new settings();
    }
}
