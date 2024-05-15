package Project;

public class GameUI implements Observer{
    private MineSweeper mineSweeper;
    public GameUI(MineSweeper mineSweeper) {
        this.mineSweeper = mineSweeper;
        mineSweeper.registerObserver(this);
    }

    @Override
    public void update(){
        
    }
    @Override
    public void updateGame(int x,int y, int flag){
        if (flag == 1) {
            mineSweeper.toggleFlag(x,y);
        }else if(flag == 2){
            mineSweeper.showButton(x,y);
        }
        else if(flag == 3){
            mineSweeper.updateFocus();
        }
        
    }
}
