package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameResult {

    public Color getWinnerColor(final Board board) {
        if (board.isKingAlone()) {
            return board.getAloneKingColor();
        }
        return calculateColor(board);
    }

    private Color calculateColor(final Board board) {
        Score whiteScore = calcuateScore(board, Color.WHITE);
        Score blackScore = calcuateScore(board, Color.BLACK);
        if (whiteScore.isGraterThan(blackScore)) {
            return Color.WHITE;
        }
        if (blackScore.isGraterThan(whiteScore)) {
            return Color.BLACK;
        }
        return Color.NONE;
    }

    public Score calcuateScore(final Board board, final Color color) {
        Map<Position, Piece> positions = board.getBoard();
        return Arrays.stream(File.values())
                .map(file -> calculateFileScore(positions, file, color))
                .reduce(Score.ZERO, Score::add);
    }

    private Score calculateFileScore(Map<Position, Piece> positions, File file, Color color) {
        List<Piece> pieces = Arrays.stream(Rank.values())
                .map(rank -> getExistPiece(positions, file, rank))
                .filter(piece -> piece.isSameColor(color))
                .toList();
        return calculateScore(pieces);
    }

    private Piece getExistPiece(final Map<Position, Piece> positions, final File file, final Rank rank) {
        Position searchPosition = Position.of(file, rank);
        if (positions.containsKey(searchPosition)) {
            return positions.get(searchPosition);
        }
        return Piece.EMPTY_PIECE;
    }

    private Score calculateScore(List<Piece> pieces) {
        Score score = pieces.stream().map(piece -> PieceScore.getPieceScore(piece.getPieceType()))
                .map(PieceScore::getScore)
                .reduce(Score.ZERO, Score::add);

        return score.subtract(calculatePawnScore(pieces));
    }

    private Score calculatePawnScore(List<Piece> pieces) {
        int pawnCount = calculatePawnCount(pieces);
        if (calculatePawnCount(pieces) > 1) {
            return new Score(pawnCount).multiply(0.5);
        }
        return Score.ZERO;
    }

    private int calculatePawnCount(List<Piece> pieces) {
        return (int) pieces.stream()
                .map(Piece::getPieceType)
                .filter(pieceType -> pieceType == PieceType.BLACK_PAWN || pieceType == PieceType.WHITE_PAWN)
                .count();
    }
}
