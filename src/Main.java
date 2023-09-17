import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Windows Classic is not available, you can set the GUI to another look and feel.
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CipherCesar().setVisible(true);
            }
        });
    }
}