import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    public static final int HEIGHT = 240;
    public static final int WIDTH = HEIGHT * 37/20;
    public static final String NAME = "Untitled";
    public static final Helper helper = new Helper();
    public static float deltaTime = 0.f;
    private final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private boolean running = false;

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    public void run() {
        long lastime = System.nanoTime();
        double AmountOfTicks = 60;
        double ns = 1000000000 / AmountOfTicks;
        double delta = 0;
        int frames = 0;
        double time = System.currentTimeMillis();

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastime) / ns;
            lastime = now;
            deltaTime = (float)delta;

            if(delta >= 1) {
                runStep();
                frames++;
                delta--;
                if(System.currentTimeMillis() - time >= 1000) {
                    System.out.println("fps:" + frames);
                    time += 1000;
                    frames = 0;
                }
            }
        }
    }

    public void runStep() {
        tick();
        render();
    }

    public void tick() {
        for(int i=0; i <helper.objects.size(); i++) {
            helper.objects.get(i).tick();
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            requestFocus();
            return;
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.drawImage(image, 0, 0,  null);
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(hints);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH*2, HEIGHT*2);
        // draw graphics here
        for(int i=0; i <helper.objects.size(); i++) {
            helper.objects.get(i).render(g);
        }
        g.dispose();
        bs.show();
    }

    public static void main(String args[]) {
        Game game = new Game();
        game.setMaximumSize(new Dimension(WIDTH*2, HEIGHT*2));
        game.setMinimumSize(new Dimension(WIDTH*2, HEIGHT*2));
        game.setPreferredSize(new Dimension(WIDTH*2, HEIGHT*2));
        game.addKeyListener(new KeyListener(helper));
        JFrame frame = new JFrame(Game.NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        helper.add(new Player(Id.Player, new Vector2(WIDTH/2, HEIGHT/2), "Jerry"));
        game.start();
    }

    public static float clamp(float var, float min, float max) {
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }
}
