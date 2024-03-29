package chess.domain.piece;

public class Pawn extends Piece {

    private Pawn(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    public static Piece of(Color color) {
        if (color == Color.WHITE) {
            return new Pawn(PieceType.WHITE_PAWN, Color.WHITE);
        }
        return new Pawn(PieceType.BLACK_PAWN, Color.BLACK);
    }
}
