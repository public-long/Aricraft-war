package cn.shoot;

import java.awt.Graphics;
import java.awt.Image;

public class Elan {
	//����л�ͼƬ
	Image eImg;
	//����л�����
	int ex,ey;
	//����л�����ֵ
	int hp;
	//����л�����״̬
	boolean exist = true;
	//����л��ٶ�
	int speed;
	//����л���ը��ʾ��ʱ���
	int time=5;
	//����л��ķ���
	int score;
	//���췽����ɳ�ʼ��
	public Elan(Image eImg, int ex, int ey, int hp, int speed, int score) {
		super();
		this.eImg = eImg;
		this.ex = ex;
		this.ey = ey;
		this.hp = hp;
		this.speed = speed;
		this.score = score;
	}
	
	//�Զ��巽�����л�
	public void drawElan(Graphics g){
		g.drawImage(eImg, ex, ey, null);
		
	}
	//�Զ���һ��������ɵл��˶�
	public void moveElan(){
		ey+=speed;
		if(ey>=600){
			exist = false;
		}
	}
}











