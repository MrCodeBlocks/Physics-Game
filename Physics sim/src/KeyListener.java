import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter{

    private Helper helper;

    public KeyListener(Helper helper) {
        this.helper = helper;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < helper.objects.size(); i++) {
            GameObject tempObject = helper.objects.get(i);

            if (tempObject.id == Id.Player) {
                // Key events

                if(key == KeyEvent.VK_W && tempObject.isGrounded) {
                    tempObject.velocity.Y = tempObject.speed.Y;
                    //System.out.println(tempObject.speed.Y);
                }
                if(key == KeyEvent.VK_D)  {
                    tempObject.velocity.X = tempObject.speed.X;
                   // System.out.println(tempObject.speed.X);
                }
                if(key == KeyEvent.VK_A) {
                    tempObject.velocity.X = -tempObject.speed.X;
                    //System.out.println(tempObject.speed.X);
                }
                if(key == KeyEvent.VK_ESCAPE) System.exit(1);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < helper.objects.size(); i++) {
            GameObject tempObject = helper.objects.get(i);

            if (tempObject.id == Id.Player) {
                // Key events

                if(key == KeyEvent.VK_W) {
                    tempObject.velocity.Y = 0;
                }
                if(key == KeyEvent.VK_D) {
                    tempObject.velocity.X = 0;
                }
                if(key == KeyEvent.VK_A) {
                    tempObject.velocity.X = 0;
                }
            }
        }
    }
}