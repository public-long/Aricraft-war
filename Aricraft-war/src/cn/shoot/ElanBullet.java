package cn.shoot;

import java.awt.Graphics;
import java.awt.Image;

public class ElanBullet {
	//����ͼƬ�Ķ���
		Image ebImg;
		//�����ڵ�������
		int ebx ; int eby;
		//����һ�������ͱ������ڱ�ʾ�ڵ��Ƿ����
		boolean exist = true;
		//���췽����ɳ�ʼ��
		public ElanBullet(Image ebImg, int ebx, int eby) {
			super();
			this.ebImg = ebImg;
			this.ebx = ebx;
			this.eby = eby;
		}
		
		//�Զ���һ���������ڻ��ڵ�
		public void drawBullet(Graphics g){
			g.drawImage(ebImg, ebx, eby, null);
		}
		

		//�Զ���һ���������ڵл����ڵ����ƶ�
		public void moveElanBullet(){
			eby+=3;
			if(eby==600+ebImg.getWidth(null)){
				exist = false;
			}
		}
}
