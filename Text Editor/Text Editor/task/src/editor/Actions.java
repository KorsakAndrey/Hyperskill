package editor;


import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static editor.InterfaceCreator.*;

public class Actions {
    public static int index = 0;
    public static int size = 0;

    public static void load() {
        //Load text
        fileChooser.setApproveButtonText("Open");
        fileChooser.setDialogTitle("Open");
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            if(selectedFile.exists()) {
                try {
                    textArea.setText(Files.readString(selectedFile.toPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                textArea.setText("");
            }
        }
    }

    public static void save() {
        //Save text
        fileChooser.setApproveButtonText("Save");
        fileChooser.setDialogTitle("Save");
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            try {
                if(selectedFile.exists()){
                    if(!selectedFile.delete()) {
                        throw new IOException("Delete error");
                    }
                }

                CharArrayWriter dataWriter = new CharArrayWriter();
                Writer writer = new FileWriter(selectedFile);
                dataWriter.write(textArea.getText());
                dataWriter.writeTo(writer);
                dataWriter.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void search() {
        new Thread(() -> {      //RegEx
            if(useRegEx.isSelected()){
                Pattern pattern = Pattern.compile(searchField.getText());
                Matcher matcher = pattern.matcher(textArea.getText());
                if(matcher.find()){
                    index = matcher.start();
                    size = matcher.end() - index;
                } else {
                    index = 0;
                    size = 0;
                }
            }else {     //NoRegEx
                index = textArea.getText().indexOf(searchField.getText());
                if(index >= 0) {
                    size = searchField.getText().length();
                } else {
                    index = 0;
                    size = 0;
                }
            }
            if(size != 0) {
                textArea.setCaretPosition(index + size);
                textArea.select(index, index + size);
                textArea.grabFocus();
            }
        }).start();
    }

    public static void nextSearch(){
        new Thread(() -> {
            if(size != 0){      //RegEx
                if(useRegEx.isSelected()) {
                    int temp = index + 1;
                    Pattern pattern = Pattern.compile(searchField.getText());
                    Matcher matcher = pattern.matcher(textArea.getText().substring(temp));
                    if(matcher.find()){
                        index = matcher.start() + temp;
                        size = matcher.end() - index + temp;
                    } else {
                        index = 0;
                        size = 0;
                    }
                } else {        //NoRegEx
                    index = textArea.getText().indexOf(searchField.getText(), index + 1);
                    if(index > 0) {
                        size = searchField.getText().length();
                    } else {
                        index = 0;
                        size = 0;
                    }
                }
                if(size != 0) {
                    textArea.setCaretPosition(index + size);
                    textArea.select(index, index + size);
                    textArea.grabFocus();
                }
            }
        }).start();
    }

    public static void prevSearch() {
        new Thread(() -> {
            if(size != 0) {     //RegEx
                if (useRegEx.isSelected()) {
                    boolean firstWrong = false;

                    int end = index + size - 1;
                    Pattern pattern = Pattern.compile(searchField.getText());
                    Matcher matcher;
                    index = end;
                    while (index > 0) {
                        index--;
                        matcher = pattern.matcher(textArea.getText().substring(index, end));
                        if (matcher.find()) {
                            size = matcher.end();
                            firstWrong = false;
                            break;
                        } else {
                            size = 0;
                            firstWrong = true;
                        }
                    }
                    if (firstWrong) {
                        index = textArea.getText().length();
                        end = index;

                        while (index > 0) {
                            index--;
                            matcher = pattern.matcher(textArea.getText().substring(index, end));
                            if (matcher.find()) {
                                size = matcher.end();
                                break;
                            } else {
                                size = 0;
                            }
                        }
                    }
                } else {        //NoRegEx
                    index = textArea.getText().substring(0, index).lastIndexOf(searchField.getText());

                    if (index >= 0) {
                        size = searchField.getText().length();
                    } else {
                        index = textArea.getText().lastIndexOf(searchField.getText());
                        if (index >= 0) {
                            size = searchField.getText().length();
                        } else {
                            index = 0;
                            size = 0;
                        }
                    }
                }
                if (size != 0) {
                    textArea.setCaretPosition(index + size);
                    textArea.select(index, index + size);
                    textArea.grabFocus();
                }
            }
        }).start();
    }
}
