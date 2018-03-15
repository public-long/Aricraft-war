package cn.shoot;

import java.awt.Image;

public class BbulletAndPlane {
	//定义一个标识判断是否碰撞
	boolean flg = false;
	//定义一个方法用于判断Boss机子弹和英雄机的碰撞
	public boolean BbulletAndPlaneCollision(int px,int py,Image pImg,BossBullet bbullet){
		int px1 = px+pImg.getWidth(null)/2;
		int py1 = py+pImg.getHeight(null)/2;
		int pw = pImg.getWidth(null)/2;
		int ph = pImg.getHeight(null)/2;
		int bbx1 = bbullet.bbx+bbullet.bbImg.getWidth(null)/2;
		int bby1 = bbullet.bby+bbullet.bbImg.getHeight(null)/2;
		int bbw = bbullet.bbImg.getWidth(null)/2;
		int bbh = bbullet.bbImg.getHeight(null)/2;
		if(Math.sqrt(Math.pow(px1-bbx1, 2)+Math.pow(py1-bby1, 2))<=pw+bbw||Math.sqrt(Math.pow(px1-bbx1, 2)+Math.pow(py1-bby1, 2))<=ph+bbh){
			flg = true;
		}
//		if (px - bbullet.bbx <= bbullet.bbImg.getWidth(null) && py - bbullet.bby <= bbullet.bbImg.getHeight(null) && 
//				px - bbullet.bbx >= -pImg.getWidth(null) && py - bbullet.bby >= -pImg.getHeight(null)) {
//			flg = true;
//		}
		return flg;

	}
}
