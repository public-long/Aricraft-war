	package cn.shoot;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ShootJPanel extends JPanel implements MouseMotionListener,MouseListener,Runnable{
	//����һ��ͼƬ�������ڴ��ͼƬ
	static Image startImage;
	//���屳��ͼ����
	int x=0,y=0;
	//����һ�����������жϵ����¼�
	boolean ck = true;
	//�����߳�
	Thread t;
	////����һ����������������Ϸ�Ļظ�����ͣ
	boolean suspend = false;
	//����һ��Ӣ�ۻ���ͼƬ������
	static Image p[] = new Image[2];
	int px=100,py=100;
	//����������±��ʶ
	int pc = 0;
	
	
	//����һ���������������ڵ�
	List<Bullet> bullets = new ArrayList<Bullet>();
	//����һ���ڵ������
	Bullet bullet;
	//����һ��Ӣ�ۻ��ڵ�ͼƬ����
	static Image bImg;
	//����һ�����ͱ������ڱ�ʶ����
	int count = 0;
	
	//����һ��������������л��ڵ�
	List<ElanBullet> ebullets = new ArrayList<ElanBullet>();
	//����һ���л��ڵ���ͼƬ
	static Image ebImg;
	//����һ���л��ڵ������
	ElanBullet ebullet;
	
	//����һ������������л�
	List<Elan> elans = new ArrayList<Elan>();
	//����һ���л������
	Elan elan;
	//�����л����ӵ��ĶԱ���
	BulletAndElan bae;
	//�����÷�
	int score=0;
	//��Ӣ�۷ɻ�����һ������ֵ
	int php=200;
	//�����л���Ӣ�۷ɻ�����ײ����
	PlanAndElan pae;
	
	//�����л��ӵ���Ӣ�۷ɻ�����ײ����
	EbulletAndPlane ebap;
	
	//����Boss���ӵ���Ӣ�۷ɻ�����ײ��
	BbulletAndPlane bbap;
	
	//����һ��Boos�ɻ�����
//	int bbx=(int)(Math.random()*300),bby=50;
	//����һ��Boss��ͼƬ����
	static Image bossImg = new ImageIcon("images/BossPlane/plane_5.png").getImage();
	//����Boos�ɻ�
	BossElan be = new BossElan(bossImg,200,50,500,1,50);
	
	
	//��ʶBoss���Ƿ����
	boolean beExist = false;
	
	//����һ��Boss�ڵ���ͼƬ
	static Image bbImg;
	//����һ��������������Boos�ڵ�
	List<BossBullet> bbullets = new ArrayList<BossBullet>();
	//����һ��Boss�л��ڵ������
	BossBullet bbullet;
	
	
	
	static{
		try {
			startImage = ImageIO.read(new File("images/GameInterface/interface_1.png"));
			p[0] = ImageIO.read(new File("images/1.png"));
			p[1] = ImageIO.read(new File("images/2.png"));
			bImg = ImageIO.read(new File("images/bullet/bullet_1.png"));
			ebImg = ImageIO.read(new File("images/bullet/bullet_7.png"));
			bbImg = ImageIO.read(new File("images/bullet/bullet_5.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//���췽����ɳ�ʼ��
	public ShootJPanel(){
		addMouseListener(this);
		addMouseMotionListener(this);
		t = new Thread(this);
	}
	
	//���ķ���
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//��ʼ��ͼ
		g.drawImage(startImage, x, y, null);
		
		//��Ӣ�ۻ�
		if(ck==false){
			pc = pc==0?1:0;
			g.drawImage(p[pc], px, py, null);
		}
		
		//ͨ��forѭ�����ڵ������е����е��ڵ�
		for (int i = 0; i < bullets.size(); i++) {
			//Ӣ�ۻ����ڵ�
			bullet = bullets.get(i);
			bullet.drawBullet(g);
		}
		
		//ͨ��forѭ�����л��ڵ������е����е��ڵ�
		for (int i = 0; i < ebullets.size(); i++) {
			ebullet = ebullets.get(i);
			ebullet.drawBullet(g);
		}
		
		//ͨ��forѭ����Boos���ڵ������е����е��ڵ�
		for(int i = 0;i<bbullets.size();i++){
			bbullet = bbullets.get(i);
			bbullet.drawBullet(g);
		}
		
		//ͨ��forѭ�������еĵл�
		for (int i = 0; i < elans.size(); i++) {
			elan = elans.get(i);
			elan.drawElan(g);             
		}
		
		//��boss�ɻ�
		if(beExist){
			be.drawBoss(g);
		}
		
		//���÷�
		if(ck==false){
			g.setColor(Color.RED);
			g.drawString("SCORE:"+score, 300, 50);
			g.drawString("PHP:"+php, 300, 80);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//������
		if(e.getModifiers()==e.BUTTON1_MASK&&ck&&e.getX()>=130&&e.getX()<=260&&e.getY()>=390&&e.getY()<=434){
			//����ck��ֵ
			ck = false;
			//������Ϸ����������
			y = -5400;
			try {
				startImage = ImageIO.read(new File("images/background/background_1.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			t.start();
		}
		//����Ҽ�
		if(e.getModifiers()==e.BUTTON3_MASK){
			suspend = suspend?resume():suspend();
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e.getX()+"=="+e.getY());
//		129==390
//		263==392
//		128==432
//		262==433

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(ck&&e.getX()>=130&&e.getX()<=260&&e.getY()>=390&&e.getY()<=434){
			//��������ͼƬΪС��
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}else if(ck==false){
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}else{
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		//������������Ӣ�ۻ����긳ֵ
		px = e.getX()-p[pc].getWidth(null)/2;
		py = e.getY()-p[pc].getHeight(null)/2;
		
//		repaint();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (this) {
			while(true){
				count++;
				if(count==1000) count=0;
				if(suspend){
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			    
				////ͨ��forѭ����������ڵ����ƶ�
				for (int i = 0; i < bullets.size(); i++) {
					bullet = bullets.get(i);
					bullet.moveBullet();
				}
				////ͨ��forѭ��������ел��ڵ����ƶ�
				for (int i = 0; i < ebullets.size(); i++) {
					ebullet = ebullets.get(i);
					ebullet.moveElanBullet();
				}
				
				if(beExist){
					if(count%40==0){
						//����BOOS�ɻ����ڵ�
						bbullet = new BossBullet(bbImg,be.bx+bossImg.getWidth(null)/2-bbImg.getWidth(null)/2, 150);
						//���ڵ����뼯����
						bbullets.add(bbullet);
					}
				}
				//ͨ��forѭ���������boss���ڵ����ƶ�
				for(int i = 0; i < bbullets.size();i++){
					bbullet = bbullets.get(i);
					bbullet.moveElanBullet();
				}
				
				if(count%40==0){
					//����Ӣ�۷ɻ����ڵ�
					bullet = new Bullet(bImg, px+p[pc].getWidth(null)/2-bImg.getWidth(null)/2, py);
					//���ڵ����뼯����
					bullets.add(bullet);
				}
				
				
				
				//����ͼ�ƶ�
				if(y<=-4){
					y+=4;
				}
                //����boss�ɻ�
				if (y == -4) {
					beExist = true;
				}
				if(beExist){ 
					be.moveBoss();
				}
				
			    ////ͨ��forѭ��������ел����ƶ�
				for (int i = 0; i < elans.size(); i++) {
					elan = elans.get(i);
					elan.moveElan();
					if(count%40==0){
					//�����л����ڵ�
					ebullet = new ElanBullet(ebImg, elan.ex+elan.eImg.getWidth(null)/2-ebImg.getWidth(null)/2, elan.ey+elan.eImg.getHeight(null)-5 );
					//���л��ڵ�����л����ڵ�������
					ebullets.add(ebullet);
					}

					
					//�жϵл��Ƿ���ײӢ�۷ɻ�
					pae = new PlanAndElan();
					boolean f = pae.planAndElanCollision(px, py, p[pc], elan);
					if(f){
						php-=elan.hp;
						elan.exist=false;
					}

				}
				
				if(count%60==0){
					//�����л�
					int num = (int)(Math.random()*5+2);
					elan = new Elan(new ImageIcon("images/LittlePlane/plane"+num+".png").getImage(), (int)(Math.random()*350), -50, 2, 2, 2);
					//���л����뼯����
					elans.add(elan);
					
				}
				

				
				//ͨ��˫��forѭ������ڵ��Եл�����ײ�ж�
				for (int i = 0; i < bullets.size(); i++) {
					//��ȡ��ǰ�ڵ�
					bullet = bullets.get(i);
					for (int j = 0; j < elans.size(); j++) {
						//��ȡ��ǰ�л�
						elan = elans.get(j);
						//ʵ�������󲢵�����ײ����
						bae = new BulletAndElan();
						bae.bulletAndElanCollision(bullet, elan);
					}
				}
				
				//����ڵ������������ڵ�����ֵΪfalse���ڵ�
				for (int i = 0; i < bullets.size(); i++) {
					bullet = bullets.get(i);
					if(bullet.exist==false){
						bullets.remove(i);
						break;
					}
				}
				
				//����л����������ел�����ֵΪfalse�ĵл�
				for (int i = 0; i < elans.size(); i++) {
					elan = elans.get(i);
					if(elan.exist==false&&elan.time<=0&&elan.hp<=0){
						score += elan.score;   //����ǰ�л��ķ������Ƿְ�
						elans.remove(i);
					}
					else if(elan.exist==false){
						elan.time--;
						elan.eImg = new ImageIcon("images/blast/blast_1.png").getImage();
					}
					if(elan.exist==false&&elan.hp!=0){
						elan.hp--;
					}
				}
				//forѭ���жϵл��ӵ��Ƿ���Ӣ�۷ɻ���ײ
				for (int i = 0; i < ebullets.size(); i++) {
					ebullet = ebullets.get(i);
					ebap = new EbulletAndPlane();
					boolean f2 = ebap.EbulletAndPlaneCollision(px, py, p[pc], ebullet);
					if(f2){
					php--;
					ebullet.exist = false;
					}
				}
				//forѭ���ж�Boss���ӵ��Ƿ���Ӣ�۷ɻ���ײ
				for (int i = 0; i < bbullets.size(); i++) {
					bbullet = bbullets.get(i);
					bbap = new BbulletAndPlane();
					boolean f3 = bbap.BbulletAndPlaneCollision(px, py, p[pc], bbullet);
					if(f3){
						php--;
						bbullet.exist = false;
					}
				}
				
				
				//���Boos���ӵ��������ӵ���ʶΪfalse���ӵ�
				for (int i = 0; i < bbullets.size(); i++) {
					bbullet = bbullets.get(i);
					if(bbullet.exist==false){
						bbullets.remove(i);
						break;
					}
				}
				
				//����л��ӵ��������ӵ���ʶΪfalse���ӵ�
				for (int i = 0; i < ebullets.size(); i++) {
					ebullet = ebullets.get(i);
					if(ebullet.exist==false){
						ebullets.remove(i);
						break;
					}
				}
				
				//Ӣ�۷ɻ��Ĵ���
				if(php<=0){
					//��������ͼƬ
					startImage = new ImageIcon("images/GameInterface/jeimian_2.png").getImage();
					x=0;y=0;
					p[pc] = new ImageIcon("images/blast/blast_2.png").getImage();
					p[0]=p[1]=p[pc];
					//��Ϸ����������ֺ���ӵ��͵л�ȥ��
					bullets.removeAll(bullets);
					elans.removeAll(elans);
					ebullets.removeAll(ebullets);
					bbullets.removeAll(bbullets);
					repaint();
					t.stop();
				}
				
				//��boos�ɻ������� �����Ķ���ʧ
				if(beExist){
//					bullets.removeAll(bullets);
					elans.removeAll(elans);
					ebullets.removeAll(ebullets);
					
				}
				
							
				
				//����ͼ�ƶ�
				repaint();
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	//�Զ��������ֵ����ͣ����
	public boolean suspend(){
		suspend = true;
		return suspend;
	}
	//�Զ��������ֵ�Ļظ�����
	public synchronized boolean resume(){
		suspend = false;
		notify();    //�������ڵȴ�������
		return suspend;
	}
}
