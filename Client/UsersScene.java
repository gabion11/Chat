import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class UsersScene extends JPanel {
   final MainFrame frame;
    JList jList;
    JButton jButton;
    public UsersScene(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init(){
        String message = "GetUsers";
        frame.out.println(message);
        frame.out.flush();
        ArrayList<String> arrayList = new ArrayList<>();



        String size = frame.readFromServer();
        System.out.println(frame.curentUser);
        for(int i = 0; i<Integer.parseInt(size);i++){
            String username = frame.readFromServer();

            if(!username.equals(frame.curentUser))
            arrayList.add(username);
        }

        String[] strings =  arrayList.toArray(new String[0]);

        jList =new JList(strings);
        jButton = new JButton("Log out");
        jList.setFont(new Font("Areal", Font.BOLD,20));
        this.add(jButton,BorderLayout.SOUTH);
        this.add(jList,BorderLayout.CENTER);

        this.repaint();
        jButton.addActionListener(this::loadLogin);
        jList.addListSelectionListener(this::selectedUser);

    }

    private void loadLogin(ActionEvent actionEvent) {
        frame.loginScene();
    }

    private void selectedUser(ListSelectionEvent listSelectionEvent) {
        if(listSelectionEvent.getValueIsAdjusting()) {
            String username = (String) jList.getSelectedValue();
            frame.curentReceiver=username;
            frame.loadConversationScene(username);
        }
    }
}
