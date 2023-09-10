


public class Board {

    // Instance variables
    private Piece[][] board;
    //private Board board1;

    //does: you idiots
    // Construct an object of type Board using given arguments.
    public Board() {

        board = new Piece[8][8];

    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {

        board[row][col] = piece;
    }


    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        // move and remove old piece position
        if(board[startRow][startCol].isMoveLegal(this, endRow, endCol)) {
            board[endRow][endCol] = getPiece(startRow, startCol);
            board[startRow][startCol] = null;
            board[endRow][endCol].setPosition(endRow, endCol);
            return true;
        }
        return false;
    }


    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int count = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[i][j] != null){
                    if(board[i][j].getCharacter() == '\u265a' || board[i][j].getCharacter() =='\u2654'){
                        count++;
                    }
                }  
            }
        }
        if (count == 2){
            return false;
        } else{
            return true;
        }
    }

    //TODO:
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        String final1 = "";
        final1 += "   0 1 2 3 4 5 6 7 ";
        for(int i = 0; i < board.length; i++){ //cols
            final1 += "\n";
            final1 += i + " |";
            for(int j = 0; j < board[i].length; j++){ //rows
                if(board[i][j] != null){
                    final1 += board[i][j].getCharacter() +"|" ;
                } else{
                    final1 += " |";
                }
            }
        }
        return final1;
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = null;
            }
        }
    }
    public void clearPiece(Piece piece){
        piece = null;
    }
    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if(startRow < 8 && startCol < 8 && endRow < 8 && endCol < 8 && startRow > -1 && startCol > -1 && endRow > -1 && endCol > -1){//make sure its cant be less than 
            if(board[startRow][startCol] != null){
                if(board[startRow][startCol].getIsBlack() == isBlack){
                    if(board[endRow][endCol] == null || board[endRow][endCol].getIsBlack() != isBlack){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if(Math.abs(startCol-endCol) == 1 || Math.abs(startRow-endRow) == 1){ // out of bounds
            return true;
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        //we checked right to left
        
        if (endRow-startRow == 0){
            int count = Math.abs(endCol-startCol); // find start variable
            int start = startCol;
            if (startCol > endCol){
                start = endCol;
            }
            for(int i = start; i > count; i--){
                if(board[startRow][i] != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        //we checked values down to up
        
        if (endCol-startCol == 0){
            int count = Math.abs(endRow-startRow);
            int start = startCol;
            if (startCol < endCol){
                start = endCol;
            }
            for(int i = start; i > count; i--){
                if(board[i][startCol] != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int dif = Math.abs(startRow - endRow);
        for (int i = 1; i < dif; i++) {
            if ((endRow > startRow) && (endCol > startCol)
                    && board[startRow + i][startCol + i] != null) {
                return false;
            } else if ((endRow < startRow) && (endCol > startCol)
                    && board[startRow - i][startCol + i] != null) {
                return false;
            } else if ((endRow < startRow) && (endCol < startCol)
                    && board[startRow - i][startCol - i] != null){
                return false;
            } else if ((endRow > startRow) && (endCol < startCol)
                    && board[startRow + i][startCol - i] != null)  {
                return false;
            }
        }
        return true;
    }
}
