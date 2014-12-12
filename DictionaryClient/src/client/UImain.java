package client;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UImain extends JFrame{
	private static final ActionListener ActionListener = null;
	private String[] ListWord =new String[100];  //�����
	private JList jlist = new JList(ListWord);
	private JTextArea txOut1 = new JTextArea("My Dictionary",5,20);
	private JTextArea txOut2 = new JTextArea("My Dictionary",5,20);
	private JTextArea txOut3 = new JTextArea("My Dictionary",5,20);
	private JTextField txInput = new JTextField("please input english", 42);
	private JTextField idInput=new JTextField(16);
	private JTextField keyInput=new JTextField(16);
	private JTextField regIdInput=new JTextField(16);
	private JTextField regKeyInput1=new JTextField("����������",16);
	private JTextField regKeyInput2=new JTextField("���ٴ���������",16);
    private JButton btSearch = new JButton("search");
    private JButton login=new JButton("��½");
    private JButton register=new JButton("ע��");
    private JButton registerOk=new JButton("ע��");
    private JButton loginOk=new JButton("��½");
    private JButton share1=new JButton("share");
    private JButton share2=new JButton("share");
    private JButton share3=new JButton("share");
    private JButton like1=new JButton("like");
    private JButton like2=new JButton("like");
    private JButton like3=new JButton("like");
    public JButton sendTo=new JButton("send");
    private JCheckBox bing =new JCheckBox("��Ӧ",false);
    private JCheckBox youdao =new JCheckBox("�е�",false);
    private JCheckBox baidu =new JCheckBox("�ٶ�",false);

	public UImain()throws Exception{
		
		//txOut.setEditable(false);   //ֻ��
		//txOut.setLineWrap(true);  //�Զ�����
		//txOut.setFont(new Font("΢���ź�",Font.BOLD,20));//����
		txInput.setFocusable(true);
		jlist.setFixedCellWidth(200);
		
		
		JPanel id=new JPanel();
		id.setLayout(new FlowLayout(FlowLayout.RIGHT,10,20));
		id.add(login);
		id.add(register);
		
		JPanel search=new JPanel();
		search.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
		search.add(new JLabel("Input the word"));
		search.add(txInput);
		search.add(btSearch);
		
		JPanel choose=new JPanel();
		choose.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
		choose.add(youdao);
		choose.add(baidu);
		choose.add(bing);

		//add(id,BorderLayout.NORTH);
		//add(search,BorderLayout.WEST);
		//add(choose,BorderLayout.SOUTH);
		JPanel upper=new JPanel();
		upper.setLayout(new BorderLayout(5,10));
		upper.add(id,BorderLayout.NORTH);
		upper.add(search,BorderLayout.CENTER);
		upper.add(choose,BorderLayout.SOUTH);
		
		JPanel more1=new JPanel();
		more1.setLayout(new FlowLayout(FlowLayout.RIGHT,10,20));
		more1.add(share1);
		more1.add(like1);
		
		JPanel more2=new JPanel();
		more2.setLayout(new FlowLayout(FlowLayout.RIGHT,10,20));
		more2.add(share2);
		more2.add(like2);
		
		JPanel more3=new JPanel();
		more3.setLayout(new FlowLayout(FlowLayout.RIGHT,10,20));
		more3.add(share3);
		more3.add(like3);
		
		JPanel r1=new JPanel();
		r1.setLayout(new BorderLayout(5,10));
		r1.add(txOut1,BorderLayout.CENTER);
		r1.add(more1,BorderLayout.EAST);
		
		JPanel r2=new JPanel();
		r2.setLayout(new BorderLayout(5,10));
		r2.add(txOut2,BorderLayout.CENTER);
		r2.add(more2,BorderLayout.EAST);
		
		JPanel r3=new JPanel();
		r3.setLayout(new BorderLayout(5,10));
		r3.add(txOut3,BorderLayout.CENTER);
		r3.add(more3,BorderLayout.EAST);
		
		JPanel result=new JPanel();
		result.setLayout(new GridLayout(3,1,5,5));
		result.add(r1);
		result.add(r2);
		result.add(r3);
		
		setLayout(new BorderLayout(5,10));
		add(upper,BorderLayout.NORTH);

		add(result,BorderLayout.CENTER);
		
		login.addActionListener(new ActionListener() {					//��½
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 final JFrame frame=new JFrame("Login");
	             frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
	             
	             frame.add(new JLabel("Id   "));
	             frame.add(idInput);
	             frame.add(new JLabel("Key"));
	             frame.add(keyInput);
	             frame.add(loginOk);
	             frame.setSize(240,200);
	             frame.setLocationRelativeTo(null);
	             frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	             frame.setVisible(true);
			}

		});
		
		loginOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 final JFrame frame=new JFrame("Register");
	             frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
	             frame.add(new JLabel("ID�������֡���ĸ��ɣ�16���ַ�����"));
	             frame.add(new JLabel("Id   "));
	             frame.add(regIdInput);
	             frame.add(new JLabel("Key"));
	             frame.add(regKeyInput1);
	             frame.add(new JLabel("Key"));
	             frame.add(regKeyInput2);
	             frame.add(registerOk);
	             frame.setSize(250,280);
	             frame.setLocationRelativeTo(null);
	             frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	             frame.setVisible(true);
			}
		});
		
		registerOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		like1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		like2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		like3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		share1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 
	             checklist checkList = new checklist();
	             checkList.addWindowListener(new WindowAdapter() {

	                 public void windowClosing(WindowEvent we) {
	                    // System.exit(0);
	                 }
	             });
	            
			}
		});
		
		txInput.getDocument().addDocumentListener(new DocumentListener(){  //�����

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String in = txInput.getText();
				
				
				txInput.requestFocusInWindow();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
				
			}
			});
		
		
		txInput.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		}
		);
		
		btSearch.addActionListener(new ActionListener(){   //���search��ť

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});


		setTitle("My Dictionary");
		setLocation(MAXIMIZED_HORIZ, MAXIMIZED_VERT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(683, 500);
		setVisible(true);
		
	}
	

	public static void main(String []args)throws Exception{

		UImain frame=new UImain() ;

	
	}

}
