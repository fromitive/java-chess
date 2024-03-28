package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

    @Test
    @DisplayName("기본 체스 보드를 반환한다.")
    void Given_BoardCreator_When_Create_Then_BasicBoardCreated() {
        //given, when
        Board board = BoardFactory.create();
        Map<Position, Piece> initialPiecePositions = board.getBoard();
        //then
        assertAll(
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(File.A, Rank.ONE), new Rook(Color.WHITE),
                                new Position(File.B, Rank.ONE), new Knight(Color.WHITE),
                                new Position(File.C, Rank.ONE), new Bishop(Color.WHITE),
                                new Position(File.D, Rank.ONE), new Queen(Color.WHITE),
                                new Position(File.E, Rank.ONE), new King(Color.WHITE),
                                new Position(File.F, Rank.ONE), new Bishop(Color.WHITE),
                                new Position(File.G, Rank.ONE), new Knight(Color.WHITE),
                                new Position(File.H, Rank.ONE), new Rook(Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(File.A, Rank.TWO), new Pawn(Color.WHITE),
                                new Position(File.B, Rank.TWO), new Pawn(Color.WHITE),
                                new Position(File.C, Rank.TWO), new Pawn(Color.WHITE),
                                new Position(File.D, Rank.TWO), new Pawn(Color.WHITE),
                                new Position(File.E, Rank.TWO), new Pawn(Color.WHITE),
                                new Position(File.F, Rank.TWO), new Pawn(Color.WHITE),
                                new Position(File.G, Rank.TWO), new Pawn(Color.WHITE),
                                new Position(File.H, Rank.TWO), new Pawn(Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(File.A, Rank.SEVEN), new Pawn(Color.BLACK),
                                new Position(File.B, Rank.SEVEN), new Pawn(Color.BLACK),
                                new Position(File.C, Rank.SEVEN), new Pawn(Color.BLACK),
                                new Position(File.D, Rank.SEVEN), new Pawn(Color.BLACK),
                                new Position(File.E, Rank.SEVEN), new Pawn(Color.BLACK),
                                new Position(File.F, Rank.SEVEN), new Pawn(Color.BLACK),
                                new Position(File.G, Rank.SEVEN), new Pawn(Color.BLACK),
                                new Position(File.H, Rank.SEVEN), new Pawn(Color.BLACK)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(File.A, Rank.EIGHT), new Rook(Color.BLACK),
                                new Position(File.B, Rank.EIGHT), new Knight(Color.BLACK),
                                new Position(File.C, Rank.EIGHT), new Bishop(Color.BLACK),
                                new Position(File.D, Rank.EIGHT), new Queen(Color.BLACK),
                                new Position(File.E, Rank.EIGHT), new King(Color.BLACK),
                                new Position(File.F, Rank.EIGHT), new Bishop(Color.BLACK),
                                new Position(File.G, Rank.EIGHT), new Knight(Color.BLACK),
                                new Position(File.H, Rank.EIGHT), new Rook(Color.BLACK)
                        ))
        );
    }

    @Test
    @DisplayName("말의 위치가 비어있는 경우 비어있는 말의 타입을 반환한다.")
    void Given_BoardCreator_When_GetPieceFromEmptyPosition_Then_ReturnEmptyPiece() {
        //given
        Board board = BoardFactory.create();
        // when, then
        Map<Position, Piece> initialPiecePositions = board.getBoard();
        Arrays.stream(Rank.values())
                .filter(rank -> !List.of(Rank.ONE, Rank.TWO, Rank.SEVEN, Rank.EIGHT).contains(rank))
                .flatMap(rank -> Arrays.stream(File.values()).map(file -> new Position(file, rank)))
                .forEach(position -> assertThat(initialPiecePositions.get(position)).isEqualTo(new Empty()));
    }
}
