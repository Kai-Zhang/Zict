package client;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

import javax.swing.JFrame;

import data.UserInfo;
import data.WordCard;
import logic.ServiceProvider;

@SuppressWarnings("serial")
public class CheckOnlineList extends JFrame {
	private JButton sendTo = new JButton("send");
    private JList<String> listDescription = new JList<String>(UserInfo.getOnlineUsers());
    CheckBoxItem[] checkBoxItems;
    public CheckOnlineList(int rating) {
        super("Send");
        JFrame jFrame=new JFrame();
        checkBoxItems = buildCheckBoxItems(UserInfo.getOnlineUsers().length);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Unable to find System Look and Feel");
        }
        JList<CheckBoxItem> listCheckBox = new JList<CheckBoxItem>(checkBoxItems);
        listDescription.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listDescription.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() != 2) {
                    return;
                }
                int selectedIndex = listDescription.locationToIndex(me.getPoint());
                if (selectedIndex < 0) {
                    return;
                }
                CheckBoxItem item = (CheckBoxItem) listCheckBox.getModel().getElementAt(selectedIndex);
                item.setChecked(!item.isChecked());
                listCheckBox.repaint();

            }
        });

        listCheckBox.setCellRenderer(new CheckBoxRenderer());
        listCheckBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listCheckBox.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                int selectedIndex = listCheckBox.locationToIndex(me.getPoint());
                if (selectedIndex < 0) {
                    return;
                }
                CheckBoxItem item = (CheckBoxItem) listCheckBox.getModel().getElementAt(selectedIndex);
                item.setChecked(!item.isChecked());
                listDescription.setSelectedIndex(selectedIndex);
                listCheckBox.repaint();
            }
        });
        setLayout(new BorderLayout(5,10));
        Container contentPane=jFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setRowHeaderView(listCheckBox);
        scrollPane.setViewportView(listDescription);
        listDescription.setFixedCellHeight(20);
        listCheckBox.setFixedCellHeight(listDescription.getFixedCellHeight());
        listCheckBox.setFixedCellWidth(20);

		sendTo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < UserInfo.getOnlineUsers().length; i ++) {
					if (checkBoxItems[i].isChecked()) {
						ServiceProvider.shareWordCard(UserInfo.getOnlineUsers()[i], rating);
					}
				}
				dispose();
			}
		});

        getContentPane().add(scrollPane); //, BorderLayout.CENTER);
        add(sendTo, BorderLayout.SOUTH);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private CheckBoxItem[] buildCheckBoxItems(int totalItems) {
        CheckBoxItem[] checkboxItems = new CheckBoxItem[totalItems];
        for (int counter = 0; counter < totalItems; counter++) {
            checkboxItems[counter] = new CheckBoxItem();
        }
        return checkboxItems;
    }


    private class CheckBoxItem {

        private boolean isChecked;

        public CheckBoxItem() {
            isChecked = false;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean value) {
            isChecked = value;
        }
    }

    /* Inner class that renders JCheckBox to JList*/
    @SuppressWarnings("rawtypes")
	class CheckBoxRenderer extends JCheckBox implements ListCellRenderer {

        public CheckBoxRenderer() {
            setBackground(UIManager.getColor("List.textBackground"));
            setForeground(UIManager.getColor("List.textForeground"));
        }

        public Component getListCellRendererComponent(JList listBox, Object obj, int currentindex,
                boolean isChecked, boolean hasFocus) {
            setSelected(((CheckBoxItem) obj).isChecked());
            return this;
        }
    }
    public void refreshList() {
    	listDescription.setListData(UserInfo.getOnlineUsers());
    }
}
