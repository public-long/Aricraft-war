package cn.shoot;

import java.awt.Graphics;
import java.awt.Image;

public class BossElan {
	//定义Boos敌机图片
	Image bossImg;
	//定义敌机坐标
	int bx,by;
	//定义Boss敌机的生命值
	int hp;
	//定义Boss机生存状态
	boolean exist = true;
	//定义敌机速度
	int speed;
	//定义Boss机爆炸显示的时间点
	int time=5;
	//定义敌机的分数
	int score;
	int f;
	//构造方法完成初始化
	public BossElan(Image bossImg, int bx, int by, int hp, int speed, int score) {
		super();
		this.bossImg = bossImg;
		this.bx = bx;
		this.by = by;
		this.hp = hp;
		this.speed = speed;
		this.score = score;
	}
	
	//自定义方法画敌机
			public void drawBoss(Graphics g){
				g.drawImage(bossImg, bx, by, null);
			}
	//自定义一个方法完成Boss机的运动
	public void moveBoss(){
		if(f==0){
			bx++;
		}
		if(f==1){
			bx--;
		}
		if(bx+bossImg.getWidth(null)>=400){
			f=1;
		}
		if(bx<=0){
			f=0;
		}
	}
	
	
}
