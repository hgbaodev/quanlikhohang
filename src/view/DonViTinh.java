package view;

import java.awt.*;
import javax.swing.*;

public class DonViTinh extends JPanel {

    private void initComponent() {
        this.setBackground(new Color(230,244,204));
        this.setBounds(0, 200, 300, 1200);
        this.setLayout(new FlowLayout(1, 0, 0));
        this.setOpaque(true);

                
        JLabel lbl = new JLabel("ĐƠN VỊ TÍNH");
        lbl.setPreferredSize(new Dimension(100,850));
        this.add(lbl);
    }

    public DonViTinh() {
        initComponent();
    }

}