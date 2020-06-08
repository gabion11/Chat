import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainFrame extends JFrame {
    String serverAddress = "127.0.0.1"; // The server's IP address
    int PORT = 8100; // The server's port
    Socket socket = new Socket(serverAddress, PORT);
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    Thread t;
    String curentReceiver;
    LoginScene loginScene;
    UsersScene usersScene;
    ConversationScene conversationScene;
    MessageScene messageSceneScene;

    String curentUser;
    public MainFrame() throws IOException {
        super("Chat");
        init();
    }


    private void init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));
      //  usersScene = new UsersScene(this);
       // this.add(usersScene,BorderLayout.CENTER);
         loginScene = new LoginScene(this);
         add(loginScene,BorderLayout.CENTER);
        pack();
    }

    public void writeToServer(String message) {
        out.println(message);
        out.flush();
    }

    public String readFromServer() {
        String messge = "";
        try {
            messge = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messge;
    }

    public synchronized void backToUsers(){
        t.stop();

        this.remove(messageSceneScene);
        this.remove(conversationScene);
        usersScene = new UsersScene(this);
        this.repaint();
        this.add(usersScene,BorderLayout.CENTER);
        this.pack();
    }
    public void loadUserScene() {

        this.remove(loginScene);
        this.repaint();
        usersScene = new UsersScene(this);
        this.add(usersScene,BorderLayout.CENTER);
        this.pack();
    }

    public void loadConversationScene(String username) {
        System.out.println(curentUser);

            this.remove(usersScene);
            this.repaint();
            conversationScene = new ConversationScene(this,username);
            this.add(conversationScene,BorderLayout.SOUTH);
            messageSceneScene = new MessageScene(this,username);
            this.add(messageSceneScene,BorderLayout.NORTH);

        DisplayMessageThread displayMessageThread = new DisplayMessageThread(this,username);
         t = new Thread(displayMessageThread);
        t.start();

        this.pack();
    }
    public void loadMessage(String username){
        remove(messageSceneScene);
        messageSceneScene = new MessageScene(this,username);

        this.add(messageSceneScene,BorderLayout.NORTH);

        pack();

    }


    public void setCurentUser(String username){
        curentUser = username;
    }


    public void loginScene() {
        this.remove(usersScene);
        loginScene = new LoginScene(this);
        add(loginScene,BorderLayout.CENTER);
        pack();
    }
}
