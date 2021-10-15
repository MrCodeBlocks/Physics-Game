import java.awt.*;

public abstract class GameObject {

    public Vector2 position, velocity, acceleration, speed;
    public Id id;
    public boolean isGrounded;

    public GameObject(Id id, Vector2 p) {
        isGrounded = false;
        this.id = id;
        position = p;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0,0);
        speed = new Vector2(0, 0);
    }

    public abstract void tick();
    public abstract void render(Graphics2D g);
    public abstract Rectangle getBounds();
}
