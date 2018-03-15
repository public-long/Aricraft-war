package cn.shoot;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class BossBullet {
	//����ͼƬ�Ķ���
	Image bbImg;
	//�����ڵ�������
	int bbx ; int bby;
	//����һ�������ͱ������ڱ�ʾ�ڵ��Ƿ����
	boolean exist = true;
	int f=0;
	//���췽����ɳ�ʼ��
	public BossBullet(Image bbImg, int bbx, int bby) {
		super();
		this.bbImg = bbImg;
		this.bbx = bbx;
		this.bby = bby;
	}
			
	//�Զ���һ���������ڻ��ڵ�
	public void drawBullet(Graphics g){
		g.drawImage(bbImg, bbx, bby, null);
	}
			
	//����һ��Boss��ͼƬ����
	static Image bossImg = new ImageIcon("images/BossPlane/plane_5.png").getImage();
	
	//�Զ���һ����������Boss�����ڵ����ƶ�
	public void moveElanBullet(){
		bby+=1;
		if(bby==600+bbImg.getWidth(null)){
			exist = false;
		}
		
	}
}
