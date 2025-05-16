package view.mappings;

import engine.board.Cell;
import javafx.scene.layout.StackPane;

import java.util.HashMap;

public class BidirectionalCellMap {
    private final HashMap<Cell, StackPane> cellToPane = new HashMap<>();
    private final HashMap<StackPane, Cell> paneToCell = new HashMap<>();

    public void put(Cell cell, StackPane pane) {
        cellToPane.put(cell, pane);
        paneToCell.put(pane, cell);
    }

    public StackPane getPane(Cell cell) {
        return cellToPane.get(cell);
    }

    public Cell getCell(StackPane pane) {
        return paneToCell.get(pane);
    }

    public HashMap<Cell, StackPane> getCellToPane() {
        return cellToPane;
    }

    public HashMap<StackPane, Cell> getPaneToCell() {
        return paneToCell;
    }

    public int getCellSize() {
        if (cellToPane.isEmpty()) {
            return 0; // or throw an exception
        }
        StackPane firstPane = cellToPane.values().iterator().next();
        return (int) firstPane.getWidth(); // Assuming all panes have the same size
    }
}
