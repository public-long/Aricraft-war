package cn.shoot;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class ShootJFrame extends JFrame{
	int width=400;
	int height=600 ;
	public ShootJFrame(){
		int pw = Toolkit.getDefaultToolkit().getScreenSize().width;
		int ph = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setTitle("·É»ú´óÕ½");
		this.setBounds((pw-width)/2, (ph-height)/2, 400, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ShootJPanel sp = new ShootJPanel();
		this.add(sp);
		this.setResizable(false);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new ShootJFrame();
	}
}
