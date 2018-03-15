package cn.shoot;

import java.awt.Graphics;
import java.awt.Image;

public class ElanBullet {
	//定义图片的对象
		Image ebImg;
		//定义炮弹的坐标
		int ebx ; int eby;
		//定义一个布尔型变量用于表示炮弹是否存在
		boolean exist = true;
		//构造方法完成初始化
		public ElanBullet(Image ebImg, int ebx, int eby) {
			super();
			this.ebImg = ebImg;
			this.ebx = ebx;
			this.eby = eby;
		}
		
		//自定义一个方法用于画炮弹
		public void drawBullet(Graphics g){
			g.drawImage(ebImg, ebx, eby, null);
		}
		

		//自定义一个方法用于敌机的炮弹的移动
		public void moveElanBullet(){
			eby+=3;
			if(eby==600+ebImg.getWidth(null)){
				exist = false;
			}
		}
}
