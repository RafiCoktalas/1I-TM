package gameLogicManager.gameControllerManager;

import gameLogicManager.gameModel.gameBoard.*;
import gameLogicManager.gameModel.player.*;

/**
 * This class is to control the game flow.
 * It is a facade class for controllers to communicate with entities and UI.
 * @author Rafi Coktalas
 * @version 10.05.2020
 */
public class FlowManager{

    private static FlowManager uniqueInstance; //Singleton

    public static FlowManager getInstance(){
        if( uniqueInstance == null ){
            uniqueInstance = new FlowManager();
        }
        return uniqueInstance;
    }

    private FlowManager(){}

    // Controller Instances
    ResourceController resourceController = ResourceController.getInstance();
    ActionController actionController = ActionController.getInstance();
    AdjacencyController adjacencyController = AdjacencyController.getInstance();

    Player currentPlayer;

    public boolean transformTerrain(int terrainID) {
        Terrain terrain = getTerrain(terrainID); // getTerrain returns Terrain object from the given id.(DECIDE THE CLASS OF THE FUNCTION)

        /* Player cannot transform if the terrain is not available */
        if(!terrain.isAvailable()){
            return false;
        }

        /* Check if the player has enough workers to have enough spades, obtain spades if possible */
        if(!resourceController.obtainSpade(currentPlayer, terrain.getType())){
            return false;
        }

        TerrainType newTerrainType = currentPlayer.getFaction().getTerrainType();
        actionController.transformTerrain(terrain, newTerrainType);//this method will connect server for the update on terrain

        adjacencyController.updateAdjacencyList(currentPlayer, terrain);

        return true;
    }

    private Terrain getTerrain(int terrainID) {

        //TODO

        return null;
    }

}
