package cn.shoot;

public class BulletAndElan {
	//����һ�����������ж��ӵ��͵л�����ײ
	public void bulletAndElanCollision(Bullet b1 , Elan e1){
		//������Զ���������
		int b1x = b1.bx;
		int b1y = b1.by;
		int b2x = b1.bx+b1.bImg.getWidth(null);
		int b3y = b1.by+b1.bImg.getHeight(null);
		int e1x = e1.ex;
		int e1y = e1.ey;
		int e2x = e1.ex+e1.eImg.getWidth(null);
		int e3y = e1.ey+e1.eImg.getHeight(null);
		if(b1x>=e1x&&b1y>=e1y&&b2x<=e2x&&b3y<=e3y){
			//���ӵ��͵л���ײ�����ж�
			b1.exist = false;
			e1.exist = false;
		}
	}
}
