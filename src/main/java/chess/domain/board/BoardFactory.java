package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardFactory {
    private static final Rank WHITE_PIECE_START_RANK = Rank.ONE;
    private static final Rank WHITE_PAWN_START_RANK = Rank.TWO;

    private static final Rank BLACK_PIECE_START_RANK = Rank.EIGHT;
    private static final Rank BLACK_PAWN_START_RANK = Rank.SEVEN;

    public static Board create() {
        Map<Position, Piece> initialPiecePositions = generateEmptyBoard();
        initialPiecePositions.putAll(getWhitePieces());
        initialPiecePositions.putAll(getBlackPieces());
        return new Board(initialPiecePositions);
    }

    private static Map<Position, Piece> generateEmptyBoard() {
        return Arrays.stream(Rank.values())
                .flatMap(BoardFactory::generateHorizontalLine)
                .collect(generateEntry());
    }

    private static Stream<Position> generateHorizontalLine(final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> new Position(file, rank));
    }

    private static Collector<Position, ?, Map<Position, Piece>> generateEntry() {
        return Collectors.toMap(position -> position, position -> Piece.EMPTY_PIECE);
    }

    private static Map<Position, Piece> getWhitePieces() {
        Map<Position, Piece> initialWhitePiecePositions = new HashMap<>();
        initialWhitePiecePositions.putAll(getNotPawnsPieces(Color.WHITE, WHITE_PIECE_START_RANK));
        initialWhitePiecePositions.putAll(getPawnsPieces(Color.WHITE, WHITE_PAWN_START_RANK));
        return initialWhitePiecePositions;
    }

    private static Map<Position, Piece> getBlackPieces() {
        Map<Position, Piece> initialWhitePiecePositions = new HashMap<>();
        initialWhitePiecePositions.putAll(getNotPawnsPieces(Color.BLACK, BLACK_PIECE_START_RANK));
        initialWhitePiecePositions.putAll(getPawnsPieces(Color.BLACK, BLACK_PAWN_START_RANK));
        return initialWhitePiecePositions;
    }

    private static Map<Position, Piece> getNotPawnsPieces(final Color color, final Rank rank) {
        return Map.of(new Position(File.A, rank), new Piece(PieceType.ROOK, color),
                new Position(File.B, rank), new Piece(PieceType.KNIGHT, color),
                new Position(File.C, rank), new Piece(PieceType.BISHOP, color),
                new Position(File.D, rank), new Piece(PieceType.QUEEN, color),
                new Position(File.E, rank), new Piece(PieceType.KING, color),
                new Position(File.F, rank), new Piece(PieceType.BISHOP, color),
                new Position(File.G, rank), new Piece(PieceType.KNIGHT, color),
                new Position(File.H, rank), new Piece(PieceType.ROOK, color));
    }

    private static Map<Position, Piece> getPawnsPieces(final Color color, final Rank rank) {
        return Arrays.stream(File.values())
                .collect(Collectors.toMap(file -> new Position(file, rank), file -> Pawn.of(color)));
    }
}
