
import java.io.IOException;

public class Main implements Runnable {
    
    long xTime = System.nanoTime();
    public static boolean terminator = false;
    public static int pWins = 0;
    public static int dWins = 0;
    
    //Rata de refresh
    public int Hz = 60;
    
    GUI gui = new GUI();
    
    public static void main(String[] args) throws IOException {
      //  new Thread(new Main()).start();
    //un fir java-un procesor virtual care poate executa codul in interiorul aplicatiei
    login frame=new login ();
    }
    
    public void run() {
        while(terminator == false) {
            if (System.nanoTime() - xTime >= 1000000000/Hz) {
                gui.refresher();
                gui.repaint();
                xTime = System.nanoTime();
            }
        }
    }
    
}