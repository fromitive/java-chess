package chess.domain.board;

import static chess.domain.Fixtures.A1;
import static chess.domain.Fixtures.A2;
import static chess.domain.Fixtures.A4;
import static chess.domain.Fixtures.A5;
import static chess.domain.Fixtures.A7;
import static chess.domain.Fixtures.B2;
import static chess.domain.Fixtures.B8;
import static chess.domain.Fixtures.C6;
import static chess.domain.Fixtures.D6;
import static chess.domain.Fixtures.EMPTY_PIECE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
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
        board.move(A2, A4, Color.WHITE);
        //then
        Map<Position, Piece> boardPieces = board.getBoard();
        assertAll(
                () -> assertThat(boardPieces.get(A2)).isEqualTo(EMPTY_PIECE),
                () -> assertThat(boardPieces.get(A4)).isEqualTo(Pawn.of(Color.WHITE)))
        ;
    }

    @Test
    @DisplayName("보드에서 같은 색상의 말의 위치로 이동시킬 경우 예외가 발생한다.")
    void Given_Board_When_MoveValidPieceAndColorInvalidPosition_Then_Exception() {
        //given
        Board board = BoardFactory.create();
        //when, then
        assertThatThrownBy(
                () -> board.move(A1, A2, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색상의 말의 위치로 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("보드에서 상대 팀 체스 말을 이동할 경우 예외가 발생한다.")
    void Given_Board_When_MoveInvalidPieceAndColorValidPosition_Then_Exception() {
        //given
        Board board = BoardFactory.create();
        //when, then
        assertThatThrownBy(
                () -> board.move(A7, A5, Color.WHITE))
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
                () -> board.move(C6, D6, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물이 존재하지 않아 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("말이 이동할 때 이동이 불가능한 경로일 경우 예외가 발생한다.")
    void Given_Board_When_MovePieceCanNotMovePosition_Then_Exception() {
        //given
        Board board = BoardFactory.create();
        //when, then
        assertThatThrownBy(
                () -> board.move(B2, B8, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말로 이동할 수 없는 위치입니다.");
    }
}
