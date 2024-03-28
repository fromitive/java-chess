package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("보드에 있는 체스말이 존재하고 이동이 가능하면 지정한 위치로 이동할 수 있다.")
    void Given_Board_When_MoveValidColorAndPieceValidPosition_Then_MoveSelectedPiece() {
        //given
        Board board = BoardFactory.create();
        //when
        board.move(new Position(File.A, Rank.TWO), new Position(File.A, Rank.FOUR), Color.WHITE);
        Map<Position, Piece> boardPieces = board.getBoard();
        //then
        assertAll(
                () -> assertThat(boardPieces.get(new Position(File.A, Rank.TWO)))
                        .isEqualTo(new Empty()),
                () -> assertThat(boardPieces.get(new Position(File.A, Rank.FOUR)))
                        .isEqualTo(new Pawn(Color.WHITE)));
    }

    @Test
    @DisplayName("보드에서 체스 말을 이동할 수 없는 경우 예외가 발생한다.")
    void Given_Board_When_MoveValidPieceAndColorInvalidPosition_Then_Exception() {
        //given
        Board board = BoardFactory.create();
        //when, then
        assertThatThrownBy(
                () -> board.move(new Position(File.A, Rank.ONE), new Position(File.A, Rank.TWO), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동이 불가능한 위치입니다.");
    }

    @Test
    @DisplayName("보드에서 상대 팀 체스 말을 이동할 경우 예외가 발생한다.")
    void Given_Board_When_MoveInvalidPieceAndColorValidPosition_Then_Exception() {
        //given
        Board board = BoardFactory.create();
        //when, then
        assertThatThrownBy(
                () -> board.move(new Position(File.A, Rank.SEVEN), new Position(File.A, Rank.FIVE), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대 팀의 기물을 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("보드에서 말울 이동할 때 출발 위치에 말 존재하지 않을 경우 예외가 발생한다.")
    void Given_Board_When_MoveEmptyPieceAndColorValidPosition_Then_Exception() {
        //given
        Board board = BoardFactory.create();
        //when, then
        assertThatThrownBy(
                () -> board.move(new Position(File.C, Rank.SIX), new Position(File.D, Rank.SIX), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물이 존재하지 않아 이동시킬 수 없습니다.");
    }
}
