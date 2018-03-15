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
	//设置一个图片对象用于存放图片
	static Image startImage;
	//定义背景图坐标
	int x=0,y=0;
	//定义一个布尔类型判断单击事件
	boolean ck = true;
	//定义线程
	Thread t;
	////定义一个布尔类型用于游戏的回复和暂停
	boolean suspend = false;
	//定义一个英雄机的图片和坐标
	static Image p[] = new Image[2];
	int px=100,py=100;
	//定义数组的下标标识
	int pc = 0;
	
	
	//定义一个集合用来管理炮弹
	List<Bullet> bullets = new ArrayList<Bullet>();
	//创建一个炮弹类对象
	Bullet bullet;
	//创建一个英雄机炮弹图片对象
	static Image bImg;
	//定义一个整型变量用于标识操作
	int count = 0;
	
	//定义一个集合用来管理敌机炮弹
	List<ElanBullet> ebullets = new ArrayList<ElanBullet>();
	//创建一个敌机炮弹的图片
	static Image ebImg;
	//创建一个敌机炮弹类对象
	ElanBullet ebullet;
	
	//定义一个集合来管理敌机
	List<Elan> elans = new ArrayList<Elan>();
	//创建一个敌机类对象
	Elan elan;
	//创建敌机和子弹的对比类
	BulletAndElan bae;
	//声明得分
	int score=0;
	//给英雄飞机定义一个生命值
	int php=200;
	//创建敌机和英雄飞机的碰撞的类
	PlanAndElan pae;
	
	//创建敌机子弹和英雄飞机的碰撞的类
	EbulletAndPlane ebap;
	
	//创建Boss机子弹和英雄飞机的碰撞类
	BbulletAndPlane bbap;
	
	//定义一个Boos飞机坐标
//	int bbx=(int)(Math.random()*300),bby=50;
	//创建一个Boss机图片对象
	static Image bossImg = new ImageIcon("images/BossPlane/plane_5.png").getImage();
	//创建Boos飞机
	BossElan be = new BossElan(bossImg,200,50,500,1,50);
	
	
	//标识Boss机是否存在
	boolean beExist = false;
	
	//创建一个Boss炮弹的图片
	static Image bbImg;
	//定义一个集合用来管理Boos炮弹
	List<BossBullet> bbullets = new ArrayList<BossBullet>();
	//创建一个Boss敌机炮弹类对象
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
	
	//构造方法完成初始化
	public ShootJPanel(){
		addMouseListener(this);
		addMouseMotionListener(this);
		t = new Thread(this);
	}
	
	//画的方法
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//开始画图
		g.drawImage(startImage, x, y, null);
		
		//画英雄机
		if(ck==false){
			pc = pc==0?1:0;
			g.drawImage(p[pc], px, py, null);
		}
		
		//通过for循环画炮弹集合中的所有的炮弹
		for (int i = 0; i < bullets.size(); i++) {
			//英雄机的炮弹
			bullet = bullets.get(i);
			bullet.drawBullet(g);
		}
		
		//通过for循环画敌机炮弹集合中的所有的炮弹
		for (int i = 0; i < ebullets.size(); i++) {
			ebullet = ebullets.get(i);
			ebullet.drawBullet(g);
		}
		
		//通过for循环画Boos机炮弹集合中的所有的炮弹
		for(int i = 0;i<bbullets.size();i++){
			bbullet = bbullets.get(i);
			bbullet.drawBullet(g);
		}
		
		//通过for循环画所有的敌机
		for (int i = 0; i < elans.size(); i++) {
			elan = elans.get(i);
			elan.drawElan(g);             
		}
		
		//画boss飞机
		if(beExist){
			be.drawBoss(g);
		}
		
		//画得分
		if(ck==false){
			g.setColor(Color.RED);
			g.drawString("SCORE:"+score, 300, 50);
			g.drawString("PHP:"+php, 300, 80);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标左键
		if(e.getModifiers()==e.BUTTON1_MASK&&ck&&e.getX()>=130&&e.getX()<=260&&e.getY()>=390&&e.getY()<=434){
			//更改ck的值
			ck = false;
			//定义游戏主界面坐标
			y = -5400;
			try {
				startImage = ImageIO.read(new File("images/background/background_1.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			t.start();
		}
		//鼠标右键
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
			//设置鼠标的图片为小手
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}else if(ck==false){
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}else{
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		//获得鼠标的坐标给英雄机坐标赋值
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
			    
				////通过for循环完成所有炮弹的移动
				for (int i = 0; i < bullets.size(); i++) {
					bullet = bullets.get(i);
					bullet.moveBullet();
				}
				////通过for循环完成所有敌机炮弹的移动
				for (int i = 0; i < ebullets.size(); i++) {
					ebullet = ebullets.get(i);
					ebullet.moveElanBullet();
				}
				
				if(beExist){
					if(count%40==0){
						//创建BOOS飞机的炮弹
						bbullet = new BossBullet(bbImg,be.bx+bossImg.getWidth(null)/2-bbImg.getWidth(null)/2, 150);
						//将炮弹放入集合中
						bbullets.add(bbullet);
					}
				}
				//通过for循环完成所有boss机炮弹的移动
				for(int i = 0; i < bbullets.size();i++){
					bbullet = bbullets.get(i);
					bbullet.moveElanBullet();
				}
				
				if(count%40==0){
					//创建英雄飞机的炮弹
					bullet = new Bullet(bImg, px+p[pc].getWidth(null)/2-bImg.getWidth(null)/2, py);
					//将炮弹放入集合中
					bullets.add(bullet);
				}
				
				
				
				//背景图移动
				if(y<=-4){
					y+=4;
				}
                //创建boss飞机
				if (y == -4) {
					beExist = true;
				}
				if(beExist){ 
					be.moveBoss();
				}
				
			    ////通过for循环完成所有敌机的移动
				for (int i = 0; i < elans.size(); i++) {
					elan = elans.get(i);
					elan.moveElan();
					if(count%40==0){
					//创建敌机的炮弹
					ebullet = new ElanBullet(ebImg, elan.ex+elan.eImg.getWidth(null)/2-ebImg.getWidth(null)/2, elan.ey+elan.eImg.getHeight(null)-5 );
					//将敌机炮弹放入敌机的炮弹集合中
					ebullets.add(ebullet);
					}

					
					//判断敌机是否碰撞英雄飞机
					pae = new PlanAndElan();
					boolean f = pae.planAndElanCollision(px, py, p[pc], elan);
					if(f){
						php-=elan.hp;
						elan.exist=false;
					}

				}
				
				if(count%60==0){
					//创建敌机
					int num = (int)(Math.random()*5+2);
					elan = new Elan(new ImageIcon("images/LittlePlane/plane"+num+".png").getImage(), (int)(Math.random()*350), -50, 2, 2, 2);
					//将敌机放入集合中
					elans.add(elan);
					
				}
				

				
				//通过双重for循环完成炮弹对敌机的碰撞判断
				for (int i = 0; i < bullets.size(); i++) {
					//获取当前炮弹
					bullet = bullets.get(i);
					for (int j = 0; j < elans.size(); j++) {
						//获取当前敌机
						elan = elans.get(j);
						//实例化对象并调用碰撞方法
						bae = new BulletAndElan();
						bae.bulletAndElanCollision(bullet, elan);
					}
				}
				
				//清除炮弹集合中所有炮弹生存值为false的炮弹
				for (int i = 0; i < bullets.size(); i++) {
					bullet = bullets.get(i);
					if(bullet.exist==false){
						bullets.remove(i);
						break;
					}
				}
				
				//清除敌机集合中所有敌机生存值为false的敌机
				for (int i = 0; i < elans.size(); i++) {
					elan = elans.get(i);
					if(elan.exist==false&&elan.time<=0&&elan.hp<=0){
						score += elan.score;   //将当前敌机的分数给记分板
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
				//for循环判断敌机子弹是否与英雄飞机碰撞
				for (int i = 0; i < ebullets.size(); i++) {
					ebullet = ebullets.get(i);
					ebap = new EbulletAndPlane();
					boolean f2 = ebap.EbulletAndPlaneCollision(px, py, p[pc], ebullet);
					if(f2){
					php--;
					ebullet.exist = false;
					}
				}
				//for循环判断Boss机子弹是否与英雄飞机碰撞
				for (int i = 0; i < bbullets.size(); i++) {
					bbullet = bbullets.get(i);
					bbap = new BbulletAndPlane();
					boolean f3 = bbap.BbulletAndPlaneCollision(px, py, p[pc], bbullet);
					if(f3){
						php--;
						bbullet.exist = false;
					}
				}
				
				
				//清除Boos机子弹中所有子弹标识为false的子弹
				for (int i = 0; i < bbullets.size(); i++) {
					bbullet = bbullets.get(i);
					if(bbullet.exist==false){
						bbullets.remove(i);
						break;
					}
				}
				
				//清除敌机子弹中所有子弹标识为false的子弹
				for (int i = 0; i < ebullets.size(); i++) {
					ebullet = ebullets.get(i);
					if(ebullet.exist==false){
						ebullets.remove(i);
						break;
					}
				}
				
				//英雄飞机的处理
				if(php<=0){
					//更换背景图片
					startImage = new ImageIcon("images/GameInterface/jeimian_2.png").getImage();
					x=0;y=0;
					p[pc] = new ImageIcon("images/blast/blast_2.png").getImage();
					p[0]=p[1]=p[pc];
					//游戏结束画面出现后把子弹和敌机去掉
					bullets.removeAll(bullets);
					elans.removeAll(elans);
					ebullets.removeAll(ebullets);
					bbullets.removeAll(bbullets);
					repaint();
					t.stop();
				}
				
				//当boos飞机出来了 其他的都消失
				if(beExist){
//					bullets.removeAll(bullets);
					elans.removeAll(elans);
					ebullets.removeAll(ebullets);
					
				}
				
							
				
				//背景图移动
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

	//自定义带返回值的暂停方法
	public boolean suspend(){
		suspend = true;
		return suspend;
	}
	//自定义带返回值的回复方法
	public synchronized boolean resume(){
		suspend = false;
		notify();    //唤醒正在等待的休眠
		return suspend;
	}
}
