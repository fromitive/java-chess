package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceTest {

    @Nested
    class PawnMove {

        @Test
        @DisplayName("폰이 직선으로 이동할 경우 경로에 어떠한 말도 없다면 이동 가능하다.")
        void canMoveStraight() {
            Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE);
            assertAll(() -> assertThat(
                            whitePawn.canMove(new Position(1, 1), new Position(1, 2), true, Map.of())).isTrue(),
                    () -> assertThat(
                            whitePawn.canMove(new Position(1, 1), new Position(1, 3), true, Map.of())).isTrue(),
                    () -> assertThat(
                            whitePawn.canMove(new Position(1, 1), new Position(1, 2), false, Map.of())).isTrue());
        }

        @Test
        @DisplayName("폰이 대각선 이동할 경우 도착 위치에 상대방의 말이 있다면 이동 가능하다.")
        void canMoveDiagonal() {
            Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE);
            assertAll(() -> assertThat(whitePawn.canMove(new Position(3, 1), new Position(2, 2), false,
                    Map.of(new Position(2, 2), new Piece(PieceType.QUEEN, Color.BLACK)))).isTrue(), () -> assertThat(
                    whitePawn.canMove(new Position(3, 1), new Position(4, 2), false,
                            Map.of(new Position(4, 2), new Piece(PieceType.QUEEN, Color.BLACK)))).isTrue());
        }

        @Test
        @DisplayName("폰이 직선으로 이동할 경우 도착 위치에 말이 있다면 이동이 불가능하다.")
        void canNotMoveStraightWhenPieceExistInToPosition() {
            Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE);
            assertAll(() -> assertThat(whitePawn.canMove(new Position(1, 1), new Position(1, 2), true,
                    Map.of(new Position(1, 2), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse(), () -> assertThat(
                    whitePawn.canMove(new Position(1, 1), new Position(1, 2), true,
                            Map.of(new Position(1, 2), new Piece(PieceType.QUEEN, Color.BLACK)))).isFalse());
        }


        @Test
        @DisplayName("폰이 직선으로 이동할 경우 도착 위치 전의 경로에 말이 있다면 이동이 불가능하다.")
        void canNotMoveStraightWhenPieceExistInPath() {
            Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE);
            assertAll(() -> assertThat(whitePawn.canMove(new Position(1, 1), new Position(1, 3), true,
                    Map.of(new Position(1, 2), new Piece(PieceType.QUEEN, Color.BLACK)))).isFalse(), () -> assertThat(
                    whitePawn.canMove(new Position(1, 1), new Position(1, 3), true,
                            Map.of(new Position(1, 2), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse());
        }

        @Test
        @DisplayName("폰이 대각선으로 이동할 경우 도착 위치가 같은 색의 말인 경우 이동이 불가능하다.")
        void canNotMoveDiagonalWhenToPositionIsSameColor() {
            Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE);
            assertAll(() -> assertThat(whitePawn.canMove(new Position(3, 1), new Position(2, 2), true,
                    Map.of(new Position(2, 2), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse(), () -> assertThat(
                    whitePawn.canMove(new Position(3, 1), new Position(4, 2), true,
                            Map.of(new Position(4, 2), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse());
        }

        @Test
        @DisplayName("폰이 대각선으로 이동할 경우 도착 위치가 비어있을 경우 이동이 불가능하다.")
        void canNotMoveDiagonalWhenToPositionIsEmpty() {
            Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE);
            assertAll(() -> assertThat(
                            whitePawn.canMove(new Position(3, 1), new Position(2, 2), true, Map.of())).isFalse(),
                    () -> assertThat(
                            whitePawn.canMove(new Position(3, 1), new Position(4, 2), true, Map.of())).isFalse());
        }
    }

    @Nested
    class KnightMove {

        @ParameterizedTest
        @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
        @DisplayName("나이트는 도착 위치가 비어있는 경우 이동할 수 있다.")
        void canMoveWhenToPositionIsEmpty(int row, int column) {
            Piece piece = new Piece(PieceType.KNIGHT, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false, Map.of())).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
        @DisplayName("나이트는 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
        void canMoveWhenToPositionIsOtherColor(int row, int column) {
            Piece piece = new Piece(PieceType.KNIGHT, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false,
                    Map.of(new Position(column, row), new Piece(PieceType.QUEEN, Color.BLACK)))).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
        @DisplayName("나이트는 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenToPositionIsSameColor(int row, int column) {
            Piece piece = new Piece(PieceType.KNIGHT, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false,
                    Map.of(new Position(column, row), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse();
        }
    }

    @Nested
    class BishopMove {

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
                "7,1", "6,2", "3,5", "2,6", "1,7"})
        @DisplayName("비숍은 도착 위치가 비어있는 경우 이동할 수 있다.")
        void canMoveWhenToPositionIsEmpty(int row, int column) {
            Piece piece = new Piece(PieceType.BISHOP, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false, Map.of())).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
                "7,1", "6,2", "3,5", "2,6", "1,7"})
        @DisplayName("비숍은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
        void canMoveWhenToPositionIsOtherColor(int row, int column) {
            Piece piece = new Piece(PieceType.BISHOP, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false,
                    Map.of(new Position(column, row), new Piece(PieceType.QUEEN, Color.BLACK)))).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
                "7,1", "6,2", "3,5", "2,6", "1,7"})
        @DisplayName("비숍은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenToPositionIsSameColor(int row, int column) {
            Piece piece = new Piece(PieceType.BISHOP, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false,
                    Map.of(new Position(column, row), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse();
        }

        @Test
        @DisplayName("비숍은 이동 경로에 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenPieceExistIn() {
            Piece piece = new Piece(PieceType.BISHOP, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(8, 8), false,
                    Map.of(new Position(6, 6), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse();
        }
    }

    @Nested
    class RookMove {

        @ParameterizedTest
        @CsvSource({"8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
                "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
        @DisplayName("룩은 도착 위치가 비어있는 경우 이동할 수 있다.")
        void canMoveWhenToPositionIsEmpty(int row, int column) {
            Piece piece = new Piece(PieceType.ROOK, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false, Map.of())).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
                "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
        @DisplayName("룩은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
        void canMoveWhenToPositionIsOtherColor(int row, int column) {
            Piece piece = new Piece(PieceType.ROOK, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false,
                    Map.of(new Position(column, row), new Piece(PieceType.QUEEN, Color.BLACK)))).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
                "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
        @DisplayName("룩은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenToPositionIsSameColor(int row, int column) {
            Piece piece = new Piece(PieceType.ROOK, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false,
                    Map.of(new Position(column, row), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse();
        }

        @Test
        @DisplayName("룩은 이동 경로에 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenPieceExistIn() {
            Piece piece = new Piece(PieceType.ROOK, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(8, 4), false,
                    Map.of(new Position(5, 4), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse();
        }
    }

    @Nested
    class QueenMove {

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
                "7,1", "6,2", "3,5", "2,6", "1,7",
                "8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
                "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
        @DisplayName("퀸은 도착 위치가 비어있는 경우 이동할 수 있다.")
        void canMoveWhenToPositionIsEmpty(int row, int column) {
            Piece piece = new Piece(PieceType.QUEEN, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false, Map.of())).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
                "7,1", "6,2", "3,5", "2,6", "1,7",
                "8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
                "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
        @DisplayName("퀸은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
        void canMoveWhenToPositionIsOtherColor(int row, int column) {
            Piece piece = new Piece(PieceType.QUEEN, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false,
                    Map.of(new Position(column, row), new Piece(PieceType.QUEEN, Color.BLACK)))).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
                "7,1", "6,2", "3,5", "2,6", "1,7",
                "8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
                "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
        @DisplayName("퀸은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenToPositionIsSameColor(int row, int column) {
            Piece piece = new Piece(PieceType.QUEEN, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false,
                    Map.of(new Position(column, row), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse();
        }

        @Test
        @DisplayName("퀸은 이동 경로에 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenPieceExistIn() {
            Piece piece = new Piece(PieceType.QUEEN, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(8, 8), false,
                    Map.of(new Position(6, 6), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse();
        }
    }

    @Nested
    class KingMove {

        @ParameterizedTest
        @CsvSource({"3,3", "5,5",
                "3,5",
                "5,4", "3,4",
                "4,3", "4,5",})
        @DisplayName("킹은 도착 위치가 비어있는 경우 이동할 수 있다.")
        void canMoveWhenToPositionIsEmpty(int row, int column) {
            Piece piece = new Piece(PieceType.KING, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false, Map.of())).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"3,3", "5,5",
                "3,5", "5,3",
                "5,4", "3,4",
                "4,3", "4,5",})
        @DisplayName("킹은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
        void canMoveWhenToPositionIsOtherColor(int row, int column) {
            Piece piece = new Piece(PieceType.KING, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false,
                    Map.of(new Position(column, row), new Piece(PieceType.QUEEN, Color.BLACK)))).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"3,3", "5,5",
                "3,5", "5,3",
                "5,4", "3,4",
                "4,3", "4,5",})
        @DisplayName("킹은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenToPositionIsSameColor(int row, int column) {
            Piece piece = new Piece(PieceType.KING, Color.WHITE);
            assertThat(piece.canMove(new Position(4, 4), new Position(column, row), false,
                    Map.of(new Position(column, row), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse();
        }
    }
}

