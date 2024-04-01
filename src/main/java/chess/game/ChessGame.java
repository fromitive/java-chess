package chess.game;

import chess.game.status.GameStatus;
import chess.game.status.InitialGame;
import chess.service.ChessService;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public class ChessGame {

    public void start(InputView inputView, OutputView outputView, ChessService chessService) {
        outputView.printInitialMessage();
        GameStatus gameStatus = new InitialGame();
        while (gameStatus.isPlayable()) {
            gameStatus = getGameStatus(gameStatus, inputView, outputView, chessService);
        }
    }

    private GameStatus getGameStatus(GameStatus gameStatus, InputView inputView, OutputView outputView,
                                     ChessService chessService) {
        try {
            gameStatus = gameStatus.play(inputView, outputView, chessService);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
        return gameStatus;
    }
}
