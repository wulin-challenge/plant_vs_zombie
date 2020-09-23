package cn.wulin.plant.vs.zombie;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sun.awt.image.URLImageSource;

public class TestImageGif extends JPanel {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		TestImageGif testImageGif = new TestImageGif();
		JFrame frame = new JFrame();
		frame.add(testImageGif);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	
	// 加载img图片
		public static BufferedImage loadImage(String fileName) {
			URL resource = Thread.currentThread().getContextClassLoader().getResource("images/" + fileName);
			System.out.println(resource.toString());
			try {
				BufferedImage img = ImageIO.read(resource);
				img = resize(img, 100, 100);

				return img;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		
	Toolkit toolkit =Toolkit.getDefaultToolkit();
	Image createImage = updateImage();
	
	public Image updateImage() {
		Image image = toolkit.getImage(loadImage2("sunflower0.gif"));
		image = image.getScaledInstance(100, 100, 10);
		return image;
	}
	
	@Override
	public void paint(Graphics g) {
		
//		g.drawImage(loadImage("sunflower0.gif"), 0, 0, null);
		
		g.drawImage(createImage, 0, 0, this);
		
	}
	
	public URL loadImage2(String fileName) {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("images/" + fileName);
		return resource;
	}

	/**
	 * 调整图片大小
	 * @param img
	 * @param newW
	 * @param newH
	 * @return
	 */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

}
