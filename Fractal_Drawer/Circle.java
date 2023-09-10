
import java.awt.Color;


public class Circle{

    double xPos;
    double yPos;
    double radius;
    Color  c;

    public Circle(double xPos, double yPos, double rad){
        //initiating variables that are made in the contructior
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = rad;
        //so calling the objectCreated.x or .y would give the value of whats said in CircleClass(x,x,x)
    }
    
    public double calculatePerimeter(){
        //return the circumference
        return  Math.PI * 2 * radius;
    }

    public double calculateArea(){
        //retruns the area
        return Math.PI * radius * radius;
    }

    public void setColor(Color color){
        this.c = color;
    }

    public void setPos(double xPos, double yPos){ 
        this.xPos = xPos;
        this.yPos = yPos;
    }
    public void setRadius(double radius){
        this.radius = radius;
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

    public double getRadius(){
        return radius;
    }
}