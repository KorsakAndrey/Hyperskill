package editor;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

public class InterfaceCreator {
    public static File selectedFile;
    public static JTextArea textArea;
    public static JTextField searchField;
    public static JButton saveButton;
    public static JButton openButton;
    public static JCheckBox useRegEx;
    public static JFileChooser fileChooser;

    public static JMenuBar getBar() {
        //Menu
        JMenu menu = new JMenu("File");
        menu.setName("MenuFile");
        JMenuItem load = new JMenuItem("Load");
        load.setName("MenuOpen");
        load.addActionListener(actionEvent -> Actions.load());
        JMenuItem save = new JMenuItem("Save");
        save.setName("MenuSave");
        save.addActionListener(actionEvent -> Actions.save());
        JMenuItem exit = new JMenuItem("Exit");
        exit.setName("MenuExit");
        exit.addActionListener(actionEvent -> TextEditor.textEditor.dispose());
        menu.add(load);
        menu.add(save);
        menu.add(exit);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        menuBar.add(getSearch());
        return menuBar;
    }

    public static JMenu getSearch() {
        JMenu search = new JMenu("Search");
        search.setName("MenuSearch");
        JMenuItem startSearch = new JMenuItem("StartSearch");
        startSearch.setName("MenuStartSearch");
        startSearch.addActionListener(actionEvent -> Actions.search());
        JMenuItem previousMatch = new JMenuItem("PreviousMatch");
        previousMatch.setName("MenuPreviousMatch");
        previousMatch.addActionListener(actionEvent -> Actions.prevSearch());
        JMenuItem nextMatch = new JMenuItem("NextMatch");
        nextMatch.setName("MenuNextMatch");
        nextMatch.addActionListener(actionEvent -> Actions.nextSearch());
        JMenuItem useRegExp = new JMenuItem("UseRegExp");
        useRegExp.setName("MenuUseRegExp");
        useRegExp.addActionListener(actionEvent -> useRegEx.doClick());
        search.add(startSearch);
        search.add(previousMatch);
        search.add(nextMatch);
        search.add(useRegExp);
        return search;
    }

    public static JPanel getTopPanel() {
        //Filename field
        searchField = new JTextField();
        Dimension dimension = new Dimension(120, 27);
        searchField.setPreferredSize(dimension);
        searchField.setName("SearchField");

        //Save button
        saveButton = new JButton(new ImageIcon("C:\\Users\\Andrey\\IdeaProjects\\Text Editor\\Text Editor" +
                "\\task\\src\\editor\\icon\\save.png"));
        saveButton.setName("SaveButton");
        saveButton.setPreferredSize(new Dimension(24, 24));
        saveButton.addActionListener(actionEvent -> Actions.save());

        //Load button
        openButton = new JButton(new ImageIcon("C:\\Users\\Andrey\\IdeaProjects\\Text Editor\\Text Editor" +
                "\\task\\src\\editor\\icon\\load.png"));
        openButton.setName("OpenButton");
        openButton.setPreferredSize(new Dimension(24,24));
        openButton.addActionListener(actionEvent -> Actions.load());

        //Start search button
        JButton startSearchButton = new JButton("F");
        startSearchButton.setName("StartSearchButton");
        startSearchButton.setPreferredSize(new Dimension(42, 24));
        startSearchButton.addActionListener(actionEvent -> Actions.search());

        //Previous search button
        JButton previousMatchButton = new JButton("<");
        previousMatchButton.setName("PreviousMatchButton");
        previousMatchButton.setPreferredSize(new Dimension(42, 24));
        previousMatchButton.addActionListener(actionEvent -> Actions.prevSearch());

        //Next search button
        JButton nextMatchButton = new JButton(">");
        nextMatchButton.setName("NextMatchButton");
        nextMatchButton.setPreferredSize(new Dimension(42, 24));
        nextMatchButton.addActionListener(actionEvent -> Actions.nextSearch());

        //UseRegEx box
        useRegEx = new JCheckBox("UseRegEx");
        useRegEx.setName("UseRegExCheckbox");

        //Top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(openButton);
        topPanel.add(saveButton);
        topPanel.add(searchField);
        topPanel.add(startSearchButton);
        topPanel.add(previousMatchButton);
        topPanel.add(nextMatchButton);
        topPanel.add(useRegEx);

        return topPanel;
    }

    public static JScrollPane getScrollPanel() {
        //Text area
        textArea = new JTextArea();
        textArea.setName("TextArea");
        //Scroll panel
        JScrollPane scrollPanel = new JScrollPane(textArea);
        scrollPanel.setName("ScrollPane");
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scrollPanel;
    }

    public static JFileChooser getFileChooser() {
        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setName("FileChooser");
        fileChooser.setCurrentDirectory(new File("C:\\Users\\Andrey\\IdeaProjects\\Text Editor\\Text Editor\\task"));
        return fileChooser;
    }
}
