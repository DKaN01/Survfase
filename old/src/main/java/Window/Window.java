package Window;

import javax.swing.JFrame;

public class Window extends JFrame{
	public Window(Panel panel, String title) {
		this.add(panel);
		this.pack();
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	public void Visible(boolean mode) {
		this.setVisible(mode);
	}
}