package core.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import core.zombies.Zombie;
import core.zombies.Zombie0;
import core.zombies.Zombie1;
import core.zombies.Zombie2;
import core.zombies.Zombie3;

/**
 * 僵尸入场控制器
 * @author wulin
 *
 */
public class ZombieEnterController {

	// 设置进场间隔,单位是每10ms加1,其实也是游戏开始的时长
	private int zombieEnterTime = 0;
	
	private List<ZombieEnterSlot> enterTimeSlot = new ArrayList<>();
	
	public ZombieEnterController() {
		//入场时间段
		addZombieEnterSlot(0, 6000, 300,1);
		//休息时间段
		addZombieEnterSlot(0, 300, 0,2);
		//------------------------------
		//入场时间段
		addZombieEnterSlot(0, 3000, 200,1);
		//休息时间段
		addZombieEnterSlot(0, 500, 0,2);
		//------------------------------
		//入场时间段
		addZombieEnterSlot(0, 6000, 100,1);
		//休息时间段
		addZombieEnterSlot(0, 500, 0,2);
		//------------------------------
		//入场时间段
		addZombieEnterSlot(0, 3000, 50,1);
		//休息时间段
		addZombieEnterSlot(0, 500, 0,2);
		//------------------------------
		//入场时间段
		addZombieEnterSlot(0, 3000, 30,1);
		//休息时间段
		addZombieEnterSlot(0, 500, 0,2);
		//------------------------------
		//入场时间段
		addZombieEnterSlot(0, 3000, 20,1);
		//休息时间段
		addZombieEnterSlot(0, 500, 0,2);
		//------------------------------
		//入场时间段
		addZombieEnterSlot(0, 3000, 15,1);
		//休息时间段
		addZombieEnterSlot(0, 500, 0,2);
		//------------------------------
		//入场时间段
		addZombieEnterSlot(0, 3000, 10,1);
		//休息时间段
		addZombieEnterSlot(0, 500, 0,2);
		//------------------------------
		//入场时间段
		addZombieEnterSlot(0, 3000, 30,1);
		//休息时间段
		addZombieEnterSlot(0, 500, 0,2);
		//------------------------------
		//入场时间段
		addZombieEnterSlot(0, 3000, 10,1);
		//休息时间段
		addZombieEnterSlot(0, 500, 0,2);
		//------------------------------
		//入场时间段
		addZombieEnterSlot(0, 3000, 20,1);
		//休息时间段
		addZombieEnterSlot(0, 500, 0,2);
		//------------------------------
		//入场时间段
		addZombieEnterSlot(0, 3000, 10,1);
		//休息时间段
		addZombieEnterSlot(0, 500, 0,2);
		//------------------------------
		//入场时间段
		addZombieEnterSlot(0, 6000, 50,1);
		//休息时间段
		addZombieEnterSlot(0, 500, 0,2);
		//------------------------------
		
		for (int i = 0; i < 1000; i++) {
			//入场时间段
			addZombieEnterSlot(0, 3000, 10,1);
			//休息时间段
			addZombieEnterSlot(0, 500, 0,2);
			//------------------------------
		}
	}
	
	private void addZombieEnterSlot(int startTime, int endTime, int addZombieTime, int state) {
		if(enterTimeSlot.size() == 0) {
			enterTimeSlot.add(new ZombieEnterSlot(startTime, endTime, addZombieTime, state));
		}else {
			ZombieEnterSlot last = enterTimeSlot.get(enterTimeSlot.size()-1);
			enterTimeSlot.add(new ZombieEnterSlot(last.getEndTime()+startTime, last.getEndTime()+endTime, addZombieTime, state));
		}
	}

	public void zombieEnter(List<Zombie> zombies) {
		zombieEnterTime++;
		
		for (ZombieEnterSlot slot : new ArrayList<>(enterTimeSlot)) {
			if(zombieEnterTime>= slot.getStartTime() && zombieEnterTime<slot.getEndTime()) {
				if(slot.getState() == 1) {
					if(zombieEnterTime%slot.getAddZombieTime()==0) {
						zombies.add(nextOneZombie());
						return;
					}
				}else if(slot.getState() == 2) {
					// 不执行任何添加僵尸的操作,表示添加添加僵尸停顿时间
				}
				return;
			}else {
				if(zombieEnterTime>slot.getEndTime()) {
					enterTimeSlot.remove(slot);
				}
				return;
			}
		}
	}
	
	// 生成僵尸
	private Zombie nextOneZombie() {
		Random rand = new Random();
		// 控制不同种类僵尸出现的概率
		int type = rand.nextInt(20);
		//	return new Zombie0();
		if(type<5) {
			return new Zombie0();
		}else if(type<10) {
			return new Zombie1();
		}else if(type<15) {
			return new Zombie2();
		}else {
			return new Zombie3();
		}
	}
	
}

/**
 * 僵尸入场时间段
 * @author wulin
 *
 */
class ZombieEnterSlot{
	/**
	 * 僵尸入场的开始时间/休息开始时间
	 */
	private int startTime;
	
	/**
	 * 僵尸入场的结束时间/休息结束时间
	 */
	private int endTime;
	
	/**
	 * 在该时间段内多少时间添加一个僵尸
	 */
	private int addZombieTime;
	
	/**
	 * 1: 表示僵尸入场时间段,2: 表示僵尸休息时间段,主要给用户喘息的时间
	 */
	private int state;
	
	/**
	 * 
	 * @param startTime 开始时间 单位 10ms
	 * @param endTime 结束时间,单位10ms
	 * @param addZombieTime 添加僵尸的时间 单位10ms
	 * @param state 是否添加僵尸的状态
	 */
	public ZombieEnterSlot(int startTime, int endTime, int addZombieTime, int state) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.addZombieTime = addZombieTime;
		this.state = state;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getAddZombieTime() {
		return addZombieTime;
	}

	public void setAddZombieTime(int addZombieTime) {
		this.addZombieTime = addZombieTime;
	}
}