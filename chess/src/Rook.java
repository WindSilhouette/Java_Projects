


public class Rook{
    public Rook(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        //  No piece H
        if(board.verifyHorizontal(row, col, endRow, endCol) && board.getPiece(endRow, endCol) == null){
            return true;
        }
        //Piece H
        if(board.getPiece(endRow, endCol) !=null && board.verifyHorizontal(row, col, endRow, endCol) && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack){
            return true;

        }
        //No Piece V
        if(board.verifyVertical(row, col, endRow, endCol) && board.getPiece(endRow, endCol) == null){
            return true;
        }
        //Piece V
        if(board.verifyAdjacent(row, col, endRow, endCol) && board.getPiece(endRow, endCol) == null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack ){
            return true;
        }
        else{
            return false;
        }
    }
    private int row;
    private int col;
    private boolean isBlack;
}