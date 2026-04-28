/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.checkinn.component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;

/**
 *
 * @author DELL
 */
public class RoundButton extends JButton{
    
    public RoundButton() {
        init();
    }
    
    private void init() {
        putClientProperty(FlatClientProperties.STYLE, "arc:50");
    }
}
