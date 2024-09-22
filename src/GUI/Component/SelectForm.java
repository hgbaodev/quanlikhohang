/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tran Nhat Sinh
 */
public class SelectForm extends JPanel{
    private JLabel lblTitle;
    public JComboBox cbb;
    
    public SelectForm(String title, String[] obj) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(0, 10, 5, 10));
        
        lblTitle = new JLabel(title);
        cbb = new JComboBox(obj);
        
        this.add(lblTitle);
        this.add(cbb);
    }
    
    public void setArr(String[] obj) {
        this.cbb.setModel(new DefaultComboBoxModel(obj));
    }
    
    public String getValue() {
        return (String) cbb.getSelectedItem();
    }
    
    public Object getSelectedItem() {
        return cbb.getSelectedItem();
    }
    
    public int getSelectedIndex() {
        return cbb.getSelectedIndex();
    }
    
    public void setSelectedIndex(int i) {
        cbb.setSelectedIndex(i);
    }
    
    public void setSelectedItem(Object a) {
        cbb.setSelectedItem(a);
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public void setLblTitle(JLabel lblTitle) {
        this.lblTitle = lblTitle;
    }

    public JComboBox getCbb() {
        return cbb;
    }

    public void setCbb(JComboBox cbb) {
        this.cbb = cbb;
    }
    
    public void setDisable() {
        cbb.setEnabled(false);
    }
    
    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        cbb.setEnabled(b);
    }
    
}
