package project_supermario_2;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Mushroom extends JLabel implements Moveable {

	private ImageIcon mushroom;
	private Player player;

	private MushroomObserver mushroomObserver;

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	private int mushroomX = 762;
	private int mushroomY = 278;

	public Mushroom(Player player) {
		this.player = player;
		initObject();
		initSetting();
		addEventListener();

	}

	private void initObject() {
		mushroom = new ImageIcon("images/superMushroom.png");
		mushroomObserver = new MushroomObserver(this);
	}

	private void initSetting() {
		left = false;
		up = false;
		down = false;

		setIcon(mushroom);
		setSize(30, 30);
		setLocation(mushroomX, mushroomY);

	}

	private void addEventListener() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (mushroomObserver.checkRightWall()) {
						mushroomX++;
					} else if (mushroomObserver.checkbottom()) {
						mushroomY++;
					}
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setLocation(mushroomX, mushroomY);
					
					if(mushroomObserver.checkRightWall()) {
//						left();
					}
				}

			}
		}).start();

	}

	@Override
	public void left() {
		left = true;
		for (int i = 0; i < 50; i++) {
			mushroomX--;
			setLocation(mushroomX, mushroomY);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void right() {
		right = true;
		for (int i = 1; i < 50; i++) {
			mushroomX++;
			setLocation(mushroomX, mushroomY);
			if (mushroomObserver.checkRightWall()) {
			}
		}
	}

	@Override
	public void down() {
		System.out.println("down");
		down = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (down) {
					mushroomY += 1;
					setLocation(mushroomX, mushroomY);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				down = false;
			}
		}).start();
	}

	private void threadSleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void up() {
		// TODO Auto-generated method stub

	}

}
