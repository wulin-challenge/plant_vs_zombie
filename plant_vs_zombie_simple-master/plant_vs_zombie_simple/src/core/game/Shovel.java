package core.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Shovel {
	
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
	
	// 铲子
	// 加载图片
	public static BufferedImage loadImage(String fileName) {
		try {
			BufferedImage img = ImageIO.read(Background.class.getResource(fileName));
			img = resize(img, 76, 34);
			return img;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	private static BufferedImage image;
	static {
		image = loadImage("shovel.png");
	}
	
	// 获取图片
	public BufferedImage getImage() {
		if(state==WAIT ||state==MOVE) {
			return image;
		}else {
			return null;
		}
	}
	
	// 画图片
	public void paintObject(Graphics g) {
		g.drawImage(getImage(),x,y,null); 
	}
	
	// 基本属性
	private int x;
	private int y;
	private int width;
	private int height;
	
	// 构造方法
	public Shovel() {
		this.x = 90;
		this.y = 0;
		this.width = 76;
		this.height = 34;
	}
	
	// 获取x,y,宽高
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
	// 铲子的状态
	public static final int WAIT = 0;
	public static final int MOVE = 1;
	public static final int REMOVE = 2;
	public int state = WAIT;
	
	// 判断铲子状态
	public boolean isWait() {
		return state==WAIT;
	}
	public boolean isMove() {
		return state==MOVE;
	}
	public boolean isRemove() {
		return state==REMOVE;
	}
	
	// 改变铲子状态
	public void goMove() {
		state = MOVE;
	}
	public void goRemove() {
		state = REMOVE;
	}
	
	// 铲子的移动
	public void moveTo(int x,int y) {
		this.x = x-this.width/2;
		this.y = y-this.height/2;
	}
	
}
