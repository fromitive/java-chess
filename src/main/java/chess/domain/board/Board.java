package chess.domain.board;

import chess.domain.Obstacle;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Board move(final Position source, final Position target, Color color) {
        Piece piece = board.get(source);
        validateEmpty(source);
        validateColorTurn(piece, color);
        validateMovement(source, target);

        board.put(target, piece);
        board.put(source, Piece.EMPTY_PIECE);

        return new Board(board);
    }

    private void validateEmpty(Position source) {
        if (board.get(source).isEmpty()) {
            throw new IllegalArgumentException("기물이 존재하지 않아 이동시킬 수 없습니다.");
        }
    }

    private void validateColorTurn(Piece piece, Color color) {
        if (!piece.isSameColor(color)) {
            throw new IllegalArgumentException("상대 팀의 기물을 이동시킬 수 없습니다.");
        }
    }

    private void validateMovement(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        validateSameColorPiece(sourcePiece, targetPiece);
        if (!canAttack(source, target, sourcePiece) && !canMove(source, target, sourcePiece)) {
            throw new IllegalArgumentException("해당 말로 이동할 수 없는 위치입니다.");
        }
    }

    private void validateSameColorPiece(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameColor(targetPiece.getColor())) {
            throw new IllegalArgumentException("같은 색상의 말의 위치로 이동시킬 수 없습니다.");
        }
    }

    private boolean canAttack(Position source, Position target, Piece sourcePiece) {
        return sourcePiece.canAttack(source, target, new Obstacle(getBoardPositions()));
    }

    private boolean canMove(Position source, Position target, Piece sourcePiece) {
        return sourcePiece.canMove(source, target, new Obstacle(getBoardPositions()));
    }

    private List<Position> getBoardPositions() {
        return board.entrySet().stream()
                .filter(position -> !position.getValue().isEmpty())
                .map(Entry::getKey)
                .toList();
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
