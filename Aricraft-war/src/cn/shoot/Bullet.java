package cn.shoot;

import java.awt.Graphics;
import java.awt.Image;

public class Bullet {
	//定义图片的对象
	Image bImg;
	//定义炮弹的坐标
	int bx ; int by;
	//定义一个布尔型变量用于表示炮弹是否存在
	boolean exist = true;
	//构造方法完成初始化
	public Bullet(Image bImg, int bx, int by) {
		super();
		this.bImg = bImg;
		this.bx = bx;
		this.by = by;
	}
	
	//自定义一个方法用于画炮弹
	public void drawBullet(Graphics g){
		g.drawImage(bImg, bx, by, null);
	}
	
	//自定义一个方法用于炮弹的移动
	public void moveBullet(){
		by--;
		if(by==-bImg.getWidth(null)){
			exist = false;
		}
	}
}
