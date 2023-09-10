import java.awt.Color;

public class Triangle {
    
    public static void main (String[] args){
        //to test the methods to see if they work
        Triangle A  = new Triangle(10, 10, 10, 10);
        System.out.println(A.calculateArea());
        Rectangle B = new Rectangle(10, 10, 10, 10);
        System.out.println(B.calculateArea());
        Circle C = new Circle(10, 10, 10);
        System.out.println(C.calculateArea());
    }
    
    double xPos;
    double yPos;
    double width;
    double height;
    Color c;

    public Triangle(double xPos, double yPos, double width, double height){
        //contructor method, inits all the things about a triangle and sets them to the variables in the classing using the 'this' statement
        // bottom corners of triangle are the x and y positions
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }
    // for all methods it had void if the method doesnt return antything(set methods) and it has double or string if the method returns one of them (get methods)
    public double calculatePerimeter(){
        return 3 * width;
    }

    public double calculateArea(){
        return 0.5 * height * width;
    }

    public void setColor(Color color){
        this.c = color;
    }

    public void setPos(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public Color getColor(){
        return c;
    }

    public double getXPos(){
        return xPos;
    }

    public double getYPos(){
        return yPos;
    }

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }
    


}
