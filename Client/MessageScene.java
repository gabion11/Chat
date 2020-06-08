import javax.swing.*;
import java.awt.*;

public class MessageScene extends JPanel {
    final MainFrame frame;

    String receiver;


    public MessageScene(MainFrame frame, String receiver) {
        this.frame = frame;
        this.receiver = receiver;
        JScrollBar scroll;
        scroll = new JScrollBar();
        init();
    }
    private void init(){
            Box box = Box.createVerticalBox();
            box.setPreferredSize(new Dimension(400,400));
            String request = "GetMessages";
            frame.writeToServer(request);
            frame.writeToServer(receiver);

            String size = frame.readFromServer();



                JPanel jPanel1 = new JPanel();

                jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.PAGE_AXIS));
                JLabel labelUserReceiver = new JLabel(frame.curentUser);
                jPanel1.add(labelUserReceiver);


                JPanel jPanel = new JPanel();
                jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
                JLabel labelUserCurrent = new JLabel(frame.curentReceiver);
                jPanel.add(labelUserCurrent);

                for (int i = 0; i < Integer.parseInt(size); i++) {

                    String username = frame.readFromServer();
                    String content = frame.readFromServer();

                    if (username.equals(frame.curentUser)) {

                        JLabel label1 = new JLabel("              ");
                        JLabel label = new JLabel(content);

                        jPanel1.add(label1);
                        jPanel.add(label);

                    } else {
                        JLabel label1 = new JLabel("                  ");
                        JLabel label = new JLabel(content);

                        jPanel.add(label1);
                        jPanel1.add(label);

                    }
                }
                    add(jPanel1,BorderLayout.WEST);
                    add(jPanel,BorderLayout.EAST);










    }

}
