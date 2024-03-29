package chess.domain.piece;

import chess.domain.position.Obstacle;
import chess.domain.position.Position;
import java.util.Objects;

public class Piece {
    public static final Piece EMPTY_PIECE = new Piece(PieceType.EMPTY, Color.NONE);

    private final PieceType pieceType;
    private final Color color;

    public Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public boolean canMove(final Position source, final Position target, final Obstacle obstacle) {
        return pieceType.canMove(source, target, obstacle);
    }

    public boolean canAttack(final Position source, final Position target, final Obstacle obstacle) {
        return pieceType.canAttack(source, target, obstacle);
    }

    public boolean isEmpty() {
        return pieceType == PieceType.EMPTY;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color);
    }
}
