package cn.wulin.plant.vs.zombie;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class LoadSystemWaitting {
	private static LoadSystemWaitting loadSystemWaitting;
	private Image image;
	private JWindow jw;
	public static void main(String args[]){
		LoadSystemWaitting.getInstance(true);
	}
	public static void instanceInit(){
		if (loadSystemWaitting == null) {
			loadSystemWaitting = new LoadSystemWaitting();
		}
	}
	public static LoadSystemWaitting getInstance(boolean alwaysOnTop) {
		if (loadSystemWaitting == null) {
			loadSystemWaitting = new LoadSystemWaitting();
		}
//		loadSystemWaitting.jw.setAlwaysOnTop(alwaysOnTop);
		loadSystemWaitting.setVisible(true);
		return loadSystemWaitting;
	}
 
	private JPanel getJPanel() {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("images/sunflower0.gif");
		Image createImage = Toolkit.getDefaultToolkit().createImage(resource);
		
		
		JPanel jp = new JPanel() {
 
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
//				g.drawImage(image, 0, 0, this);
				
				
				g.drawImage(createImage, 0, 0, this);
			}
 
		};
		return jp;
	}
 
	public static void closeWindow() {
		if (loadSystemWaitting != null) {
			loadSystemWaitting.setVisible(false);
		}
	}
	public void setVisible(boolean b){
		jw.setVisible(b);
	}
	
	public URL loadImage(String fileName) {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("images/" + fileName);
		return resource;
	}
 
 
	private LoadSystemWaitting() {
		image = Toolkit.getDefaultToolkit().createImage(loadImage("sunflower0.gif"));
		
//		URL resource = LoadSystemWaitting.class
//		.getResource("/cn/wulin/plant/vs/zombie/sunflower0.gif");
//		image = Toolkit.getDefaultToolkit().createImage(resource
//				);
		jw = new JWindow();
		jw.setSize(145, 150);
		jw.setLayout(new BorderLayout());
		jw.add(getJPanel(), BorderLayout.CENTER);
		jw.setLocationRelativeTo(null);
	}
 
}
