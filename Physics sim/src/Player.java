import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player extends GameObject{

    private float jumpForce, moveSpeed, trueWidth, trueHeight;

    public Player(Id id, Vector2 p, String Name) {
        super(id, p);
        jumpForce = 20.f;
        moveSpeed = 10.f;
        speed = new Vector2(moveSpeed, -jumpForce);
        acceleration = new Vector2(0.f, 1.f);
        trueWidth = Game.WIDTH*2-40;
        trueHeight = Game.HEIGHT*2-70;
    }

    @Override
    public void tick() {

        isGrounded = (position.Y >= trueHeight);

        velocity.add(acceleration.scale(Game.deltaTime));
        position.add(position.add(velocity.scale(Game.deltaTime)));

        Vector2 posNextFrame = position.add(velocity.scale(Game.deltaTime));
        Vector2 velNextFrame = velocity.add(acceleration.scale(Game.deltaTime));

        position = posNextFrame;
        velocity = velNextFrame;

        position.X = Game.clamp(position.X, 0, trueWidth);
        position.Y = Game.clamp(position.Y, 0, trueHeight);
        System.out.println(velocity.X);
        //System.out.println(velocity.X + ", " + velocity.Y);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(235, 64, 52));
        g.fillRect((int)position.X, (int)position.Y, 40, 70);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)position.X, (int)position.Y, 40,70);
    }
}
