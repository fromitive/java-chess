package chess.service;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.service.dao.ChessDAO;
import chess.service.dto.ChessDTO;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {
    private final ChessDAO chessDAO;

    public ChessService(ChessDAO chessDAO) {
        this.chessDAO = chessDAO;
    }

    public ChessDTO loadChess() {
        Map<Position, Piece> board = generateEmptyBoard();
        try {
            board.putAll(chessDAO.getBoard());
            return new ChessDTO(new Board(board), chessDAO.getCurrentTurnColor());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Position, Piece> generateEmptyBoard() {
        return Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values()).map(rank -> Position.of(file, rank)))
                .collect(Collectors.toMap(position -> position, position -> Piece.EMPTY_PIECE));
    }

    void saveChess(Board board, Color color) {
        chessDAO.updateBoard(board);
        chessDAO.updateColor(color);
    }

    void initializeChess() {
        chessDAO.initialize();
    }
}
