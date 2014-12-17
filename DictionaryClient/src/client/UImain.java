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
import javax.swing.event.*;
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
	private static final ActionListener ActionListener = null;

	private String username=new String("hahahhhah");
	private JTextArea usernameArea=new JTextArea();
	private boolean loginOrNot=true;//false未登录
    private String[] onlinefri =new String[100];  //在线好友
    private String[] offlinefri=new String[100];//不在线好友
	private JList online = new JList(onlinefri);
	private JList offline= new JList(offlinefri);
	private JTextArea txOut1 = new JTextArea(5,50);
	private JTextArea txOut2 = new JTextArea(5,50);
	private JTextArea txOut3 = new JTextArea(5,50);

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
    public JButton sendTo=new JButton("send");
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
				
				 if (idInput.getText().equals("")) {
	                    JOptionPane.showMessageDialog(null, "用户名不能为空!");
	                } else if (keyInput.getText().equals("")) {
	                    JOptionPane.showMessageDialog(null, "密码不能为空!");
	                } else {
	    				String userID = idInput.getText();
	    				String password = keyInput.getText();
	    				if (userID == null || password == null) {
						return;
					}
					UserInfo.login(userID, password);
	            }
				
				// TODO: Result Echo
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

	               if (regIdInput.getText().equals("")) {
	                    JOptionPane.showMessageDialog(null, "用户名不能为空!");
	                } else if (regKeyInput1.getText().equals("")) {
	                    JOptionPane.showMessageDialog(null, "密码不能为空!");
	                } else if (regKeyInput2.getText().equals("")) {
	                    JOptionPane.showMessageDialog(null, "请再次输入密码");
	                } else if (regKeyInput1.getText().equals(regKeyInput2.getText())) {
	                    JOptionPane.showMessageDialog(null, "两次密码不一致！");
	                } else {
	                	String userID = regIdInput.getText();
	    				String password = regKeyInput1.getText();
	    				String passwordConfirm = regKeyInput2.getText();
	    				// TODO: More Careful Password Check
	    				if (userID == null || password == null || !passwordConfirm.equals(password)) {
	    					return;
	    				}
	    				if (userID.matches("\\w{1,16}") && password.matches("\\w{6,}")) {
	    					UserInfo.register(userID, password);
	    				}
	                	//检查用户名是否已经被注册
	                	//数据库录入并且把loginOrNot变成true
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
	             CheckOnlineList checkList = new CheckOnlineList(sendTo);
	             checkList.addWindowListener(new WindowAdapter() {
	                 public void windowClosing(WindowEvent we) {
	                    // System.exit(0);
	                 }
	             });
			}
		});
		 share2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					 
					CheckOnlineList checkList = new CheckOnlineList(sendTo);
		             checkList.addWindowListener(new WindowAdapter() {

		                 public void windowClosing(WindowEvent we) {
		                    // System.exit(0);
		                 }
		             });
		            
				}
			});
	        share3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					 
					CheckOnlineList checkList = new CheckOnlineList(sendTo);
		             checkList.addWindowListener(new WindowAdapter() {

		                 public void windowClosing(WindowEvent we) {
		                    // System.exit(0);
		                 }
		             });
		            
				}
			});
		// TODO: Maybe need to be deleted.
		txInput.getDocument().addDocumentListener(new DocumentListener(){  //�����
			@Override
			public void insertUpdate(DocumentEvent e) {
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				String in = txInput.getText();
				
				
				txInput.requestFocusInWindow();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
			});
		
		
		txInput.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		
		}
		);
		
		search.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				homePage();
				String currentWord = txInput.getText();
				ServiceProvider.getExplanation(currentWord);
				if (WordEntry.getExplanation(0) == null) {
					txOut1.setText("");
					txOut2.setText("");
					txOut3.setText("");
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
				if (outputList.size() >= 1) {
					txOut1.setText(outputList.get(0).getExplanation());
				}
				if (outputList.size() >= 2) {
					txOut1.setText(outputList.get(1).getExplanation());
				}
				if (outputList.size() == 3) {
					txOut1.setText(outputList.get(2).getExplanation());
				}
			}
		});
		sendTo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        homeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				homePage();
			}
		});
        userButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(loginOrNot)
					userPage1();
				else {
					loginPageNew();
				}
			}
		});
		likeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(loginOrNot)
					likePage();
				else {
					loginPageNew();
				}
			}
		});
		friend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
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
       
       
       private void loginPage(){
       	
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
       
       private void loginPageNew(){
       	
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

       private void regPage(){
       	
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
       
       private void mainPage(){
       	this.setContentPane(new mainPanel());
           setTitle("字字字字典zzZict");
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

       private void homePage(){
       	this.setContentPane(new homePanel());
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
       private void part1(){
           txOut1.setBounds(140,241,540,100);
           like1.setBounds(706, 262, 61, 28);
           share1.setBounds(706,299,61,28);
           add(txOut1);
           add(like1);
           add(share1);
       }
       private void part2(){
           txOut2.setBounds(140,350,540,100);
           like2.setBounds(706, 371, 61,28);
           share2.setBounds(706,409,61,28);
           add(txOut2);
           add(like2);
           add(share2);
       }
       private void part3(){
           txOut3.setBounds(140,460,540,100);
           like3.setBounds(706, 480,61,28);
           share3.setBounds(706,518,61,28);
           add(txOut3);
           add(like3);
           add(share3);
       }
       
       private void userPage1(){
       	this.setContentPane(new userPanel1());
           setTitle("字字字字典zzZict");
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
           usernameArea.setText(username);
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
       
       private void userPage2(){
       	this.setContentPane(new userPanel2());
           setTitle("字字字字典zzZict");
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
           usernameArea.setText(username);
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

       private void likePage(){
       	this.setContentPane(new likePanel());
           setTitle("字字字字典zzZict");
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
           usernameArea.setText(username);
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
       
       private void flushUserState(){
       	if(!loginOrNot){
       	login.setBounds(618, 15, 64, 32);
           register.setBounds(706,15,64,32);        
           add(login);
           add(register);
           }
       	else{
       		
       		welcome.setBounds(500,15,250,32);
       		welcomeUser.setBounds(611, 20, 100, 32);
       		welcomeUser.setOpaque(false);
       		welcomeUser.setFont(new Font("微软雅黑",Font.BOLD , 16));
       		welcomeUser.setText(username);
    		welcomeUser.setForeground(Color.white);
    		welcomeUser.setEditable(false);
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
		}
		Network.receiveFromServer();
		System.out.println("Done");
		new UImain();
	}
}

