package core.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import core.plants.Plant;

/**
 * 鼠标事件
 * @author wulin
 *
 */
public class GameMouseEvent extends MouseAdapter{
	// 滚轮机上的植物，状态为stop和wait
	private List<Plant> plants = new ArrayList<Plant>();
	
	// 草地集合
	private List<Glass> glasses = new ArrayList<Glass>();
	
	// 铲子
	private List<Shovel> shovels = new ArrayList<Shovel>();
	
	/**
	 * 鼠标当前选中的对象
	 */
	private Object selectedObj = null;
	
	public GameMouseEvent(List<Plant> plants, List<Glass> glasses, List<Shovel> shovels) {
		this.plants = plants;
		this.glasses = glasses;
		this.shovels = shovels;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 获得鼠标的坐标
		int mx = e.getX();
		int my = e.getY();
		
		getSelectedPlant(mx, my);
		getSelectedShovel(mx, my);
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// 获得鼠标的坐标
		int mx = e.getX();
		int my = e.getY();
		if(selectedObj != null && GamePlay.getState() == GamePlay.RUNNING) {
			
			moveSelectedPlant(mx, my);
			moveSelectedShovel(mx, my);
			return;
		}
		
		
	}
	
	/**
	 * 移动被选中的植物
	 * @param mx
	 * @param my
	 */
	private void moveSelectedPlant(int mx ,int my) {
		if(selectedObj instanceof Plant) {
			Plant plant = (Plant)selectedObj;
			if(plant.isMove()) {
				plant.moveTo(mx, my);
			}
		}
	}
	
	/**
	 * 移动被选中的铲子
	 * @param mx
	 * @param my
	 */
	private void moveSelectedShovel(int mx ,int my) {
		if(selectedObj instanceof Shovel) {
			Shovel shovel = (Shovel)selectedObj;
			if(shovel.isMove()) {
				shovel.moveTo(mx, my);
			}
		}
	}

	/**
	 * 获取点击滚轮机上的植物
	 * @param mx 鼠标x坐标
	 * @param my 鼠标y坐标
	 * @return
	 */
	private void getSelectedPlant(int mx ,int my) {
		if(selectedObj != null) {
			return;
		}
		for (Plant p : new ArrayList<>(plants)) {
			if(p != null) {
				int x1 = p.getX();
				int x2 = p.getX()+p.getWidth();
				int y1 = p.getY();
				int y2 = p.getY()+p.getHeight();						
				if(mx>=x1&&mx<=x2&&my>=y1&&my<=y2) {
					p.goMove();
					
					selectedObj = p;
					break;
				}
			}
		}
	}
	
	/**
	 * 获取点击铲子
	 * @param mx 鼠标x坐标
	 * @param my 鼠标y坐标
	 * @return
	 */
	private void getSelectedShovel(int mx ,int my) {
		if(selectedObj != null) {
			return;
		}
		for (Shovel shovel : new ArrayList<>(shovels)) {
			if(shovel != null) {
				int x1 = shovel.getX();
				int x2 = shovel.getX()+shovel.getWidth();
				int y1 = shovel.getY();
				int y2 = shovel.getY()+shovel.getHeight();						
				if(mx>=x1&&mx<=x2&&my>=y1&&my<=y2) {
					shovel.goMove();
					selectedObj = shovel;
					break;
				}
			}
		}
	}
}
