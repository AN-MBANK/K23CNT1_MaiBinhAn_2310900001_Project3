package K23CNT1_.Mba_day02.dependency_injection;

// Lớp sử dụng Shape (được inject qua constructor)
public class DrawShape {
    private Shape shape;

    public DrawShape(Shape shape) {
        this.shape = shape;
    }

    public void draw() {
        shape.draw();
    }

    public static void main(String[] args) {
        DrawShape drawShape = new DrawShape(new CircleShape());
        drawShape.draw();

        drawShape = new DrawShape(new RectangleShape());
        drawShape.draw();
    }
}
