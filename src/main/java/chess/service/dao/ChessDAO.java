package chess.service.dao;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface ChessDAO {
    Map<Position, Piece> getBoard();

    Color getColor();

    void updateBoard(Board board);

    void updateColor(Color color);

    void initialize();
}
