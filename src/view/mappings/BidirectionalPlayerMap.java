package view.mappings;

import javafx.scene.layout.StackPane;
import model.Colour;
import model.player.Player;

import java.util.HashMap;

public class BidirectionalPlayerMap {
    private final HashMap<Player, StackPane> playerToPane = new HashMap<>();
    private final HashMap<StackPane, Player> paneToPlayer = new HashMap<>();

    /**
     * Associates a player with its StackPane representation
     * @param player The player object
     * @param pane The StackPane displaying the player's components
     */
    public void put(Player player, StackPane pane) {
        playerToPane.put(player, pane);
        paneToPlayer.put(pane, player);
    }

    /**
     * Gets the StackPane for a specific player
     * @param player The player to look up
     * @return The StackPane representing this player
     */
    public StackPane getPane(Player player) {
        return playerToPane.get(player);
    }

    /**
     * Gets the Player associated with a StackPane
     * @param pane The StackPane to look up
     * @return The Player represented by this StackPane
     */
    public Player getPlayer(StackPane pane) {
        return paneToPlayer.get(pane);
    }

    /**
     * Gets the first player in the map
     * @return First player in the map or null if empty
     */
    public Player getPlayer() {
        if (playerToPane.isEmpty()) {
            return null;
        }
        return playerToPane.keySet().iterator().next();
    }
    public Colour getColour() {
        if (playerToPane.isEmpty()) {
            return null;
        }
        return playerToPane.keySet().iterator().next().getColour();
    }
    /**
     * Gets all StackPanes in the map
     * @return Array of all StackPanes in the map
     */
    public StackPane[] getAllPanes() {
        return paneToPlayer.keySet().toArray(new StackPane[0]);
    }

    /**
     * Gets the full mapping from players to stack panes
     * @return HashMap of Player to StackPane mappings
     */
    public HashMap<Player, StackPane> getPlayerToPane() {
        return playerToPane;
    }
    public HashMap<StackPane, Player> getPaneToPlayer() {
        return paneToPlayer;
    }
    public StackPane remove(Player player) {
        StackPane pane = playerToPane.get(player);
        if (pane != null) {
            playerToPane.remove(player);
            paneToPlayer.remove(pane);
        }
        return pane;
    }
    public Player remove(StackPane pane) {
        Player player = paneToPlayer.get(pane);
        if (player != null) {
            paneToPlayer.remove(pane);
            playerToPane.remove(player);
        }
        return player;
    }
}
