import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CipherCesar extends JFrame {

    static JScrollPane scrollPane1;
    static JScrollPane scrollPane2;
    private final JTextArea textArea1 = new JTextArea();
    private final JTextArea textArea2 = new JTextArea();
    private final JTextField txtField = new JTextField();

    public CipherCesar() {

        super("Шифрование Цезарь");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setLayout(new FlowLayout());
        int w = 1000, h = 800;
        this.setBounds(dimension.width / 2 - w / 2, dimension.height / 2 - h / 2, w, h);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        JLabel labelCaption1 = new JLabel();
        labelCaption1.setText("Введите текст для шифровки или дешифровки текста:");
        Font font = new Font("Times New Roman", Font.PLAIN, 22);
        labelCaption1.setFont(font);
        labelCaption1.setPreferredSize(new Dimension(w - 30, 30));

        textArea1.setFont(font);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);

        scrollPane1 = new JScrollPane(textArea1);
        scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(new Dimension(w - 120, 300));

        txtField.setFont(font);
        txtField.setPreferredSize(new Dimension(100, 50));

        JButton btnClear = new JButton("Очистить");
        btnClear.setPreferredSize(new Dimension(250, 50));
        btnClear.setFont(font);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea2.setText("");
                textArea1.setText("");
                txtField.setText("");
            }
        });

        JButton btnCipher = new JButton();
        btnCipher.setFont(font);
        btnCipher.setText("Шифрование");
        btnCipher.setPreferredSize(new Dimension(250, 50));
        btnCipher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int k = Integer.parseInt(txtField.getText());
                String s = CaesarIn(k, textArea1.getText());

                textArea2.setText(s);
            }
        });

        JButton btnDecipher = new JButton();
        btnDecipher.setFont(font);
        btnDecipher.setText("Дешифрование");
        btnDecipher.setPreferredSize(new Dimension(250, 50));
        btnDecipher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int k = Integer.parseInt(txtField.getText());
                String s = CaesarOut(k, textArea1.getText());
                s = s.replace("\b", "я");
                textArea2.setText(s);
            }
        });

        JLabel labelCaption2 = new JLabel();
        labelCaption2.setText("Результат введённого текста:");
        labelCaption2.setFont(font);
        labelCaption2.setPreferredSize(new Dimension(w - 30, 50));

        textArea2.setFont(font);
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);

        scrollPane2 = new JScrollPane(textArea2);
        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setPreferredSize(new Dimension(w - 120, 300));

        placeHolders();
        this.add(btnCipher);
        this.add(btnClear);
        this.add(btnDecipher);
        this.add(txtField);
        this.add(labelCaption1);
        this.add(scrollPane1);
        this.add(labelCaption2);
        this.add(scrollPane2);
        this.revalidate();
    }

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

    void placeHolders() {
        placeHolderTxt(txtField);
    }

    void placeHolderTxt(JTextField txt) {
        if (txt.getText().length() == 0) {
            txt.setText("Сдвиг");
            txt.setForeground(new Color(150, 150, 150));
        }

        txt.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (txt.getText().equals("Сдвиг")) {
                    txt.setText("");
                    txt.setForeground(new Color(50, 50, 50));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt.getText().length() == 0) {
                    txt.setText("Сдвиг");
                    txt.setForeground(new Color(150, 150, 150));
                }
            }
        });
    }

    String CaesarIn(int temp, String s) {
        char buffer;
        StringBuilder c = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            buffer = s.charAt(i);
            buffer += (temp % 12345);
            c.append(buffer);
        }
        return c.toString();
    }

    String CaesarOut(int temp, String s) {
        char buffer;
        StringBuilder c = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '™')
                c.append('-');
            else if ((int) s.charAt(i) == '\t')
                c.append('я');
            else if ((int) s.charAt(i) == '\b')
                c.append('я');
            else {
                buffer = s.charAt(i);
                buffer -= (temp % 12345);
                c.append(buffer);
            }
        }

        return c.toString();
    }
}
