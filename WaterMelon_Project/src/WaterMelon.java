import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import java.awt.Color;

public class WaterMelon extends JFrame implements ActionListener {
	// System.getProperty("user.dir") <-- 현재프로젝트경로/src/Music...
	// (C:\C#\자바\WaterMelon_Project)
	String path[] = { ConstVariable.DIRECTORY + "/Music/Nilo.wav", ConstVariable.DIRECTORY + "/Music/ALI.wav",
			ConstVariable.DIRECTORY + "/Music/MC.wav", ConstVariable.DIRECTORY + "/Music/Kwill.wav",
			ConstVariable.DIRECTORY + "/Music/Park.wav", ConstVariable.DIRECTORY + "/Music/Winter.wav",
			ConstVariable.DIRECTORY + "/Music/Dandan.wav", };
	String musicInfo[] = { "지나오다 - 닐로", "지우개 - 알리", "어디에도 - 엠씨더맥스", "내 생에 아름다운 - 케이윌", "숨 - 박효신", "겨울을 걷는다 - 윤딴딴",
			"술이 웬수라서 - 윤딴딴" };
	String imgpath[] = { ConstVariable.DIRECTORY + "/Img/Nilo.jpg", ConstVariable.DIRECTORY + "/Img/ALI.jpg",
			ConstVariable.DIRECTORY + "/Img/MC.jpg", ConstVariable.DIRECTORY + "/Img/Kwill.jpg",
			ConstVariable.DIRECTORY + "/Img/Park.jpg", ConstVariable.DIRECTORY + "/Img/Winter.jpg",
			ConstVariable.DIRECTORY + "/Img/Dandan.jpg" };

	String userNickName = null;
	JLabel lblUserInfo;
	JButton btnStart;
	JButton btnP;
	JButton btnN;
	JLabel lblMusicInfo = new JLabel("Water Melon - Water Melon Project");

	JPanel PnMain;

	static LoginForm lf = null;

	int now = 1;
	File file = null;
	public AudioInputStream ais = null;
	Clip clip = null;
	long clipPosition = 0L;

	static DataBase db = new DataBase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		lf = new LoginForm();
	}

	/**
	 * Create the application.
	 */

	public WaterMelon(String userNickName) {
		super("Water Melon");
		getContentPane().setBackground(Color.WHITE);
		this.userNickName = userNickName;
		lf.setVisible(false);
		design();
		musicInfo(now);
		musicimg(now);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void musicimg(int now) {
		JPanel Pnimg = new JPanel();
		Pnimg.setBackground(Color.WHITE);
		Pnimg.setBounds(0, 79, 982, 312);
		Pnimg.setBorder(
				new TitledBorder(new TitledBorder("재생 목록"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));

		JPanel Pn1 = new JPanel();
		Pn1.setBackground(Color.WHITE);
		Pn1.setBounds(318, 15, 385, 243);
		ImageIcon ic = null;
		if (now == 0) {
			ic = new ImageIcon(imgpath[now]);
		} else if (now == 1) {
			ic = new ImageIcon(imgpath[now]);
		} else if (now == 2) {
			ic = new ImageIcon(imgpath[now]);
		} else if (now == 3) {
			ic = new ImageIcon(imgpath[now]);
		} else if (now == 4) {
			ic = new ImageIcon(imgpath[now]);
		} else if (now == 5) {
			ic = new ImageIcon(imgpath[now]);
		} else if (now == 6) {
			ic = new ImageIcon(imgpath[now]);
		}
		Image icg = ic.getImage();
		Image img = icg.getScaledInstance(400, 250, Image.SCALE_SMOOTH);
		ImageIcon Icon = new ImageIcon(img);
		JLabel imgLabel = new JLabel(Icon, JLabel.CENTER);

		Pn1.add(imgLabel);
		Pnimg.setLayout(null);

		Pnimg.add(Pn1);
		getContentPane().add(Pnimg);
	}

	private void musicInfo(int now) {
		getContentPane().setLayout(null);
		JPanel PnList = new JPanel();
		PnList.setBackground(Color.WHITE);
		PnList.setBorder(
				new TitledBorder(new TitledBorder("앨범 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		PnList.setBounds(0, 404, 982, 68);
		PnList.setLocation(0, 391);
		lblMusicInfo.setBounds(12, 28, 322, 18);
		// 여기잇던 라벨을
		if (now == 0) {
			lblMusicInfo.setText(musicInfo[now]);
		} else if (now == 1) {
			lblMusicInfo.setText(musicInfo[now]);
		} else if (now == 2) {
			lblMusicInfo.setText(musicInfo[now]);
		} else if (now == 3) {
			lblMusicInfo.setText(musicInfo[now]);
		} else if (now == 4) {
			lblMusicInfo.setText(musicInfo[now]);
		} else if (now == 5) {
			lblMusicInfo.setText(musicInfo[now]);
		} else if (now == 6) {
			lblMusicInfo.setText(musicInfo[now]);
		}

		PnList.setLayout(null);
		PnList.add(lblMusicInfo);

		getContentPane().add(PnList);

	}

	private void design() {
		// this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));// 전체
		getContentPane().setLayout(null);
		setResizable(false);

		JPanel PnTitle = new JPanel(new GridLayout(1, 1));
		PnTitle.setBackground(Color.WHITE);
		PnTitle.setBorder(
				new TitledBorder(new TitledBorder("회원 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		lblUserInfo = new JLabel("<" + userNickName + ">" + "님 환영합니다.");
		lblUserInfo.setBounds(809, 20, 161, 15);

		JLabel imgLabel = new JLabel(new ImageIcon("\\\\192.168.0.34\\webtest2(과제제출용)\\이효준\\Java 미니프로젝트\\WaterMelon_Project\\SubFile\\Img\\Logo.jpg"),
				SwingConstants.CENTER);

		JPanel cPn1 = new JPanel();
		cPn1.setBackground(Color.WHITE);
		cPn1.setLayout(null);
		cPn1.add(lblUserInfo);
		cPn1.add(imgLabel);
		PnTitle.setBounds(0, 0, 982, 81);
		PnTitle.add(cPn1);

		imgLabel.setBounds(370, 0, 250, 50);

		getContentPane().add(PnTitle);

		JPanel Pnbut = new JPanel();
		Pnbut.setBackground(Color.WHITE);
		Pnbut.setBounds(318, 340, 385, 38);

		// Pnbut.setLocation(0, 391);
		JPanel Pn2 = new JPanel();
		Pn2.setBackground(Color.WHITE);
		Pn2.setBounds(0, 0, 385, 38);

		btnP = new JButton("\uC774\uC804 \uACE1");
		btnP.setBounds(344, 274, 86, 46);
		btnP.setEnabled(false);
		Pn2.add(btnP);

		btnStart = new JButton("시작");
		btnStart.setLocation(442, 274);
		btnStart.setSize(111, 46);
		Pn2.add(btnStart);

		btnN = new JButton("\uB2E4\uC74C \uACE1");
		btnN.setBounds(565, 274, 86, 46);
		btnN.setEnabled(false);
		Pn2.add(btnN);
		// this.add(PnList);

		Pnbut.setLayout(null);
		Pnbut.add(Pn2);
		getContentPane().add(Pnbut);

		addListener();

		setBounds(0, 0, 1000, 490);// x,y,w,h
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void addListener() {
		btnStart.addActionListener(this);
		btnP.addActionListener(this);
		btnN.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			if (btnStart.getText().equals("시작")) { // 1 // 5
				btnStart.setText("일시정지");
				btnP.setEnabled(false);
				btnN.setEnabled(false);
				music(true, now);
				System.out.println("현재 곡 : " + musicInfo[now]);
				musicInfo(now);
			} else if (btnStart.getText().equals("일시정지")) { // 2 // 4
				btnStart.setText("시작");
				btnP.setEnabled(true);
				btnN.setEnabled(true);
				music(false, now);
				musicInfo(now);
				//
				System.out.println(musicInfo[now] + "곡이 중단되었습니다.");
			}
		} else if (e.getSource() == btnP) { // 3
			if (now < 1) {
				JOptionPane.showMessageDialog(this, "더이상 이전 곡이 없습니다.");
			} else {
				clipPosition = 0;
				// clip.setMicrosecondPosition(0);{
				btnStart.setText("일시정지");
				btnP.setEnabled(false);
				btnN.setEnabled(false);
				clip.stop();

				music(true, --now);

				System.out.println("현재 곡 : " + musicInfo[now]);
			}
			musicInfo(now);
			musicimg(now);
		} else if (e.getSource() == btnN) {
			if (now > 5) {
				JOptionPane.showMessageDialog(this, "더이상 다음 곡이 없습니다.");
			} else {
				clipPosition = 0;
				btnStart.setText("일시정지");
				btnP.setEnabled(false);
				btnN.setEnabled(false);
				// clip.setMicrosecondPosition(0);
				clip.stop();
				music(true, ++now);
				System.out.println("현재 곡 : " + musicInfo[now]);
			}
			musicInfo(now);
			musicimg(now);
		}
	}

	private void music(boolean value, int idx) {
		if (value == true) {
			try {
				file = new File(path[idx]);
				ais = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				if (file.exists()) {
					clip.open(ais);
					clip.setMicrosecondPosition(clipPosition);
					clip.start();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return;
		} else {

			clipPosition = clip.getMicrosecondPosition();
			clip.stop();

		}
	}
}