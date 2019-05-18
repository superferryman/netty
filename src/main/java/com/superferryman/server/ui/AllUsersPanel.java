package com.superferryman.server.ui;

import com.superferryman.dao.impl.UserDAOImpl;
import com.superferryman.pojo.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 在线用户面板
 * 
 * @author RuanYaofeng, VisonSun
 * @date 2018-04-18 10:19
 */
@SuppressWarnings("serial")
public class AllUsersPanel extends JPanel {

    // 初始化控件
    private JScrollPane scrollPane = new JScrollPane();
    private JTable tabUsers = new JTable();
    private JPopupMenu popupMenu = new JPopupMenu();
    private JMenuItem itemChange = new JMenuItem("修改信息");
    private JMenuItem itemRefresh = new JMenuItem("刷新列表");
    private JButton btnRefresh = new JButton("刷新所有成员列表");

    /**
     * Create the panel.
     */
    public AllUsersPanel() {
        // 设置布局
        setLayout(new BorderLayout(0, 0));
        // 初始化
        init();
        // 设置监听
        myListener();
    }

    /**
     * 初始化
     */
    private void init() {
        // 初始化列表
        updateTable();

        // 列表添加滚动条
        add(scrollPane);
        scrollPane.setViewportView(tabUsers);

        // 初始化右键菜单
        popupMenu.add(itemChange);
        popupMenu.addSeparator();
        popupMenu.add(itemRefresh);
        popupMenu.setPopupSize(100, 70);

        add(btnRefresh, BorderLayout.SOUTH);
    }

    /**
     * 在线用户
     * 
     * @return 在线用户的二维数组
     */
    private Object[][] onlineUsers() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd-hh-ss");
        List<User> list = null;
        try {
            list = UserDAOImpl.INSTANCE.findAll();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Object[][] objNull = new Object[1][];
        if (list != null) {
            Object[][] obj = new Object[list.size()][];

            for (int i = 0; i < list.size(); i++) {
                obj[i] = new Object[7];
                obj[i][0] = list.get(i).getUserId();
                obj[i][1] = list.get(i).getUsername();
            }
            return obj;
        }
        return objNull;
    }

    /**
     * 更新列表
     */
    private void updateTable() {
        tabUsers.setModel(
                new DefaultTableModel(onlineUsers(), new String[] { "ID", "用户名" }));
    }

    /**
     * 监听事件
     */
    private void myListener() {
        // 列表添加鼠标点击监听
        tabUsers.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // 显示右键菜单
                    popupMenu.show(tabUsers, e.getX(), e.getY());
                }
            }
        });

        // 列表添加鼠标移动监听
        tabUsers.addMouseMotionListener(new MouseAdapter() {

            public void mouseMoved(MouseEvent e) {
                // 每次移动清空选中
                tabUsers.removeColumnSelectionInterval(0, tabUsers.getColumnCount() - 1);
                tabUsers.removeRowSelectionInterval(0, tabUsers.getRowCount() - 1);
                // 每次移动选中一行
                if (e.getSource() == tabUsers && tabUsers.getSelectedColumn() == -1
                        && tabUsers.getSelectedRow() == -1) {
                    int row = tabUsers.rowAtPoint(e.getPoint());
                    tabUsers.setRowSelectionInterval(row, row);
                }
            }
        });

        // 修改信息选项点击监听
        itemChange.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 修改窗口， 成功就返回
                User user = null;
                try {
                    user = UserDAOImpl.INSTANCE.findById((String) tabUsers.getValueAt(tabUsers.getSelectedRow(), 0));
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                UpdateUserDialog updateDialog = new UpdateUserDialog();
                updateDialog.setUser(user);
                updateDialog.setModalityType(ModalityType.APPLICATION_MODAL);
                updateDialog.txtName.setText(user.getUsername());
                updateDialog.setUser(user);
                updateDialog.setVisible(true);
                
                // 更新列表
                updateTable();
            }
        });

        // 刷新列表选项点击监听
        itemRefresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });
        
        // 刷新按钮点击监听
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });

    }
}
