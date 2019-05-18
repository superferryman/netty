package com.superferryman.server.ui;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.superferryman.protocol.common.StringConst;

import java.awt.*;

/**
 * 服务端主窗体类
 * 
 * @author RuanYaofeng, VisonSun
 * @date 2018-04-18 10:01
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    static{
        // 加载皮肤
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            // TODO: handle exception
        }

        // 全局字体
        UIManager.put("Button.font", new Font("微软雅黑", Font.PLAIN, 14));
        UIManager.put("Label.font", new Font("微软雅黑", Font.PLAIN, 14));
        UIManager.put("Checkbox.font", new Font("微软雅黑", Font.PLAIN, 14));
    }

    /**
     * 构造主窗体
     */
    public MainFrame() {
        setTitle(StringConst.CRAZYCHAT_SERVER);
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.add(new ServerStatusPanel());
        tabbedPane.add(new OnlineUsersPanel());
        tabbedPane.add(new AllUsersPanel());

        tabbedPane.setTitleAt(0, StringConst.SERVER_STATUS);
        tabbedPane.setTitleAt(1, StringConst.ONLINE_USERS);
        tabbedPane.setTitleAt(2, StringConst.ALL_USERS);
    }

}
