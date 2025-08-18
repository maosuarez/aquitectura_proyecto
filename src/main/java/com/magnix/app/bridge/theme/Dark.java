package com.magnix.app.bridge.theme;

import com.magnix.app.bridge.IPostBridge;
import com.magnix.app.bridge.Post;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

public class Dark implements IPostBridge {
    @Override
    public void render(Post post) {
        JTextArea area = new JTextArea(post.getFormattedContent());
        area.setFont(new Font("SansSerif", Font.PLAIN, 16));
        area.setBackground(new Color(30, 30, 30));
        area.setForeground(new Color(220, 220, 220));
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400, 200));

        JOptionPane.showMessageDialog(null, scroll, "🌙 Publicación (Tema Oscuro)", JOptionPane.INFORMATION_MESSAGE);
    }
}
