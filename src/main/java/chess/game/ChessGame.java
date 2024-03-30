package chess.game;

import chess.game.status.GameStatus;
import chess.game.status.InitialGame;
import chess.service.ChessService;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public class ChessGame {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start(ChessService chessService) {
        outputView.printInitialMessage();
        GameStatus gameStatus = new InitialGame();
        while (gameStatus.isPlayable()) {
            gameStatus = getGameStatus(gameStatus, chessService);
        }
    }

    private GameStatus getGameStatus(GameStatus gameStatus, ChessService chessService) {
        try {
            gameStatus = gameStatus.play(inputView, outputView, chessService);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
        return gameStatus;
    }
}
