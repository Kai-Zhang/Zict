package client;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.midi.Receiver;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import data.Explanation;
import data.UserInfo;
import data.WordCard;
import data.WordEntry;
import logic.ServiceProvider;
import network.Network;

@SuppressWarnings("serial")
public class UImain extends JFrame{
	public static UImain mainFrame = null;
	public String []wordCardContent=new String[2];
	
	private JTextArea usernameArea=new JTextArea();
	private JList<String> onlineJList = new JList<String>(UserInfo.getOnlineUsers());
	private JList<String> offlineJList=new JList<String>(UserInfo.getOfflineUsers());
	private JList<WordCard> wordCardList = new JList<WordCard>();
	private JTextArea[] txOut = {new JTextArea(5,50), new JTextArea(5,50), new JTextArea(5,50)};
	private JTextField txInput = new JTextField("请输入英文", 42);
	private JTextField idInput=new JTextField(16);
	
	private JTextArea likeNum1=new JTextArea();
	private JTextArea likeNum2=new JTextArea();
	private JTextArea likeNum3=new JTextArea();
	
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
	private ImageIcon unlogIcon=new ImageIcon("images/unlog.png");
	
	private ImageIcon moreIconEmpty=new ImageIcon("images/button_04_empty.png");
	private JButton moreButtonEmpty=new JButton(moreIconEmpty);
    private ImageIcon wordCardIKnowIcon=new ImageIcon("images/iknow.png");
	private JButton wordCardIKnow=new JButton(wordCardIKnowIcon);
	private ImageIcon saveIcon=new ImageIcon("images/save.png");
	private JButton saveButton=new JButton(saveIcon);
	
	private JLabel homeMarkJLabel=new JLabel(homeIconM);
	private JButton userMarkJButton=new JButton(userIconM);
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
    private JButton unlogButton=new JButton(unlogIcon);
    
    private JButton share1=new JButton(shareIcon);
    private JButton share2=new JButton(shareIcon);
    private JButton share3=new JButton(shareIcon);
    
    private CheckOnlineList shareList=null;
    
    private JButton like1=new JButton(likelogoIcon);
    private JButton like2=new JButton(likelogoIcon);
    private JButton like3=new JButton(likelogoIcon);
    private JButton unlike1=new JButton(likeagreeIcon);
    private JButton unlike2=new JButton(likeagreeIcon);
    private JButton unlike3=new JButton(likeagreeIcon);
	 
    private JCheckBox youdao =new JCheckBox("有道",true);
    private JCheckBox baidu =new JCheckBox("百度",true);
    private JCheckBox bing =new JCheckBox("必应",true);
    
    final JFrame regFrame=new JFrame("Register");
    final JFrame loginFrame=new JFrame("Login");
    final JFrame wordCardfFrame=new JFrame("wordCard");
    
    private ImageIcon moreIcon=new ImageIcon("images/button_04.png");
    private ImageIcon moreIconM=new ImageIcon("images/buttonmark_04.png");
    private ImageIcon userMarkIcon=new ImageIcon("images/button_03mark.png");
    private JButton userButtonMark=new JButton(userMarkIcon);
    private JButton moreButton=new JButton(moreIcon);
    private JLabel moreMarkLabel=new JLabel(moreIconM);
    
    private ImageIcon wordcardIcon=new ImageIcon("images/wordcard.png");
    private ImageIcon wordcardmarkIcon=new ImageIcon("images/wordcardmark.png");
    private JButton wcInUserPageButton=new JButton(wordcardIcon);
    private JButton wcInUserPagemarkButton=new JButton(wordcardmarkIcon);
    
 	public UImain() throws Exception {
		mainPage();
    	login.setBorder(new EmptyBorder(0,0,0,0));
    	register.setBorder(new EmptyBorder(0,0,0,0));
    	loginPageLoginOk.setBorder(new EmptyBorder(0,0,0,0));
    	search.setBorder(new EmptyBorder(0,0,0,0));
    	regExit.setBorder(new EmptyBorder(0,0,0,0));
    	loginExit.setBorder(new EmptyBorder(0,0,0,0));
    	regPageRegOk.setBorder(new EmptyBorder(0,0,0,0));
    	unlogButton.setBorder(new EmptyBorder(0,0,0,0));

     	share1.setBorder(new EmptyBorder(0,0,0,0));
    	share2.setBorder(new EmptyBorder(0,0,0,0));
    	share3.setBorder(new EmptyBorder(0,0,0,0));
    	
    	like1.setBorder(new EmptyBorder(0,0, 0, 0));
    	like2.setBorder(new EmptyBorder(0,0, 0, 0));
    	like3.setBorder(new EmptyBorder(0,0, 0, 0));

    	login.setContentAreaFilled(false);
    	register.setContentAreaFilled(false);
    	loginPageLoginOk.setContentAreaFilled(false);
    	regExit.setContentAreaFilled(false);
    	loginExit.setContentAreaFilled(false);
    	regPageRegOk.setContentAreaFilled(false);
    	unlogButton.setContentAreaFilled(false);
    	search.setContentAreaFilled(false);
    	saveButton.setContentAreaFilled(false);
    	wordCardIKnow.setContentAreaFilled(false);
    	wordCardList.setOpaque(false);
    	wordCardList.setBackground(new Color(0,0,0,0));
    	onlineJList.setOpaque(false);
    	onlineJList.setBackground(new Color(0,0,0,0));
    	offlineJList.setOpaque(false);
    	offlineJList.setBackground(new Color(0,0,0,0));
    	
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
					JOptionPane.showMessageDialog(null, "账号不能为空!");
				} else if (password.equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空!");
				} else if (!userID.matches("\\w{1,16}")) {
					JOptionPane.showMessageDialog(null, "账号格式不正确");
				} else if (!password.matches("\\w{6,}")) {
					JOptionPane.showMessageDialog(null, "密码格式不正确");
				} else {
					UserInfo.login(userID, password);
	            }
			}
		});

		unlogButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserInfo.logout();
				flushMainPage();
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
					regFrame.dispose();
				}
			}
		});

		like1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (UserInfo.isLogged()) {
					ServiceProvider.clickLike(WordEntry.getWord(), WordEntry.getExplanation(0).getSource());
					int newLikeNumber = WordEntry.getExplanation(0).getLikeNumber() + 1;
					WordEntry.getExplanation(0).setLikeNumber(newLikeNumber);
					likeNum1.setText(" "+newLikeNumber + "");
					WordEntry.getExplanation(0).setLiked(true);
					like1();
					repaint();
				}
				else {
					JOptionPane.showMessageDialog(null, "请先登录");
					loginPage();
				}
			}
		});

		like2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (UserInfo.isLogged()) {
					ServiceProvider.clickLike(WordEntry.getWord(), WordEntry.getExplanation(1).getSource());
					int newLikeNumber = WordEntry.getExplanation(1).getLikeNumber() + 1;
					WordEntry.getExplanation(1).setLikeNumber(newLikeNumber);
					likeNum2.setText(" "+newLikeNumber + "");
					WordEntry.getExplanation(1).setLiked(true);
					like2();
					repaint();
				}
				else {
					JOptionPane.showMessageDialog(null, "请先登录");
					loginPage();
				}
			}
		});

		like3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (UserInfo.isLogged()) {
					ServiceProvider.clickLike(WordEntry.getWord(), WordEntry.getExplanation(2).getSource());
					int newLikeNumber = WordEntry.getExplanation(2).getLikeNumber() + 1;
					WordEntry.getExplanation(2).setLikeNumber(newLikeNumber);
					likeNum3.setText(" "+newLikeNumber + "");
					WordEntry.getExplanation(2).setLiked(true);
					like3();
					repaint();
				}
				else {
					JOptionPane.showMessageDialog(null, "请先登录");
					loginPage();
				}
			}
		});
		
		unlike1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (UserInfo.isLogged()) {
					ServiceProvider.cancelLike(WordEntry.getWord(), WordEntry.getExplanation(0).getSource());
					int newLikeNumber = WordEntry.getExplanation(0).getLikeNumber() - 1;
					WordEntry.getExplanation(0).setLikeNumber(newLikeNumber);
					likeNum1.setText(" "+newLikeNumber + "");
					WordEntry.getExplanation(0).setLiked(false);
					unlike1();
					repaint();
				}
				else {
					JOptionPane.showMessageDialog(null, "请先登录");
					loginPage();
				}
			}
		});
		
		unlike2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (UserInfo.isLogged()) {
					ServiceProvider.cancelLike(WordEntry.getWord(), WordEntry.getExplanation(1).getSource());
					int newLikeNumber = WordEntry.getExplanation(1).getLikeNumber() - 1;
					WordEntry.getExplanation(1).setLikeNumber(newLikeNumber);
					likeNum2.setText(" "+newLikeNumber + "");
					WordEntry.getExplanation(1).setLiked(false);
					unlike2();
					repaint();
				}
				else {
					JOptionPane.showMessageDialog(null, "请先登录");
					loginPage();
				}
			}
		});
		
		unlike3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (UserInfo.isLogged()) {
					ServiceProvider.cancelLike(WordEntry.getWord(), WordEntry.getExplanation(2).getSource());
					int newLikeNumber = WordEntry.getExplanation(2).getLikeNumber() - 1;
					WordEntry.getExplanation(2).setLikeNumber(newLikeNumber);
					likeNum3.setText(" "+newLikeNumber + "");
					WordEntry.getExplanation(2).setLiked(false);
					unlike3();
					repaint();
				}
				else {
					JOptionPane.showMessageDialog(null, "请先登录");
					loginPage();
				}
			}
		});

		share1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (UserInfo.isLogged()) {
					shareList = new CheckOnlineList(0);
					ServiceProvider.getUserList();
				}
				else {
					JOptionPane.showMessageDialog(null, "请先登录");
					loginPage();
				}
			}
		});

		share2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (UserInfo.isLogged()) {
					shareList = new CheckOnlineList(1);
					ServiceProvider.getUserList();
				}
				else {
					JOptionPane.showMessageDialog(null, "请先登录");
					loginPage();
				}
			}
		});

		share3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (UserInfo.isLogged()) {
					shareList = new CheckOnlineList(2);
					ServiceProvider.getUserList();
				}
				else {
					JOptionPane.showMessageDialog(null, "请先登录");
					loginPage();
				}
			}
		});

		search.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String currentWord = txInput.getText();
				if (!currentWord.matches("[a-zA-Z]*")) {
					JOptionPane.showMessageDialog(null, "请输入合法的英文单词");
					return;
				}
				homePage();
				txOut[0].setText("正在服务器上查找...");
				txOut[1].setText("正在服务器上查找...");
				txOut[2].setText("正在服务器上查找...");
				ServiceProvider.getExplanation(currentWord);
				flushResultPage();
			}
		});

        homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				homePage();
				flushResultPage();
			}
		});

        userButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(UserInfo.isLogged()){
					userButton.setIcon(userIcon);
					userPage1();
					}
				else {
					loginPage();
				}
			}
		});
        
        moreButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				morePage();
			}
		});
        
        moreButtonEmpty.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				morePage();
			}
		});
        
        userMarkJButton.addActionListener(new ActionListener() {//在userPage当前页面 点击左边的user时
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(UserInfo.isLogged()){
					userPage1();
					}
				else {
					loginPage();
				}
			}
		});
        

        wcInUserPagemarkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				wclistPage();
				//wordCard(wordCardContent);
			}
		});
        wcInUserPageButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				wclistPage();
			}
		});

		likeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiceProvider.getLikedList();
				if(UserInfo.isLogged())
					likePage();
				else {
					loginPage();
				}
			}
		});

		friend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServiceProvider.getUserList();
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
				UserInfo.logout();
				Network.sendToServer("Bye!");
			}
			
			@Override
			public void windowClosed(WindowEvent e) { }
			
			@Override
			public void windowActivated(WindowEvent e) { }
		});
		
		wordCardIKnow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				wordCardfFrame.dispose();
			}
		});
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BufferedImage  bi = new BufferedImage(wordCardfFrame.getWidth(), wordCardfFrame.getHeight()-30, BufferedImage.TYPE_INT_ARGB);
				Graphics2D  g2d = bi.createGraphics();
				wordCardfFrame.paint(g2d);

				Date dt=new Date();
			    SimpleDateFormat matter1=new SimpleDateFormat("yyyyMMddHHmmss");
			    //System.out.println(matter1.format(dt));
			    String str=matter1.format(dt).toString();
			    //String str =new String(Integer.toString((int)(Math.random()*100)));
				File pic=new File(str+".png");
				try {
					ImageIO.write(bi, "PNG", pic);
					JOptionPane.showMessageDialog(null, "保存成功！");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		wordCardList.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent evt) {
		            if (evt.getClickCount() == 2) {          // Double-click
		                
		            } 
		        }
		});
	}
	
 	private void buttonArea(JButton button){
 		likeButton.setBounds(0, 0, 108, 150);
        homeButton.setBounds(0,150,108,150);
        userButton.setBounds(0,300,108,150);
        moreButton.setBounds(0,450,108,150); 
        
        homeButton.setBorder(new EmptyBorder(0,0,0,0));
        userButton.setBorder(new EmptyBorder(0,0,0,0));
        likeButton.setBorder(new EmptyBorder(0,0,0,0));
        moreButton.setBorder(new EmptyBorder(0,0,0,0));
        
        likeButton.setContentAreaFilled(false);
        homeButton.setContentAreaFilled(false);
        userButton.setContentAreaFilled(false);
        moreButton.setContentAreaFilled(false);
        
        
        if (button==homeButton) {
       	 homeMarkJLabel.setBounds(0, 150, 108, 150);
       	 add(homeMarkJLabel);
        }else if (button==userButton) {
       	 userMarkJButton.setBounds(0, 300, 108, 150);
       	 userMarkJButton.setBorder(new EmptyBorder(0,0,0,0));
         userMarkJButton.setContentAreaFilled(false);
       	 add(userMarkJButton);
		}else if (button==likeButton) {
			likeMarkJLabel.setBounds(0, 0, 108, 150);
	        add(likeMarkJLabel);
		}else if (button==moreButton) {
			moreMarkLabel.setBounds(0, 450, 108, 150);
			add(moreMarkLabel);
		}
        
        add(likeButton);
        add(userButton);
        add(homeButton);
        add(moreButton);
        //add(userButtonMark);
        remove(button);
        
   }

 	public void wordCard(String [] str) {
		wordCardfFrame.setContentPane(new wordCardpanel());
		wordCardfFrame.setResizable(false);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		wordCardfFrame.setLocation( (scr.width - frm.width) / 2,
		            (scr.height - frm.height) / 2 - 18);
		JLabel word=new JLabel(str[1]);
		JTextArea exp=new JTextArea(str[2]);
		exp.setOpaque(false);
		exp.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 14));
		word.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 14));
		exp.setLineWrap(true);
		wordCardIKnow.setBorder(new EmptyBorder(0,0,0,0));
		saveButton.setBorder(new EmptyBorder(0,0,0,0));
		wordCardfFrame.setLayout(null);
		wordCardfFrame.add(wordCardIKnow).setBounds(0, 267, 125, 33);
		wordCardfFrame.add(saveButton).setBounds(124,267,125,33);
		wordCardfFrame.add(word).setBounds(30, 85, 180, 20);
		wordCardfFrame.add(exp).setBounds(30, 120, 180, 100);
		
		wordCardfFrame.setSize(250,325);
		wordCardfFrame.setLocationRelativeTo(null);
		wordCardfFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		wordCardfFrame.setVisible(true);
		wordCardfFrame.repaint();
		}
 	private class morePanel extends JPanel{
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			super.paintComponent(g);
			Image img = Toolkit.getDefaultToolkit().getImage("images/morebg.png");
			g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	private class wordCardpanel extends JPanel{
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			super.paintComponent(g);
			Image img = Toolkit.getDefaultToolkit().getImage("images/iknowbg.png");
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

    private class mainPanel extends JPanel {
        public void paintComponent(Graphics g) {
       	 Graphics2D g2 = (Graphics2D)g;
       	 super.paintComponent(g);
       	 Image img = Toolkit.getDefaultToolkit().getImage("images/mainbg.png");
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

	private class wclistPanel extends JPanel {
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			super.paintComponent(g);
			Image img = Toolkit.getDefaultToolkit().getImage("images/wclistbg.png");
			g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	
	private void wclistPage() {
		setContentPane(new wclistPanel());
		setTitle("字字字字典Zict");
		setLayout(null);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocale(getLocale());
		
		wordCardList.setListData(UserInfo.getReceivedCards().toArray(new WordCard[UserInfo.getReceivedCards().size()]));
		JScrollPane wclist=new JScrollPane(wordCardList);
		wclist.setOpaque(false);
		wclist.getViewport().setOpaque(false);
		wclist.setBounds(230,303,420,220);
		wclist.setBorder(new EmptyBorder(0,0,0,0));

		add(wclist);

		buttonArea(userButton);
		flushWCList();
		flushUserState();
		setVisible(true);
	}
	
	private void loginPage() {
		loginFrame.setContentPane(new loginPanel());
		loginFrame.setResizable(false);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		loginFrame.setLocation( (scr.width - frm.width) / 2,
		            (scr.height - frm.height) / 2 - 18);

		loginFrame.setLayout(null);
		loginFrame.add(idInput).setBounds(59, 76, 155, 28);
		idInput.setBorder(new EmptyBorder(0,0,0,0));
		loginFrame.add(keyInput).setBounds(58, 116, 155, 28);
		keyInput.setBorder(new EmptyBorder(0,0,0,0));
		loginFrame.add(loginPageLoginOk).setBounds(28, 159, 85, 32);
		loginFrame.add(loginExit).setBounds(129, 159, 85, 32);


		loginExit.setMnemonic(KeyEvent.VK_ESCAPE);
		loginPageLoginOk.setMnemonic(KeyEvent.VK_ENTER);
		
		loginFrame.setSize(250,230);
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
		regFrame.add(regIdInput).setBounds(60, 116, 155, 30);
		regIdInput.setBorder(new EmptyBorder(0,0,0,0));
		regFrame.add(regKeyInput1).setBounds(60, 155, 155, 30);
		regKeyInput1.setBorder(new EmptyBorder(0,0,0,0));
		regFrame.add(regKeyInput2).setBounds(60, 192, 155, 30);
		regKeyInput2.setBorder(new EmptyBorder(0,0,0,0));
		regFrame.add(regPageRegOk).setBounds(27, 233, 85, 32);
		regFrame.add(regExit).setBounds(127, 233, 85, 32);
	
		regFrame.setSize(250,310);
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

		txInput.setBounds(271, 350, 210, 35);
		txInput.setBorder(new EmptyBorder(0,0,0,0));
		txInput.setOpaque(false);
		txInput.setFont(new Font("微软雅黑",Font.BOLD,16));
		search.setBounds(498,345,82,42);
		youdao.setBounds(232, 408, 15,15);
		baidu.setBounds(360,408,15,15);
		bing.setBounds(490,408,15,15);

		moreButtonEmpty.setBounds(0,450,108,150); 
		moreButtonEmpty.setBorder(new EmptyBorder(0,0,0,0));
		moreButtonEmpty.setContentAreaFilled(false);
		
		add(moreButtonEmpty);
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
		setTitle("字字字字典Zict");
		setLayout(null);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocale(getLocale());
		txInput.setBounds(320, 154, 195, 35);
		txInput.setBorder(new EmptyBorder(0,0,0,0));
		txInput.setOpaque(false);
		txInput.setFont(new Font("微软雅黑",Font.BOLD,16));
		search.setBounds(530,154,82,42);
		youdao.setBounds(279, 208, 15,15);
		baidu.setBounds(398,208,15,15);
		bing.setBounds(517,208,15,15);
		
		buttonArea(homeButton);
		
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
        like1.setBounds(706, 262, 31, 28);
        share1.setBounds(706,299,61,28);
        
        likeNum1.setBounds(735, 263, 30, 26);
        likeNum1.setFont(new Font("微软雅黑",Font.PLAIN , 16));
        likeNum1.setText(" "+WordEntry.getExplanation(0).getLikeNumber());
        likeNum1.setForeground(Color.black);
        likeNum1.setEditable(false);
		
        add(txOut[0]);
		add(like1);
		add(share1);
		add(likeNum1);
	}

	private void part2() {
		txOut[1].setBounds(140,350,540,100);
		txOut[1].setLineWrap(true);
		txOut[1].setWrapStyleWord(true);
        like2.setBounds(706, 371, 31, 28);
        share2.setBounds(706,409,61,28);
		
        likeNum2.setBounds(735, 372, 30, 26);
        //zanNum2.setOpaque(false);
        likeNum2.setFont(new Font("微软雅黑",Font.PLAIN , 16));
        likeNum2.setText(" "+WordEntry.getExplanation(1).getLikeNumber());
        likeNum2.setForeground(Color.black );
        likeNum2.setEditable(false);
        
		add(txOut[1]);
		add(like2);
        add(share2);
        add(likeNum2);
	}

	private void part3() {
		txOut[2].setBounds(140,460,540,100);
		txOut[2].setLineWrap(true);
		txOut[2].setWrapStyleWord(true);
		like3.setBounds(706, 480,31,28);
		share3.setBounds(706,518,61,28);
		
        likeNum3.setBounds(735, 481, 30, 26);
        //zanNum3.setOpaque(false);
        likeNum3.setFont(new Font("微软雅黑",Font.PLAIN , 16));
        likeNum3.setText(" "+WordEntry.getExplanation(2).getLikeNumber());
        likeNum3.setForeground(Color.black);
        likeNum3.setEditable(false);
		
		add(txOut[2]);
		add(like3);
		add(share3);
		add(likeNum3);
	}

	private void like1(){
		 remove(like1);
	    	unlike1.setBounds(706, 262, 31, 28);
	    	add(unlike1);
	        
	    }
	private void unlike1(){
		 remove(unlike1);
	    	like1.setBounds(706, 262, 31, 28);
	    	add(like1);
	        
	    }
	private void like2(){
		 remove(like2);
	    	unlike2.setBounds(706, 371, 31, 28);
	    	add(unlike2);
	        
	    }
	private void unlike2(){
		 remove(unlike2);
	    	like2.setBounds(706, 371, 31, 28);
	    	add(like2);
	        
	    }
	private void like3(){
		 remove(like3);
	    	unlike3.setBounds(706, 480, 31, 28);
	    	add(unlike3);
	        
	    }
	private void unlike3(){
		 remove(unlike3);
	    	like3.setBounds(706, 480, 31, 28);
	    	add(like3);
	        
	    }
	
	private void userPage1() {
		setContentPane(new userPanel1());
		setTitle("字字字字典Zict");
		setLayout(null);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocale(getLocale());
		usernameArea.setBounds(378, 130, 200, 20);
		usernameArea.setFont(new Font("微软雅黑",Font.BOLD , 16));
		usernameArea.setOpaque(false);
		usernameArea.setText(UserInfo.getName());
		add(usernameArea);

		friend.setBounds(323,303,203,71);
		friend.setBorder(new EmptyBorder(0,0,0,0));
		add(friend);
		wcInUserPageButton.setBounds(323,387,203,71);
		wcInUserPageButton.setBorder(new EmptyBorder(0,0,0,0));
		wcInUserPageButton.setContentAreaFilled(false);
		add(wcInUserPageButton);
		buttonArea(userButton);
		flushUserState();
		setVisible(true);
	}

	private void userPage2() {
		this.setContentPane(new userPanel2());
		setTitle("字字字字典Zict");
		setLayout(null);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocale(getLocale());
		usernameArea.setBounds(378, 130, 200, 20);
		usernameArea.setFont(new Font("微软雅黑",Font.BOLD , 16));
		usernameArea.setOpaque(false);
		usernameArea.setText(UserInfo.getName());
		add(usernameArea);

		JScrollPane onl=new JScrollPane(onlineJList);
		JScrollPane offl=new JScrollPane(offlineJList);
		onl.setBounds(236,303,100,220);
		offl.setBounds(536,303,100,220);
		onl.setBorder(new EmptyBorder(0,0,0,0));
		offl.setBorder(new EmptyBorder(0,0,0,0));
		onl.setOpaque(false);
		onl.getViewport().setOpaque(false);
		offl.setOpaque(false);
		offl.getViewport().setOpaque(false);
		add(onl);
		add(offl);

		buttonArea(userButton);
		flushUserState();
		setVisible(true);
	}

	private void likePage() {
		this.setContentPane(new likePanel());
		setTitle("字字字字典Zict");
		setLayout(null);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocale(getLocale());
		usernameArea.setBounds(250, 57, 200, 20);
		usernameArea.setFont(new Font("微软雅黑",Font.BOLD , 16));
		usernameArea.setOpaque(false);
		usernameArea.setText(UserInfo.getName());
		add(usernameArea);

		JScrollPane word=new JScrollPane(onlineJList);
		JScrollPane site=new JScrollPane(offlineJList);
		word.setBounds(450,247,90,240);//单词
		site.setBounds(308,247,90,240);//网站
		word.setOpaque(false);
		word.getViewport().setOpaque(false);
		site.setOpaque(false);
		site.getViewport().setOpaque(false);
		
		word.setBorder(new EmptyBorder(0,0,0,0));
		site.setBorder(new EmptyBorder(0,0,0,0));
		

		add(word);
		add(site);
		
		buttonArea(likeButton);
		flushUserState();
		setVisible(true);
	}
	
	private void morePage(){
		this.setContentPane(new morePanel());
		setTitle("字字字字典Zict");
		setLayout(null);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocale(getLocale());
		
		buttonArea(moreButton);
		flushUserState();
		setVisible(true);
	}
	
	public void flushResultPage(){
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
			if (i==0) {
				part1();
			}else if (i==1) {
				part2();
			}else if (i==2) {
				part3();
			}else ;
		}
		repaint();
		flushLikeStatus();
	}

	public void flushUserState() {
		if (!UserInfo.isLogged()){
			login.setBounds(618, 15, 64, 32);
			register.setBounds(706,15,64,32);     
			remove(welcome);
			remove(welcomeUser);
			remove(unlogButton);   
			add(login);
			add(register);
		}
		else {
    		unlogButton.setBounds(730,15,50,32);
    		unlogButton.setOpaque(false);
    		welcome.setBounds(500,15,250,32);
    		welcomeUser.setBounds(611, 20, 100, 32);
    		welcomeUser.setOpaque(false);
    		welcomeUser.setFont(new Font("微软雅黑",Font.BOLD , 16));
    		welcomeUser.setText(UserInfo.getName());
    		welcomeUser.setForeground(Color.black);
    		welcomeUser.setEditable(false);
    		remove(login);
    		remove(register);
    		add(welcome);
    		add(welcomeUser);
    		add(unlogButton);
		}
		repaint();
	}
	
	public void flushUserList() {
		onlineJList.setListData(UserInfo.getOnlineUsers());
		offlineJList.setListData(UserInfo.getOfflineUsers());
		if (shareList != null) {
			shareList.refreshList();
		}
	}
	
	public void flushWCList(){
		wordCardList.setListData(UserInfo.getReceivedCards().toArray(new WordCard[UserInfo.getReceivedCards().size()]));
		repaint();
				
	}
	
	public void flushLikeStatus() {
		if (UserInfo.isLogged()) {
			if (WordEntry.getExplanation(0) != null) {
				if (WordEntry.getExplanation(0).isLiked()) {
					like1(); repaint();
				}
				else {
					unlike1(); repaint();
				}
			}
			else {
				unlike1(); repaint();
			}
			if (WordEntry.getExplanation(1) != null) {
				if (WordEntry.getExplanation(1).isLiked()) {
					like2(); repaint();
				}
				else {
					unlike2(); repaint();
				}
			}
			else {
				unlike2(); repaint();
			}
			if (WordEntry.getExplanation(2) != null) {
				if (WordEntry.getExplanation(2).isLiked()) {
					like3(); repaint();
				}
				else {
					unlike3(); repaint();
				}
			}
			else {
				unlike3(); repaint();
			}
		}
		else {
			//unlike1(); unlike2(); unlike3(); repaint();
		}
	}

	public void flushWordCardArea(){
		userButton.setIcon(userMarkIcon);
		//remove(userButton);
		//userButtonMark.setBounds(0,300,108,150);
		//userButtonMark.setBorder(new EmptyBorder(0,0,0,0));
		//userButtonMark.setContentAreaFilled(false);
		//add(userButtonMark);
		//System.out.print("hahhah");
	}
	
	public void flushMainPage(){
		mainPage();
	}
	
	public void flushHomePage(){
		homePage();
	}
	public void loginFrameDispose(){
		loginFrame.dispose();
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
