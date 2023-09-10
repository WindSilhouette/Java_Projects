import java.util.Random;
import java.util.Scanner;

public class MyMaze{
    static Cell[][] maze;
    int startRow;
    int endRow;

    private static int[][] neighbor = new int[4][2]; // updates for the neighbor

    public MyMaze(int rows, int cols, int startRow, int endRow) {
        maze = new Cell[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                maze[i][j] = new Cell();
            }
        }
        this.startRow = startRow;
        this.endRow = endRow;
    }

    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int rows, int cols, int startRow, int endRow) {
        MyMaze maze = new MyMaze(rows, cols, startRow, endRow);
        Stack1Gen<int[]> s = new Stack1Gen<>();
        s.push(new int[]{startRow, 0});
        maze.maze[startRow][0].setVisited(true);
        maze.maze[endRow][cols-1].setRight(false);
        int currCols = 0;
        int currRow = startRow;
        while(!s.isEmpty()){
            int[] top = s.top();

            openCell(currCols, currRow);
            if(neighbor[0][0] == 69){ //if no open spaces it will pop
                s.pop();
                currRow = top[0];
                currCols = top[1];
            }else{
                s.push(new int[]{neighbor[0][0], neighbor[0][1]});
                maze.maze[neighbor[0][0]][neighbor[0][1]].setVisited(true);
                int newCols = neighbor[0][1];
                int newRow = neighbor[0][0];
                if (neighbor[1][1] == 0) { //up case
                    maze.maze[newRow][newCols].setBottom(false);
                }
                if (neighbor[1][1] == 1) { //right case
                    maze.maze[currRow][currCols].setRight(false);
                }
                if (neighbor[1][1] == 2) { //down case
                    maze.maze[currRow][currCols].setBottom(false);
                }
                if (neighbor[1][1] == 3) { //left case
                    maze.maze[newRow][newCols].setRight(false);
                }
                currCols = neighbor[0][1];
                currRow = neighbor[0][0];
            }
            //increment currcols and currrow to newcols and newrow
        }
        for (int i = 0; i < maze.maze.length; i++) {
            for (int j = 0; j <maze.maze[0].length; j++){
                maze.maze[i][j].setVisited(false);
            }
        }
        return maze;
    }

    public static void openCell(int col, int row){ //helper function, takes in current row and col, return array of possible coordinates
        int[][] cords = new int[4][2];
        int open = 0;
        if(row - 1 >= 0 && !maze[row- 1][col].getVisited()) { //top case, cords array filled with coordinates of open cell or 69
            cords[0][0] = row - 1;
            cords[0][1] = col;
            open = 1;
        }else {
            cords[0][0]=69;
            cords[0][1]=69;
        }
        if(col + 1 < maze[0].length && !maze[row][col + 1].getVisited()) { //right case DONE
            cords[1][0] = row;
            cords[1][1] = col + 1;
            open = 1;
        }else{
            cords[1][0] = 69;
            cords[1][1] = 69;
        }
        if(row + 1 < maze.length && !maze[row + 1][col].getVisited()) { //down case
            cords[2][0] = row + 1;
            cords[2][1] = col;
            open = 1;
        }else{
            cords[2][0] = 69;
            cords[2][1] = 69;
        }
        if(col - 1 >= 0 && !maze[row][col - 1].getVisited()) { //left case DONE
            cords[3][0] = row;
            cords[3][1] = col - 1;
            open = 1;
        }else{
            cords[3][0] = 69;
            cords[3][1] = 69;
        }
        Random rand = new Random();
        int r = rand.nextInt(4); //random array index 0-3
        if (open == 1) { //exists an open spot
            int complete = 0;
            while (complete == 0) { //found an open spot
                if (cords[r][0] != 69) {
                    complete++;
                }
                else {
                    r = rand.nextInt(4);
                }
            }
        }
        else {
            neighbor[0][0] = 69; // if nothing is open
        }
        neighbor[0][0] = cords[r][0];
        neighbor[0][1] = cords[r][1];
        neighbor[1][1] = r; //stores direction as num
    }

    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze() {
        for (int i = 0; i < (maze.length * 2) + 1; i++) {
            String endMaze = "";
            if(startRow != i/2 || i % 2 == 0){
                endMaze += "|";
            }else{
                endMaze += " ";
            }
            for (int j = 0; j < maze[0].length; j++) {
                if ( i == maze.length * 2 || i == 0) {
                    endMaze += "---|";
                } else {
                    if (i % 2 == 1) {
                        if (maze[i / 2][j].getVisited()) { //visited
                            endMaze += " * ";
                        } else {
                            endMaze += "   ";
                        }
                        if (maze[i / 2][j].getRight())
                            endMaze += "|";
                        else
                            endMaze += " ";
                    } else {
                        if (maze[i / 2 - 1][j].getBottom())
                            endMaze += "---|";
                        else
                            endMaze += "   |";
                    }
                }
            }
            System.out.println(endMaze);
        }
    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
        Q1Gen<int[]> q = new Q1Gen<>();
        q.add(new int[]{startRow, 0});
        while(q.length() > 0){
            int[] temp = q.remove();
            maze[temp[0]][temp[1]].setVisited(true);
            if(temp[0] == endRow && temp[1] == maze[0].length-1){ // hits end goal
                break;
            }
            int row = temp[0];
            int col = temp[1];
            // One of the options
            if(boundchecker(this, row + 1, col) && !maze[row][col].getBottom()){
                q.add(new int[]{row+1,col});
            }
            if(boundchecker(this, row, col+1) && !maze[row][col].getRight()){
                q.add(new int[]{row,col+1});
            }
            if(boundchecker(this, row - 1, col) && !maze[row-1][col].getBottom()){
                q.add(new int[]{row-1,col});
            }
            if(boundchecker(this, row, col-1) && !maze[row][col-1].getRight()){
                q.add(new int[]{row,col-1});
            }
        }
    }

    public static boolean boundchecker(MyMaze maze, int row, int col){ // helper function to find walls
        return row > -1 && row < maze.maze.length && col > -1 && col < maze.maze[0].length && !maze.maze[row][col].getVisited();
    }

    public static void main(String[] args){
        /*Make and solve maze */
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter two numbers, between 5 and 20");
        int i = scan.nextInt();
        int j = scan.nextInt(); //user rows and cols
        int r1 = rand.nextInt(i);
        int r2 = rand.nextInt(i); //random entrance and exit
        MyMaze test = new MyMaze(i, j, r1, r2); // end row and end col are indexes (start at 0)
        test = test.makeMaze(i, j, r1, r2);
        test.printMaze();
        test.solveMaze();
        System.out.println();
        test.printMaze();

    }
}
