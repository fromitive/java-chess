package chess.game.status;

import chess.domain.board.Board;
import chess.domain.board.BoardCreator;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public class RestartGame implements GameStatus {
    private final InputView inputView;

    public RestartGame(InputView inputView) {
        this.inputView = inputView;
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play() {
        Board board = BoardCreator.create();
        OutputView.printBoard(board);
        return new MainGame(inputView, board);
    }
}
