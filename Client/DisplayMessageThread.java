public class DisplayMessageThread implements Runnable {
    final MainFrame frame;
    String username;
    public DisplayMessageThread(MainFrame frame,String username){
        this.frame=frame;
        this.username=username;
    }
    public void run(){
        System.out.println("entered");
    while(true) {

        frame.loadMessage(frame.curentReceiver);
        try {
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    }
}
