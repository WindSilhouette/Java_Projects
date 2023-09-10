

import java.util.Scanner;

public class Game {
    public static void main(String[] args){

        //while loops counters
        int count = 0;
        int white = 0;
        int black = 0;

        int startRow;
        int endRow;
        int startCol;
        int endCol;

        //user input formal [start Row] [start col] [end row] [end col]

        Board board = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
        
        while(count == 0){   //board.isGameOver() == false
            Scanner scan = new Scanner(System.in);

            black = 0;
            white = 0;

            //game start, whites turn first
            while (white == 0){
                System.out.println(board);
                System.out.println("It is currently white's turn to play. (top)");
                System.out.println("What is your move? (format: [start Row] [start col] [end row] [end col])");

                startRow= scan.nextInt();
                startCol = scan.nextInt();
                endRow = scan.nextInt();
                endCol = scan.nextInt();

                while(board.movePiece(startRow, startCol, endRow, endCol) == false){
                    System.out.println("move is not legal, retry");
                    System.out.println("enter new cords");
                    startRow= scan.nextInt();
                    startCol = scan.nextInt();
                    endRow = scan.nextInt();
                    endCol = scan.nextInt();
                }
                white++;
            }

            // ends game if king is down
            if (board.isGameOver()){
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        if (board.getPiece(i,j) != null){
                            if (board.getPiece(i,j).getCharacter() == '\u2654' ){
                                System.out.println("Black has won the game!");
                                count = 1;
                            }else if (board.getPiece(i,j).getCharacter() == '\u265a' ){
                                System.out.println("White has won the game!");
                                count = 1;
                            }
                        }
                    }
                }
            }

            //blacks turn
            if(board.isGameOver() != true) {
                while (black == 0) {
                    System.out.println(board);
                    System.out.println("It is currently black's turn to play. (bottom)");
                    System.out.println("What is your move? (format: [start Row] [start col] [end row] [end col])");

                    //get each number into a variable
                    startRow = scan.nextInt();
                    startCol = scan.nextInt();
                    endRow = scan.nextInt();
                    endCol = scan.nextInt();

                    while (board.movePiece(startRow, startCol, endRow, endCol) == false) {
                        System.out.println("move is not legal, retry");
                        System.out.println("enter new cords");
                        startRow = scan.nextInt();
                        startCol = scan.nextInt();
                        endRow = scan.nextInt();
                        endCol = scan.nextInt();
                    }
                    black++;
                }

                //ends game
                if (board.isGameOver()) {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (board.getPiece(i, j) != null) {
                                if (board.getPiece(i, j).getCharacter() == '\u2654') {
                                    System.out.println("Black has won the game!");
                                    count = 1;
                                } else if (board.getPiece(i, j).getCharacter() == '\u265a') {
                                    System.out.println("White has won the game!");
                                    count = 1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
