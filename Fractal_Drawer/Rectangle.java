
import java.awt.Color;

public class Rectangle {
    double xPos;
    double yPos;
    double height;
    double width;
    Color c;

    public Rectangle(double xPos, double yPos, double width, double height){
        //upper corners the positions are located
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        //default color is black
    }

    public double calculatePerimeter(){
        return 2 * (width + height);
    }

    public double calculateArea(){
        return width * height;
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

