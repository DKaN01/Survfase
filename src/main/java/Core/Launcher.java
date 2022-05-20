package Core;

import Window.*;

public class Launcher {
	public static void main(String[] args) {
		Keyhandler kh = new Keyhandler();
		Panel panel = new Panel(16,80,45, kh);
		Window win = new Window(panel, "Surv");
		win.Visible(true);
		Core core = new Core(panel,win, kh);
	}
}