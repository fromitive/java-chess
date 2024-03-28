package chess.domain.piece;

import static chess.domain.Fixtures.A1;
import static chess.domain.Fixtures.A2;
import static chess.domain.Fixtures.A3;
import static chess.domain.Fixtures.A4;
import static chess.domain.Fixtures.A5;
import static chess.domain.Fixtures.A7;
import static chess.domain.Fixtures.B2;
import static chess.domain.Fixtures.C1;
import static chess.domain.Fixtures.D2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {
    @Test
    @DisplayName("폰이 직선으로 이동할 경우 경로에 어떠한 말도 없다면 이동 가능하다.")
    void Given_Pawn_When_CanMoveStraightPositionEmpty_Then_True() {
        //given
        Piece whitePawn = new Pawn(Color.WHITE);
        //when, then
        assertThat(whitePawn.canMove(A1, A2, Map.of())).isTrue();
    }

    @Test
    @DisplayName("폰이 시작위치에 있을 경우 2칸 전진이 가능하다.")
    void Given_Pawn_When_CanMoveTwoStraightInFirstPositionAndPositionEmpty_Then_True() {
        //given
        Piece whitePawn = new Pawn(Color.WHITE);
        Piece blackPawn = new Pawn(Color.BLACK);
        //when, then
        assertAll(
                () -> assertThat(whitePawn.canMove(A2, A4, Map.of())).isTrue(),
                () -> assertThat(blackPawn.canMove(A7, A5, Map.of())).isTrue()
        );
    }

    @Test
    @DisplayName("폰이 대각선 이동할 경우 도착 위치에 상대방의 말이 있다면 이동 가능하다.")
    void Given_Pawn_When_CanMoveDiagonalPositionEnemy_Then_True() {
        //given
        Piece whitePawn = new Pawn(Color.WHITE);
        //when, then
        assertAll(
                () -> assertThat(whitePawn.canMove(C1, B2, Map.of(B2, new Pawn(Color.BLACK)))).isTrue(),
                () -> assertThat(whitePawn.canMove(C1, D2, Map.of(D2, new Pawn(Color.BLACK)))).isTrue()
        );
    }

    @Test
    @DisplayName("폰이 직선으로 이동할 경우 도착 위치에 말이 있다면 이동이 불가능하다.")
    void Given_Pawn_When_CanNotMoveStraightPositionHasPiece_Then_False() {
        //given
        Piece whitePawn = new Pawn(Color.WHITE);
        //when, then
        assertAll(
                () -> assertThat(whitePawn.canMove(A1, A2, Map.of(A2, new Pawn(Color.WHITE)))).isFalse(),
                () -> assertThat(whitePawn.canMove(A1, A2, Map.of(A2, new Pawn(Color.BLACK)))).isFalse()
        );
    }

    @Test
    @DisplayName("폰이 직선으로 이동할 경우 도착 위치 전의 경로에 말이 있다면 이동이 불가능하다.")
    void Given_Pawn_When_CanNotMoveStraightIfPieceIsOnDirection_Then_False() {
        //given
        Piece whitePawn = new Pawn(Color.WHITE);
        //when, then
        assertAll(
                () -> assertThat(whitePawn.canMove(A1, A3, Map.of(A2, new Pawn(Color.BLACK)))).isFalse(),
                () -> assertThat(whitePawn.canMove(A1, A3, Map.of(A2, new Pawn(Color.WHITE)))).isFalse()
        );
    }

    @Test
    @DisplayName("폰이 대각선으로 이동할 경우 도착 위치가 같은 색의 말인 경우 이동이 불가능하다.")
    void Given_Pawn_When_CanNotMoveDiagonalPositionSameColorPiece_Then_False() {
        Piece whitePawn = new Pawn(Color.WHITE);
        assertAll(
                () -> assertThat(whitePawn.canMove(C1, B2, Map.of(B2, new Pawn(Color.WHITE)))).isFalse(),
                () -> assertThat(whitePawn.canMove(C1, D2, Map.of(B2, new Pawn(Color.WHITE)))).isFalse()
        );
    }

    @Test
    @DisplayName("폰이 대각선으로 이동할 경우 도착 위치가 비어있을 경우 이동이 불가능하다.")
    void Given_Pawn_When_CanNotMoveDiagonalPositionEmpty_Then_False() {
        Piece whitePawn = new Pawn(Color.WHITE);
        assertAll(
                () -> assertThat(whitePawn.canMove(C1, B2, Map.of())).isFalse(),
                () -> assertThat(whitePawn.canMove(C1, D2, Map.of())).isFalse());
    }
}
