package cn.shoot;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class BossBullet {
	//定义图片的对象
	Image bbImg;
	//定义炮弹的坐标
	int bbx ; int bby;
	//定义一个布尔型变量用于表示炮弹是否存在
	boolean exist = true;
	int f=0;
	//构造方法完成初始化
	public BossBullet(Image bbImg, int bbx, int bby) {
		super();
		this.bbImg = bbImg;
		this.bbx = bbx;
		this.bby = bby;
	}
			
	//自定义一个方法用于画炮弹
	public void drawBullet(Graphics g){
		g.drawImage(bbImg, bbx, bby, null);
	}
			
	//创建一个Boss机图片对象
	static Image bossImg = new ImageIcon("images/BossPlane/plane_5.png").getImage();
	
	//自定义一个方法用于Boss机的炮弹的移动
	public void moveElanBullet(){
		bby+=1;
		if(bby==600+bbImg.getWidth(null)){
			exist = false;
		}
		
	}
}
