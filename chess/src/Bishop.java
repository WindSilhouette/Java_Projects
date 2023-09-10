

public class Bishop{
    public Bishop(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        //Diagonal Piece Capture
        if(board.getPiece(endRow, endCol) !=null && board.verifyDiagonal(row, col, endRow, endCol) && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack){
            return true;

        }
        //Diagonal No Piece 
        if(board.verifyDiagonal(row, col, endRow, endCol) && board.getPiece(endRow, endCol) == null){
            return true;
        }
        //Every other case
        else{
            return false;

        } 
}
    private int row;
    private int col;
    private boolean isBlack;

}
