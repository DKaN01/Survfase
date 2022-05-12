import Core.*;
import Window.*;

public class Launcher {
    public static void main(String[] args){
        Keyhandler kh = new Keyhandler();
        Panel pn = new Panel(64,20,12,kh);
        Window win = new Window(pn,"Surfase");
        win.Visible(true);
        Core core = new Core(pn,win,kh);
    }
}
