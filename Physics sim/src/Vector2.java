public class Vector2 {

    public float X,Y, magnitude, direction;

    public Vector2(float x, float y) {
        this.X = x;
        this.Y = y;
        magnitude = (float)Math.pow((X*X)+(Y*Y), 0.5);
        direction = (float)Math.atan2(Y, X);
    }

    public Vector2 add(Vector2 vec) {
        float mx = this.X + vec.X;
        float my = this.Y + vec.Y;
        return new Vector2(mx, my);
    }

    public Vector2 scale(float u) {
        float mx = this.X * u;
        float my = this.Y * u;
        return new Vector2(mx, my);
    }

    public void normalize() {
        X /= magnitude;
        Y /= magnitude;
        magnitude /= magnitude;
    }
}
