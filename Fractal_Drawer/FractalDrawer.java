// FractalDrawer class draws a fractal of a shape indicated by user input
import java.awt.Color;
import java.util.Scanner;

public class FractalDrawer {
    private double totalArea=0;  // member variable for tracking the total area

    public FractalDrawer() {}  // contructor

    //color randomizer
    // Random rand = new Random();
    // int R = (int)(Math.random()*256);
    // int G = (int)(Math.random()*256);
    // int B = (int)(Math.random()*256);
    // Color c = new Color(R, G, B);

    //TODO:
    // drawFractal creates a new Canvas object
    // and determines which shapes to draw a fractal by calling appropriate helper function
    // drawFractal returns the area of the fractal
    public double drawFractal(String type) {           
        while(true){
            if (type.equals("circle")){
                Canvas can = new Canvas(800, 800);  
                drawCircleFractal(200, 400, 400, Color.BLUE, can, 7);
                return totalArea;
            }else if (type.equals("triangle")){
                Canvas can = new Canvas(800, 800);  
                drawTriangleFractal(300, 300, 250, 450, Color.BLUE, can, 7);
                return totalArea;
            }else if (type.equals("rectangle")){
                Canvas can = new Canvas(800, 800);  
                drawRectangleFractal(350, 300, 250, 250, Color.BLUE, can, 7);
                return totalArea;
            } else{
                // goes back into the loop if the input is not whats expected.
                System.out.println("No such a shape, try again");
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter the shape of your choice: circle, triangle or rectangle.");
                String A = scan.nextLine();
                FractalDrawer fratcalDrawer = new FractalDrawer();
                return fratcalDrawer.drawFractal(A);
                

            }
        }
    }

    //TODO:
    // drawTriangleFractal draws a triangle fractal using recursive techniques
    public void drawTriangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level){
        Triangle myTriangle = new Triangle(x, y, width, height);
        myTriangle.setColor(c);
        can.drawShape(myTriangle);
        if (level == 0){
            can.drawShape(myTriangle);
            this.totalArea = myTriangle.calculateArea();
        } else {
            double num1 = myTriangle.getHeight()/2;
            

            Triangle myTriangle1 = new Triangle(myTriangle.getXPos() + num1, myTriangle.getYPos() + num1, myTriangle.getWidth() - num1, myTriangle.getHeight()- num1);
            Triangle myTriangle2 = new Triangle(myTriangle.getXPos() - num1, myTriangle.getYPos() + num1, myTriangle.getWidth() - num1, myTriangle.getHeight()- num1);
            Triangle myTriangle3 = new Triangle(myTriangle.getXPos(), myTriangle.getYPos()-num1, myTriangle.getWidth() - num1, myTriangle.getHeight()- num1);

            int R = (int)(Math.random()*256);
            int G = (int)(Math.random()*256);
            int B = (int)(Math.random()*256);
            Color randomcolor = new Color(R, G, B);

            myTriangle1.setColor(randomcolor);
            myTriangle2.setColor(randomcolor);
            myTriangle3.setColor(randomcolor);

            drawTriangleFractal(myTriangle.getWidth() - num1, myTriangle.getHeight()- num1, myTriangle.getXPos()+num1 *2,  myTriangle.getYPos()+num1, randomcolor, can, level-1);
            drawTriangleFractal(myTriangle.getWidth() - num1, myTriangle.getHeight()- num1, myTriangle.getXPos()-num1,  myTriangle.getYPos()+num1, randomcolor, can, level-1);
            drawTriangleFractal(myTriangle.getWidth() - num1, myTriangle.getHeight()- num1, myTriangle.getXPos() +num1/2,  myTriangle.getYPos()-num1*2, randomcolor, can, level-1);

            this.totalArea += myTriangle1.calculateArea();
            this.totalArea += myTriangle2.calculateArea();
            this.totalArea += myTriangle3.calculateArea();
        }
    }

    // TODO:
    // drawCircleFractal draws a circle fractal using recursive techniques
    public void drawCircleFractal(double radius, double x, double y, Color c, Canvas can, int level) {
        Circle myCircle = new Circle(x, y, radius);
        myCircle.setColor(c);
        can.drawShape(myCircle); //method in canvas class
        if (level == 0){ //levels start at 7 subract one after each time it goes through the loop to get 7 loops
            can.drawShape(myCircle);
            this.totalArea = myCircle.calculateArea();
        } else {
            double num1 = myCircle.getRadius();
            double num2 = num1 / 2;
            //double num3 = num2 / 2;
            
            //making the variables to make the fractal look cool/ random math
            //create the three circles
            Circle myCircle1 = new Circle(myCircle.getXPos() - num2 *2, myCircle.getYPos() + num2 *2 , num1/2);
            Circle myCircle2 = new Circle(myCircle.getXPos() + num2 *2, myCircle.getYPos() + num2 *2, num1/2);
            Circle myCircle3 = new Circle(myCircle.getXPos() + num2 *2,myCircle.getYPos() - num2 *2 , num1/2);
            Circle myCircle4 = new Circle(myCircle.getXPos()  + num2 *2,myCircle.getYPos() - num2 *2 , num1/2);

            //random color
            int R = (int)(Math.random()*256);
            int G = (int)(Math.random()*256);
            int B = (int)(Math.random()*256);
            Color randomcolor = new Color(R, G, B);
            
            myCircle1.setColor(randomcolor);
            myCircle2.setColor(randomcolor);
            myCircle3.setColor(randomcolor);
            myCircle4.setColor(randomcolor);
            //now we draw the circles that we created
            
            //dont need these lines theyre for testing
            // can.drawShape(myCircle1);
            // can.drawShape(myCircle2);
            // can.drawShape(myCircle3);

            drawCircleFractal(num1/2, myCircle.getXPos() - num1 *2 , myCircle.getYPos() + num1 , randomcolor, can, level-1);
            drawCircleFractal(num1/2, myCircle.getXPos() + num1 *2, myCircle.getYPos() + num1, randomcolor, can, level-1);
            drawCircleFractal(num1/2, myCircle.getXPos() + num1 *2, myCircle.getYPos() - num1, randomcolor, can, level-1);
            drawCircleFractal(num1/2, myCircle.getXPos() - num1 *2, myCircle.getYPos() - num1, randomcolor, can, level-1);
            //now that we drew the three circles we need to add the areas of them to the total area variable
            
            //this.totalArea += myCircle.calculateArea();
            this.totalArea += myCircle1.calculateArea();
            this.totalArea += myCircle2.calculateArea();
            this.totalArea += myCircle3.calculateArea();
            this.totalArea += myCircle4.calculateArea();
        }
    }

    //TODO:
    // drawRectangleFractal draws a rectangle fractal using recursive techniques
    public void drawRectangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        Rectangle myRectangle = new Rectangle(x, y, width, height);
        myRectangle.setColor(c);
        can.drawShape(myRectangle);
        if (level == 0){
            can.drawShape(myRectangle);
            this.totalArea = myRectangle.calculateArea();
        } else {
            double num1 = myRectangle.getHeight()/2;
            double num2 = myRectangle.getWidth()/2;


            Rectangle myRectangle1 = new Rectangle(myRectangle.getXPos() + num1 , myRectangle.getYPos() + num1, myRectangle.getWidth() - num2, myRectangle.getHeight() - num1);
            Rectangle myRectangle2 = new Rectangle(myRectangle.getXPos() - num1 , myRectangle.getYPos() + num1, myRectangle.getWidth() - num2, myRectangle.getHeight() - num1);
            Rectangle myRectangle3 = new Rectangle(myRectangle.getXPos() + num1 , myRectangle.getYPos() - num1, myRectangle.getWidth() - num2, myRectangle.getHeight() - num1);
            Rectangle myRectangle4 = new Rectangle(myRectangle.getXPos() - num1 , myRectangle.getYPos() - num1, myRectangle.getWidth() - num2, myRectangle.getHeight() - num1);

            int R = (int)(Math.random()*256);
            int G = (int)(Math.random()*256);
            int B = (int)(Math.random()*256);
            Color randomcolor = new Color(R, G, B);
            
            myRectangle1.setColor(randomcolor);
            myRectangle2.setColor(randomcolor);
            myRectangle3.setColor(randomcolor);
            myRectangle4.setColor(randomcolor);

            drawRectangleFractal(myRectangle.getWidth() - num2, myRectangle.getHeight() - num1, myRectangle.getXPos() + num1 *2, myRectangle.getYPos() + num1 * 2, randomcolor, can, level - 1);
            drawRectangleFractal(myRectangle.getWidth() - num2, myRectangle.getHeight() - num1, myRectangle.getXPos() - num1 *2, myRectangle.getYPos() + num1 * 2, randomcolor, can, level - 1);
            drawRectangleFractal(myRectangle.getWidth() - num2, myRectangle.getHeight() - num1, myRectangle.getXPos() - num1 *2, myRectangle.getYPos() - num1 * 2, randomcolor, can, level - 1);
            drawRectangleFractal(myRectangle.getWidth() - num2, myRectangle.getHeight() - num1, myRectangle.getXPos() + num1 *2, myRectangle.getYPos() - num1 * 2, randomcolor, can, level - 1);

            this.totalArea += myRectangle1.calculateArea();
            this.totalArea += myRectangle2.calculateArea();
            this.totalArea += myRectangle3.calculateArea();
            this.totalArea += myRectangle4.calculateArea();
        }
    }

    //TODO:
    // main should ask user for shape input, and then draw the corresponding fractal.
    // should print area of fractal
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the shape of your choice: circle, triangle or rectangle.");
        String A = scan.nextLine();
        FractalDrawer fratcalDrawer = new FractalDrawer();
        //creates a drawer object
        //next we need to actually draw the fractals with another method and it actually returns the total area also
        double final1 = fratcalDrawer.drawFractal(A);
        System.out.println("The total area is: " + final1);
    }
}
