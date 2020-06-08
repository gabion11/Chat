import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginScene extends JPanel {
    final MainFrame frame;

    JTextField username;
    JPasswordField pass;
    JLabel error;

    public LoginScene(MainFrame frame) {
        this.frame=frame;
        init();
    }
    private void init(){

        Box box = Box.createVerticalBox();


        JLabel usernameLabel = new JLabel("Username");
        username = new JTextField();

        JLabel passLabel = new JLabel("Password");
        pass = new JPasswordField();

        JButton login = new JButton("Login");
        JButton signIn = new JButton("Sign In");
        error = new JLabel();
        box.add(usernameLabel);
        box.add(username);
        box.add(passLabel);
        box.add(pass);
        box.add(login);
        box.add(signIn);
        box.add(error);
        this.add(box);

        frame.add(this);

        login.addActionListener(this::actionPerformed);
        signIn.addActionListener(this::actionPerformed);

    }
    public void actionPerformed(ActionEvent evt) {
        String s = evt.getActionCommand();
        String message = "";
        if (s.equals(("Login"))) {
            message = "Login";
        } else if (s.equals(("Sign In"))) {
            message = "SignIn_";
        }
        frame.writeToServer(message);
        frame.writeToServer(username.getText());
        frame.writeToServer(pass.getText());

        String response = frame.readFromServer();

        if (response.equals("OK")) {
            frame.setCurentUser(username.getText());
            frame.loadUserScene();
        } else {
            error.setText("Wrong username or password");
            repaint();
        }

    }

}
