package cn.shoot;

import java.awt.Image;

public class EbulletAndPlane {
	//定义一个标识判断是否碰撞
	boolean flg = false;
	//定义一个方法用于判断敌机子弹和英雄机的碰撞
	public boolean EbulletAndPlaneCollision(int px,int py,Image pImg,ElanBullet ebullet){
		int px1 = px+pImg.getWidth(null)/2;
		int py1 = py+pImg.getHeight(null)/2;
		int pw = pImg.getWidth(null)/2;
		int ph = pImg.getHeight(null)/2;
		int ebx1 = ebullet.ebx+ebullet.ebImg.getWidth(null)/2;
		int eby1 = ebullet.eby+ebullet.ebImg.getHeight(null)/2;
		int ebw = ebullet.ebImg.getWidth(null)/2;
		int ebh = ebullet.ebImg.getHeight(null)/2;
		if(Math.sqrt(Math.pow(px1-ebx1, 2)+Math.pow(py1-eby1, 2))<=pw+ebw||Math.sqrt(Math.pow(px1-ebx1, 2)+Math.pow(py1-eby1, 2))<=ph+ebh){
			flg = true;
		}
		return flg;
	}
}
