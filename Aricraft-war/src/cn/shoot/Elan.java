package cn.shoot;

import java.awt.Graphics;
import java.awt.Image;

public class Elan {
	//定义敌机图片
	Image eImg;
	//定义敌机坐标
	int ex,ey;
	//定义敌机生命值
	int hp;
	//定义敌机生存状态
	boolean exist = true;
	//定义敌机速度
	int speed;
	//定义敌机爆炸显示的时间点
	int time=5;
	//定义敌机的分数
	int score;
	//构造方法完成初始化
	public Elan(Image eImg, int ex, int ey, int hp, int speed, int score) {
		super();
		this.eImg = eImg;
		this.ex = ex;
		this.ey = ey;
		this.hp = hp;
		this.speed = speed;
		this.score = score;
	}
	
	//自定义方法画敌机
	public void drawElan(Graphics g){
		g.drawImage(eImg, ex, ey, null);
		
	}
	//自定义一个方法完成敌机运动
	public void moveElan(){
		ey+=speed;
		if(ey>=600){
			exist = false;
		}
	}
}











