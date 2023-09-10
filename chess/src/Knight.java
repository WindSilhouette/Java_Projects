

public class Knight {
    private int row;
    private int col;
    private boolean isBlack;
    
    public Knight(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        //if piece is detected, it must not match isBlack, endrow and endcol must not != isBlack
        if(board.getPiece(endRow, endCol) != null && board.getPiece(row, col).getIsBlack() != isBlack){
            if(Math.abs(row-endRow) == 2){
                if(Math.abs(col-endCol) == 1){
                    return true;
                }
            }else if(Math.abs(row-endRow) == 1){
                if(Math.abs(col-endCol) == 2){
                    return true;
                }
            }
        }


        //if the space is null than the move can be made
        if(board.getPiece(endRow, endCol) == null){
            if(Math.abs(row-endRow) == 2){
                if(Math.abs(col-endCol) == 1){
                    return true;
                }
            }else if(Math.abs(row-endRow) == 1){
                if(Math.abs(col-endCol) == 2){
                    return true;
                }

            }
        }
        return false;
    }
}
