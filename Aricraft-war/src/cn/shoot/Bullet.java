package cn.shoot;

import java.awt.Graphics;
import java.awt.Image;

public class Bullet {
	//����ͼƬ�Ķ���
	Image bImg;
	//�����ڵ�������
	int bx ; int by;
	//����һ�������ͱ������ڱ�ʾ�ڵ��Ƿ����
	boolean exist = true;
	//���췽����ɳ�ʼ��
	public Bullet(Image bImg, int bx, int by) {
		super();
		this.bImg = bImg;
		this.bx = bx;
		this.by = by;
	}
	
	//�Զ���һ���������ڻ��ڵ�
	public void drawBullet(Graphics g){
		g.drawImage(bImg, bx, by, null);
	}
	
	//�Զ���һ�����������ڵ����ƶ�
	public void moveBullet(){
		by--;
		if(by==-bImg.getWidth(null)){
			exist = false;
		}
	}
}
