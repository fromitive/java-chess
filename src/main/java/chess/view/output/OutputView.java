package chess.view.output;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.view.input.command.GameCommand;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String WHITE_COLOR = "흰색";
    private static final String BLACK_COLOR = "검은색";
    private static final String WINNER_FORMAT = "%s이 이겼습니다!";

    public void printBoard(final Board board) {
        Map<Position, Piece> positions = board.getBoard();
        Arrays.stream(Rank.values()).sorted(Collections.reverseOrder()).forEach(rank -> printRankLine(positions, rank));
        System.out.println();
    }

    private void printRankLine(final Map<Position, Piece> positions, final Rank rank) {
        String rankLine = Arrays.stream(File.values())
                .map(file -> positions.get(Position.of(file, rank)))
                .map(PieceSymbol::getDisplay)
                .collect(Collectors.joining(""));
        System.out.println(rankLine);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        String commandMessage = Arrays.stream(GameCommand.values())
                .map(gameCommand -> "> %s".formatted(gameCommand.getHelperMessage()))
                .collect(Collectors.joining("\n"));

        System.out.println(commandMessage);
    }

    public void printWinnerColor(Color color) {
        if (color == Color.WHITE) {
            System.out.println(WINNER_FORMAT.formatted(WHITE_COLOR));
        }
        if (color == Color.BLACK) {
            System.out.println(WINNER_FORMAT.formatted(BLACK_COLOR));
        }
    }
}
