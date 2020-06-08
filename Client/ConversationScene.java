import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConversationScene extends JPanel {
    final MainFrame frame;
    String username;

    JLabel inputText = new JLabel("Enter your message");
    JTextField inputField;
    JButton send;
    JButton back;
    public ConversationScene(MainFrame frame,String username) {
        this.frame = frame;
        this.username= username;
        init();
    }

    private void  init(){
        Box box = Box.createHorizontalBox();
        back = new JButton("Back");
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(200,20));
        send = new JButton("Send");
        box.add(back);
        box.add(inputText);
        box.add(inputField);
        box.add(send);
        add(box);
        back.addActionListener(this::backToUsers);
        send.addActionListener(this::sentMessage);
    }

    private void backToUsers(ActionEvent actionEvent) {
        frame.backToUsers();
    }

    private void sentMessage(ActionEvent actionEvent) {

        if(!(inputField.getText().equals(""))){
            String raspuns = "AddMessage";
            frame.writeToServer(raspuns);
            frame.writeToServer(username);
            frame.writeToServer(inputField.getText());
            inputField.setText("");
            frame.loadMessage(username);
        }
    }
}
