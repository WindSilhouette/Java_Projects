

public class King {
    
    private int row;
    private int col;
    private boolean isBlack;
    
    public King(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        //case 1 piece
        if(board.getPiece(endRow, endCol) != null && board.verifyAdjacent(row, col, endRow, endCol) && board.getPiece(row, col).getIsBlack()== isBlack){
            return true;
        }
        //case 2 no piece in way
        if(board.verifyAdjacent(row, col, endRow, endCol) && board.getPiece(endRow, endCol) == null){
            return true;

        }
        //case 3 invalid move
        return false;
        
    }
    

}
