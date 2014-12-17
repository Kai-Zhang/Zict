/*
 * TODO list:
 * + Click like counter or another visible sign  -->  UI design or another variable
 * + Register check  -->  use regex in register
 */

package client;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import data.Explanation;
import data.UserInfo;
import data.WordEntry;
import logic.ServiceProvider;
import network.Network;

@SuppressWarnings("serial")
public class UImain extends JFrame{
	public static UImain mainFrame = null;
	private JTextArea usernameArea=new JTextArea();
    private String[] ListWord =new String[100];  //联想表
	private JList jlist = new JList(ListWord);
	private JTextArea[] txOut = {new JTextArea(5,50), new JTextArea(5,50), new JTextArea(5,50)};

	private JTextField txInput = new JTextField("请输入英文", 42);
	private JTextField idInput=new JTextField(16);
	private JPasswordField keyInput=new JPasswordField(16);
	private JTextField regIdInput=new JTextField(16);
	private JPasswordField regKeyInput1=new JPasswordField(16);
	private JPasswordField regKeyInput2=new JPasswordField(16);
    
	private JLabel welcome=new JLabel(new ImageIcon("images/welcome.png"));
	private JTextArea welcomeUser = new JTextArea();
	
	private ImageIcon searchIcon=new ImageIcon("images/search.png");
	private ImageIcon loginIcon=new ImageIcon("images/login2.png");
	private ImageIcon registerIcon=new ImageIcon("images/register2.png");
	private ImageIcon loginPageLoginOkIcon=new ImageIcon("images/login2.png");
	private ImageIcon exitIcon=new ImageIcon("images/quxiao.png");
	private ImageIcon regPageRegOkIcon=new ImageIcon("images/register2.png");
	private ImageIcon likeIcon=new ImageIcon("images/button_01.png");
	private ImageIcon homeIcon=new ImageIcon("images/button_02.png");
	private ImageIcon userIcon=new ImageIcon("images/button_03.png");
	private ImageIcon likeIconM=new ImageIcon("images/buttonmark_01.png");
	private ImageIcon homeIconM=new ImageIcon("images/buttonmark_02.png");
	private ImageIcon userIconM=new ImageIcon("images/buttonmark_03.png");
	private ImageIcon friendIcon=new ImageIcon("images/friend.png");
	private ImageIcon likelogoIcon=new ImageIcon("images/likelogo.png");
	private ImageIcon shareIcon=new ImageIcon("images/share.png");
	private ImageIcon likeagreeIcon=new ImageIcon("images/likeagree.png");
	private JLabel homeMarkJLabel=new JLabel(homeIconM);
	private JLabel userMarkJLabel=new JLabel(userIconM);
	private JLabel likeMarkJLabel=new JLabel(likeIconM);

	private JButton friend= new JButton(friendIcon);

	private JButton search = new JButton(searchIcon);
    private JButton login=new JButton(loginIcon);
    private JButton register=new JButton(registerIcon);
    private JButton loginPageLoginOk= new JButton(loginPageLoginOkIcon);
    private JButton regExit= new JButton(exitIcon);
    private JButton loginExit= new JButton(exitIcon);
    private JButton regPageRegOk= new JButton(regPageRegOkIcon);
    private JButton homeButton=new JButton(homeIcon);
    private JButton userButton=new JButton(userIcon);
    private JButton likeButton=new JButton(likeIcon);
    
    private JButton share1=new JButton(shareIcon);
    private JButton share2=new JButton(shareIcon);
    private JButton share3=new JButton(shareIcon);
    
    private JButton like1=new JButton(likelogoIcon);
    private JButton like2=new JButton(likelogoIcon);
    private JButton like3=new JButton(likelogoIcon);
    private JLabel likeMark=new JLabel(likeagreeIcon);
    private JCheckBox youdao =new JCheckBox("有道",true);
    private JCheckBox baidu =new JCheckBox("百度",true);
    private JCheckBox bing =new JCheckBox("必应",true);
    
    final JFrame regFrame=new JFrame("Register");
    final JFrame loginFrame=new JFrame("Login");

	public UImain() throws Exception {
		mainPage();
    	login.setBorder(new EmptyBorder(0,0,0,0));
    	register.setBorder(new EmptyBorder(0,0,0,0));
    	loginPageLoginOk.setBorder(new EmptyBorder(0,0,0,0));
    	search.setBorder(new EmptyBorder(0,0,0,0));
    	regExit.setBorder(new EmptyBorder(0,0,0,0));
    	loginExit.setBorder(new EmptyBorder(0,0,0,0));
    	regPageRegOk.setBorder(new EmptyBorder(0,0,0,0));
    	homeButton.setBorder(new EmptyBorder(0,0,0,0));
    	userButton.setBorder(new EmptyBorder(0,0,0,0));
    	likeButton.setBorder(new EmptyBorder(0,0,0,0));

		login.addActionListener(new ActionListener() {					
			@Override
			public void actionPerformed(ActionEvent e) {
				loginPage();
			}
		});

		loginPageLoginOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userID = idInput.getText();
				String password = new String(keyInput.getPassword());
				if (userID.equals("")) {
					JOptionPane.showMessageDialog(null, "用户名不能为空!");
				} else if (password.equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空!");
				} else {
					UserInfo.login(userID, password);
	            }
			}
		});

		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				regPage();	 
			}
		});

		regExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regFrame.dispose();
			}
		});

		loginExit.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 loginFrame.dispose();
	    	 }
	     });

		regPageRegOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
            	String userID = regIdInput.getText();
				String password = new String(regKeyInput1.getPassword());
				String passwordConfirm = new String(regKeyInput2.getPassword());

				if (userID.equals("")) {
					JOptionPane.showMessageDialog(null, "用户名不能为空!");
				} else if (password.equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空!");
				} else if (passwordConfirm.equals("")) {
					JOptionPane.showMessageDialog(null, "请再次输入密码");
				} else if (!password.equals(passwordConfirm)) {
					JOptionPane.showMessageDialog(null, "两次密码不一致！");
				} else if (!userID.matches("\\w{1,16}")) {
					JOptionPane.showMessageDialog(null, "用户名应为16位以下数字或字母");
				} else if (!password.matches("\\w{6,}")) {
					JOptionPane.showMessageDialog(null, "请输入6位以上的密码");
				} else {
					UserInfo.register(userID, password);
				}
			}
		});

		like1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServiceProvider.clickLike(WordEntry.getWord(), WordEntry.getExplanation(0).getSource());
			}
		});

		like2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServiceProvider.clickLike(WordEntry.getWord(), WordEntry.getExplanation(1).getSource());
			}
		});

		like3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServiceProvider.clickLike(WordEntry.getWord(), WordEntry.getExplanation(2).getSource());
			}
		});

		share1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	             new CheckOnlineList(0);
			}
		});

		share2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CheckOnlineList(1);
			}
		});

		share3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CheckOnlineList(2);
			}
		});

		search.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				homePage();
				txOut[0].setText("正在服务器上查找...");
				txOut[1].setText("正在服务器上查找...");
				txOut[2].setText("正在服务器上查找...");
				String currentWord = txInput.getText();
				ServiceProvider.getExplanation(currentWord);
				if (WordEntry.getExplanation(0) == null) {
					txOut[0].setText("");
					txOut[1].setText("");
					txOut[2].setText("");
					return;
				}
				ArrayList<Explanation> outputList = new ArrayList<Explanation>();
				for (int i = 0; i < 3; i ++) {
					String source = WordEntry.getExplanation(i).getSource();
					if (source.equals("baidu")) {
						if (baidu.isSelected()) {
							outputList.add(WordEntry.getExplanation(i));
						}
					}
					else if (source.equals("bing")) {
						if (bing.isSelected()) {
							outputList.add(WordEntry.getExplanation(i));
						}
					}
					else {
						if (youdao.isSelected()) {
							outputList.add(WordEntry.getExplanation(i));
						}
					}
				}
				for (int i = 0; i < outputList.size(); i ++) {
					txOut[i].setText(outputList.get(i).getExplanation());
				}
			}
		});

        homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				homePage();
			}
		});

        userButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(UserInfo.isLogged())
					userPage1();
				else {
					loginPageNew();
				}
			}
		});

		likeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(UserInfo.isLogged())
					likePage();
				else {
					loginPageNew();
				}
			}
		});

		friend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				userPage2();
			}
		});

		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) { }
			
			@Override
			public void windowIconified(WindowEvent e) { }
			
			@Override
			public void windowDeiconified(WindowEvent e) { }
			
			@Override
			public void windowDeactivated(WindowEvent e) { }
			
			@Override
			public void windowClosing(WindowEvent e) {
				Network.sendToServer("Bye!");
			}
			
			@Override
			public void windowClosed(WindowEvent e) { }
			
			@Override
			public void windowActivated(WindowEvent e) { }
		});
	}

	private class mainPanel extends JPanel {
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			super.paintComponent(g);
			Image img = Toolkit.getDefaultToolkit().getImage("images/bg2.png");
			g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
       
	private class loginPanel extends JPanel {
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			super.paintComponent(g);
			Image img = Toolkit.getDefaultToolkit().getImage("images/loginbg.jpg");
			g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	private class regPanel extends JPanel {
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			super.paintComponent(g);
			Image img = Toolkit.getDefaultToolkit().getImage("images/registebg.jpg");
			g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	private class homePanel extends JPanel {
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			super.paintComponent(g);
			Image img = Toolkit.getDefaultToolkit().getImage("images/homebg.png");
			g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    }
	}

	private class userPanel1 extends JPanel {
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			super.paintComponent(g);
			Image img = Toolkit.getDefaultToolkit().getImage("images/userbg1.png");
			g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	private class userPanel2 extends JPanel {
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			super.paintComponent(g);
			Image img = Toolkit.getDefaultToolkit().getImage("images/userbg2.png");
			g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	private class likePanel extends JPanel {
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			super.paintComponent(g);
			Image img = Toolkit.getDefaultToolkit().getImage("images/likebg.png");
			g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	private void loginPage() {
		loginFrame.setContentPane(new loginPanel());
		loginFrame.setResizable(false);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		loginFrame.setLocation( (scr.width - frm.width) / 2,
		            (scr.height - frm.height) / 2 - 18);

		//loginFrame.setLayout(null);
		//loginFrame.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
		loginFrame.setLayout(null);
		loginFrame.add(idInput).setBounds(58, 74, 155, 28);
		idInput.setBorder(new EmptyBorder(0,0,0,0));
		loginFrame.add(keyInput).setBounds(58, 111, 155, 28);
		keyInput.setBorder(new EmptyBorder(0,0,0,0));
		loginFrame.add(loginPageLoginOk).setBounds(28, 149, 85, 32);
		loginFrame.add(loginExit).setBounds(129, 149, 85, 32);

		loginFrame.setSize(250,220);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loginFrame.setVisible(true);
	}

	private void loginPageNew() {
		loginFrame.setContentPane(new loginPanel());
		loginFrame.setResizable(false);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		loginFrame.setLocation( (scr.width - frm.width) / 2,
		            (scr.height - frm.height) / 2 - 18);

		//loginFrame.setLayout(null);
		//loginFrame.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
		loginFrame.setLayout(null);
		loginFrame.add(idInput).setBounds(58, 74, 155, 28);
		idInput.setBorder(new EmptyBorder(0,0,0,0));
		loginFrame.add(keyInput).setBounds(58, 111, 155, 28);
		keyInput.setBorder(new EmptyBorder(0,0,0,0));
		loginFrame.add(loginPageLoginOk).setBounds(28, 149, 85, 32);
		loginFrame.add(register).setBounds(129, 149, 85, 32);

		loginFrame.setSize(250,220);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loginFrame.setVisible(true);
	}

	private void regPage() {
		regFrame.setContentPane(new regPanel());
		regFrame.setResizable(false);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		regFrame.setLocation( (scr.width - frm.width) / 2,
		            (scr.height - frm.height) / 2 - 18);

		//loginFrame.setLayout(null);
		//loginFrame.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
		regFrame.setLayout(null);
		regFrame.add(regIdInput).setBounds(58, 110, 155, 28);
		regIdInput.setBorder(new EmptyBorder(0,0,0,0));
		regFrame.add(regKeyInput1).setBounds(58, 150, 155, 28);
		regKeyInput1.setBorder(new EmptyBorder(0,0,0,0));
		regFrame.add(regKeyInput2).setBounds(58, 186, 155, 28);
		regKeyInput2.setBorder(new EmptyBorder(0,0,0,0));
		regFrame.add(regPageRegOk).setBounds(27, 223, 85, 32);
		regFrame.add(regExit).setBounds(127, 223, 85, 32);

		regFrame.setSize(250,300);
		regFrame.setLocationRelativeTo(null);
		regFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		regFrame.setVisible(true);
	}

	private void mainPage() {
		setContentPane(new mainPanel());
		setTitle("字字字字典Zict");
		setLayout(null);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		setLocation( (scr.width - frm.width) / 2,
		            (scr.height - frm.height) / 2 - 18);

		txInput.setBounds(272, 350, 210, 35);
		txInput.setBorder(new EmptyBorder(0,0,0,0));
		txInput.setFont(new Font("微软雅黑",Font.BOLD,16));
		search.setBounds(498,345,86,45);
		youdao.setBounds(232, 408, 15,15);
		baidu.setBounds(360,408,15,15);
		bing.setBounds(490,408,15,15);

		add(txInput);
		add(search);
		add(youdao);
		add(baidu);
		add(bing);
		flushUserState();
		setVisible(true);
	}

	private void homePage() {
		setContentPane(new homePanel());
		setTitle("字字字字典zzZict");
		setLayout(null);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		setLocation( (scr.width - frm.width) / 2,
		            (scr.height - frm.height) / 2 - 18);
		txInput.setBounds(320, 154, 195, 35);
		txInput.setBorder(new EmptyBorder(0,0,0,0));
		txInput.setFont(new Font("微软雅黑",Font.BOLD,16));
		search.setBounds(530,154,80,37);
		youdao.setBounds(279, 208, 15,15);
		baidu.setBounds(398,208,15,15);
		bing.setBounds(517,208,15,15);
		likeButton.setBounds(0, 0, 80, 200);
		//homeButton.setBounds(0,200,80,200);
		userButton.setBounds(0,400,80,200);
		homeMarkJLabel.setBounds(0, 200, 80, 200);

		//第一条第二条第三条，可以根据需要选择是否显示
		// TODO Need Get Explanation is Done.
		part1();
		part2();
		part3();

		add(likeButton);
		//add(homeButton);
		add(userButton);
		add(homeMarkJLabel);
		add(txInput);
		add(search);
		add(youdao);
		add(baidu);
		add(bing);
		flushUserState();
		setVisible(true);
	}

	private void part1() {
		txOut[0].setBounds(140,241,540,100);
		txOut[0].setLineWrap(true);
		txOut[0].setWrapStyleWord(true);
		like1.setBounds(706, 262, 61, 28);
		share1.setBounds(706,299,61,28);
		add(txOut[0]);
		add(like1);
		add(share1);
	}

	private void part2() {
		txOut[1].setBounds(140,350,540,100);
		txOut[1].setLineWrap(true);
		txOut[1].setWrapStyleWord(true);
		like2.setBounds(706, 371, 61,28);
		share2.setBounds(706,409,61,28);
		add(txOut[1]);
		add(like2);
	}

	private void part3() {
		txOut[2].setBounds(140,460,540,100);
		txOut[2].setLineWrap(true);
		txOut[2].setWrapStyleWord(true);
		like3.setBounds(706, 480,61,28);
		share3.setBounds(706,518,61,28);
		add(txOut[2]);
		add(like3);
		add(share3);
	}

	private void userPage1() {
		setContentPane(new userPanel1());
		setTitle("字字字字典Zict");
		setLayout(null);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		setLocation( (scr.width - frm.width) / 2,
		            (scr.height - frm.height) / 2 - 18);

		usernameArea.setBounds(378, 130, 200, 20);
		usernameArea.setFont(new Font("微软雅黑",Font.BOLD , 16));
		usernameArea.setOpaque(false);
		usernameArea.setText(UserInfo.getName());
		add(usernameArea);

		friend.setBounds(323,303,203,71);
		friend.setBorder(new EmptyBorder(0,0,0,0));
		add(friend);
		likeButton.setBounds(0, 0, 80, 200);
		homeButton.setBounds(0,200,80,200);
		//userButton.setBounds(0,400,80,200);
		//homeMarkJLabel.setBounds(0, 200, 80, 200);
		userMarkJLabel.setBounds(0,400,80,200);
		add(likeButton);
		add(homeButton);
		//add(userButton);
		add(userMarkJLabel);
		setVisible(true);
	}

	private void userPage2() {
		this.setContentPane(new userPanel2());
		setTitle("字字字字典Zict");
		setLayout(null);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		setLocation( (scr.width - frm.width) / 2,
		            (scr.height - frm.height) / 2 - 18);

		usernameArea.setBounds(378, 130, 200, 20);
		usernameArea.setFont(new Font("微软雅黑",Font.BOLD , 16));
		usernameArea.setOpaque(false);
		usernameArea.setText(UserInfo.getName());
		add(usernameArea);

		likeButton.setBounds(0, 0, 80, 200);
		homeButton.setBounds(0,200,80,200);
		//userButton.setBounds(0,400,80,200);
		//homeMarkJLabel.setBounds(0, 200, 80, 200);
		userMarkJLabel.setBounds(0,400,80,200);
		add(likeButton);
		add(homeButton);
		//add(userButton);
		add(userMarkJLabel);
		setVisible(true);
	}

	private void likePage() {
		this.setContentPane(new likePanel());
		setTitle("字字字字典Zict");
		setLayout(null);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		setLocation( (scr.width - frm.width) / 2,
		            (scr.height - frm.height) / 2 - 18);

		usernameArea.setBounds(250, 57, 200, 20);
		usernameArea.setFont(new Font("微软雅黑",Font.BOLD , 16));
		usernameArea.setOpaque(false);
		usernameArea.setText(UserInfo.getName());
		add(usernameArea);

		//likeButton.setBounds(0, 0, 80, 200);
		homeButton.setBounds(0,200,80,200);
		userButton.setBounds(0,400,80,200);
		//homeMarkJLabel.setBounds(0, 200, 80, 200);
		likeMarkJLabel.setBounds(0, 0, 80, 200);
		//add(likeButton);
		add(homeButton);
		add(userButton);
		add(likeMarkJLabel);
		setVisible(true);
	}

	public void flushUserState() {
		if (!UserInfo.isLogged()){
			login.setBounds(618, 15, 64, 32);
			register.setBounds(706,15,64,32);        
			add(login);
			add(register);
		}
		else {
			welcome.setBounds(570,15,250,32);
			welcomeUser.setBounds(681, 19, 100, 32);
			welcomeUser.setOpaque(false);
			welcomeUser.setFont(new Font("微软雅黑",Font.BOLD , 16));
			welcomeUser.setText(UserInfo.getName());
			add(welcome);
			add(welcomeUser);
		}
	}

	public static void main(String []args)throws Exception{
		File configFile = new File("config.txt");
		if (!configFile.exists()) {
			System.out.println("Configuration File Missing!");
			return;
		}
		Scanner configScanner = new Scanner(configFile);
		String serverIP = configScanner.nextLine();
		configScanner.close();
		System.out.print("Connecting Remote Server ... ");
		try {
			Network.connectToServer(serverIP);
		} catch (Exception e) {
			System.err.println("Undone");
			System.err.println("Can't connect to the remote server.");
			return;
		}
		Network.receiveFromServer();
		System.out.println("Done");
		mainFrame = new UImain();
	}
}
