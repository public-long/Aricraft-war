package cn.shoot;

import java.awt.Graphics;
import java.awt.Image;

public class BossElan {
	//����Boos�л�ͼƬ
	Image bossImg;
	//����л�����
	int bx,by;
	//����Boss�л�������ֵ
	int hp;
	//����Boss������״̬
	boolean exist = true;
	//����л��ٶ�
	int speed;
	//����Boss����ը��ʾ��ʱ���
	int time=5;
	//����л��ķ���
	int score;
	int f;
	//���췽����ɳ�ʼ��
	public BossElan(Image bossImg, int bx, int by, int hp, int speed, int score) {
		super();
		this.bossImg = bossImg;
		this.bx = bx;
		this.by = by;
		this.hp = hp;
		this.speed = speed;
		this.score = score;
	}
	
	//�Զ��巽�����л�
			public void drawBoss(Graphics g){
				g.drawImage(bossImg, bx, by, null);
			}
	//�Զ���һ���������Boss�����˶�
	public void moveBoss(){
		if(f==0){
			bx++;
		}
		if(f==1){
			bx--;
		}
		if(bx+bossImg.getWidth(null)>=400){
			f=1;
		}
		if(bx<=0){
			f=0;
		}
	}
	
	
}
