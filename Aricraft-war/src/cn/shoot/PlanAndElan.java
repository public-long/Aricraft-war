package cn.shoot;

import java.awt.Image;

public class PlanAndElan {
	//定义一个标识判断是否碰撞
	boolean flg = false;
	//定义一个方法判断英雄机和敌机的碰撞
	public boolean planAndElanCollision(int px,int py,Image pImg,Elan elan){
		int px1 = px+pImg.getWidth(null)/2;
		int py1 = py+pImg.getHeight(null)/2;
		int pw = pImg.getWidth(null)/2;
		int ph = pImg.getHeight(null)/2;
		int ex1 = elan.ex+elan.eImg.getWidth(null)/2;
		int ey1 = elan.ey+elan.eImg.getHeight(null)/2;
		int ew = elan.eImg.getWidth(null)/2;
		int eh = elan.eImg.getHeight(null)/2;
		if(Math.sqrt(Math.pow(ex1-px1, 2)+Math.pow(ey1-py1, 2))<=pw+ew||Math.sqrt(Math.pow(ex1-px1, 2)+Math.pow(ey1-py1, 2))<=ph+eh){
			flg = true;
		}
		return flg;
	}
}
