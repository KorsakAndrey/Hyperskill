package editor;

import javax.swing.*;

import java.awt.*;

import static editor.InterfaceCreator.*;

public class TextEditor extends JFrame {
    public static JFrame textEditor = null;

    public  TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("TextEditor");
        setBounds(500, 200, 500, 300);
        setLayout(new BorderLayout());
        add(getTopPanel(), BorderLayout.NORTH);
        add(getScrollPanel(), BorderLayout.CENTER);
        JLayeredPane layeredPane = this.getLayeredPane();
        layeredPane.add(getFileChooser(), 1);
        setJMenuBar(getBar());
        textEditor = this;
        setVisible(true);

    }
}
