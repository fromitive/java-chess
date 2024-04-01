package chess.service.dao.db;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.service.dao.ChessDAO;
import chess.service.dao.db.symbol.ColorSymbol;
import chess.service.dao.db.symbol.FileSymbol;
import chess.service.dao.db.symbol.PieceSymbol;
import chess.service.dao.db.symbol.RankSymbol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DatabaseChessDAO implements ChessDAO {

    private static final String GET_BOARD_QUERY = "SELECT `file`, `rank`, `color`, `piece_type` from `board`";
    private static final String GET_CURRENT_TURN_COLOR_QUERY = "SELECT `color` from `current_turn_color`";
    private static final String INSERT_BOARD_QUERY = "INSERT INTO `board` (`file`, `rank`, `piece_type`, `color`) VALUES (?, ?, ?, ?)";
    private static final String INSERT_COLOR_TURN_QUERY = "INSERT INTO `current_turn_color` (`color`) VALUES (?)";
    private static final String INITIALIZE_BOARD_QUERY = "DELETE FROM `board`";
    private static final String INITIALIZE_COLOR_TURN_QUERY = "DELETE FROM `current_turn_color`";

    private final Connection connection;

    public DatabaseChessDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        try (var statement = connection.prepareStatement(GET_BOARD_QUERY)) {
            var resultSet = statement.executeQuery();
            validateEmpty(resultSet);
            return buildBoard(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateEmpty(final ResultSet resultSet) throws SQLException {
        if (!resultSet.isBeforeFirst()) {
            throw new RuntimeException("DB에 기존 게임에 대한 정보가 존재하지 않습니다.");
        }
    }

    private Map<Position, Piece> buildBoard(final ResultSet resultSet) throws SQLException {
        var board = new HashMap<Position, Piece>();
        while (resultSet.next()) {
            FileSymbol fileSymbol = FileSymbol.getFileSymbolBySymbol(resultSet.getString(1));
            RankSymbol rankSymbol = RankSymbol.getRankSymbolBySymbol(resultSet.getString(2));
            ColorSymbol colorSymbol = ColorSymbol.getColorSymbolBySymbol(resultSet.getString(3));
            PieceSymbol pieceSymbol = PieceSymbol.getPieceSymbolBySymbol(resultSet.getString(4));
            board.put(Position.of(fileSymbol.getFile(), rankSymbol.getRank()),
                    Piece.of(pieceSymbol.getPieceType(), colorSymbol.getColor()));
        }
        return board;
    }

    @Override
    public Color getCurrentTurnColor() {
        try (var statement = connection.prepareStatement(GET_CURRENT_TURN_COLOR_QUERY)) {
            var resultSet = statement.executeQuery();
            validateEmpty(resultSet);
            resultSet.next();
            ColorSymbol colorSymbol = ColorSymbol.getColorSymbolBySymbol(resultSet.getString(1));
            return colorSymbol.getColor();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBoard(final Board board) {
        try (var statement = connection.prepareStatement(INSERT_BOARD_QUERY)) {
            executeInitializeQuery(INITIALIZE_BOARD_QUERY);
            board.getBoard().entrySet().stream()
                    .filter(this::isNotEmpty)
                    .forEach(entry -> addPieceIntoStatement(entry, statement));
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isNotEmpty(final Entry<Position, Piece> entry) {
        return entry.getValue() != Piece.EMPTY_PIECE;
    }

    private void addPieceIntoStatement(final Entry<Position, Piece> entry, final PreparedStatement statement) {
        Position position = entry.getKey();
        FileSymbol fileSymbol = FileSymbol.getFileSymbolByFile(position.file());
        RankSymbol rankSymbol = RankSymbol.getRankSymbolByRank(position.rank());
        Piece piece = entry.getValue();
        PieceSymbol pieceSymbol = PieceSymbol.getPieceSymbolByPieceType(piece.getPieceType());
        ColorSymbol colorSymbol = ColorSymbol.getColorSymbolByColor(piece.getColor());
        setInsertedPieceInfo(statement, fileSymbol, rankSymbol, pieceSymbol, colorSymbol);
    }

    private void setInsertedPieceInfo(final PreparedStatement statement, final FileSymbol fileSymbol,
                                      final RankSymbol rankSymbol, final PieceSymbol pieceSymbol,
                                      final ColorSymbol colorSymbol) {
        try {
            statement.setString(1, fileSymbol.getSymbol());
            statement.setString(2, rankSymbol.getSymbol());
            statement.setString(3, pieceSymbol.getSymbol());
            statement.setString(4, colorSymbol.getSymbol());
            statement.addBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateColor(final Color color) {
        try (var statement = connection.prepareStatement(INSERT_COLOR_TURN_QUERY)) {
            executeInitializeQuery(INITIALIZE_COLOR_TURN_QUERY);
            ColorSymbol colorSymbol = ColorSymbol.getColorSymbolByColor(color);
            statement.setString(1, colorSymbol.getSymbol());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize() {
        executeInitializeQuery(INITIALIZE_COLOR_TURN_QUERY);
        executeInitializeQuery(INITIALIZE_BOARD_QUERY);
    }

    private void executeInitializeQuery(final String query) {
        try (var statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
