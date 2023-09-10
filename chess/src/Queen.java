


public class Queen{
    public Queen(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        //Piece capture H
        if(board.getPiece(endRow, endCol) !=null && board.verifyHorizontal(row, col, endRow, endCol) && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack){
            return true;

        }
        //No Piece H
        if(board.verifyHorizontal(row, col, endRow, endCol) && board.getPiece(endRow, endCol) == null){
            return true;
        }
        //Piece Capture V
        if(board.getPiece(endRow, endCol) !=null && board.verifyVertical(row, col, endRow, endCol) && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack){
            return true;

        }
        //No Piece V
        if(board.verifyVertical(row, col, endRow, endCol) && board.getPiece(endRow, endCol) == null){
            return true;
        }
        //Piece Capture D
        if(board.getPiece(endRow, endCol) !=null && board.verifyDiagonal(row, col, endRow, endCol) && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack){
            return true;

        }
        //Capture D
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

