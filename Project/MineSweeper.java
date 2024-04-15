package Project;

public class MineSweeper {
    public static void restart(){
        //This will restart the whole game and all it's content with the settings currently equiped
        System.out.println("restarted"); //Debugging var
    }

    public static void main(String[] args) {
        new grid();
        new settings();
    }
}
