package lk.checkinn.util;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AppIconUtil {
    private static Image appIcon;
    
    static {
        try{
            URL iconPath = AppIconUtil.class.getResource("/lk/checkinn/img/cube.png");
//            if(iconPath !=null) {
                ImageIcon icon = new ImageIcon(iconPath);
                AppIconUtil.appIcon = icon.getImage();
//            }
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "INVALID IOCN PATH");
        }
        
    }
    
    public static void applyIcon(JFrame frame){
        if(frame != null) {
            frame.setIconImage(appIcon);
        }
    }
}