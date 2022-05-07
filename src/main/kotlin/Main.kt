import controller.PlayerAPI

import models.Player
import persistence.JSONSerializer
import utils.ScannerInput
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import utils.TeamUtility
import java.io.File

//private val playerAPI = PlayerAPI(XMLSerializer(File("players.xml")))
private val playerAPI = PlayerAPI(JSONSerializer(File("players.json")))
//var playerAPI: PlayerAPI()
fun main(args: Array<String>) {
    runMenu()
}

fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1 -> addPlayer()
            2 -> listPlayers()
            3 -> updatePlayer()
            4 -> deletePlayer()
            5 -> searchPlayer()
            20 -> save()
            21 -> load()
            0 -> exitApp()
            else -> System.out.println("Invalid menu choice: ${option}")
        }
    } while (true)
}
fun mainMenu(): Int {
    return readNextInt(
        """ 
         > ----------------------------------
         > |        BASKETBALL APP          |
         > ----------------------------------
         > |    PLayer MENU                 |
         > |   1) Add a Player              |
         > |   2) List players              |
         > |   3) Update a player           |
         > |   4) Delete a player           |
         > ----------------------------------
         > |   5) Search Player             |
         > ----------------------------------
         > |   20) Save player              |
         > |   21) Load player              |
         > |   0) Exit                      |
         > ----------------------------------
         > ==>> """.trimMargin(">")
    )
}

fun addPlayer() {
    //logger.info { "addNote() function invoked" }
    val playerName = readNextLine("Enter the players name: ")
    val playerNum = readNextInt("Enter the players number: ")
    print(TeamUtility.listofTeam())
    var team = readNextLine("Enter the players team: ")
    while(!TeamUtility.isValidTeam(team))
        team = readNextLine("NOT Valid - Enter the players team: "
    val height = readNextInt("Enter the players height in measure:")
    print(PositionUtility.listofPositions())
    var position = readNextLine("Enter the players position: ")
    while(!PositionUtility.isValidPosition(position))
        position = readNextLine("NOT Valid - Enter the players position: ")
    val retired = readNextLine("Is PLAYER RETIRED: ")
    var isRetired = false
    if (retired.uppercase().startsWith("Y"))
        isRetired = true

    val isAdded = playerAPI.add(Player(playerName, playerNum, team,height, position, isRetired ))

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}
fun listPlayers() {
    if (playerAPI.numberOfPlayers() > 0) {
        val option = readNextInt(
            """
                  > ----------------------------------------
                  > |   1) View ALL Players                |
                  > |   2) View ALL retired Players        |
                  >     3) View ALL Players listed by teams|
                  > ----------------------------------------
         > ==>> """.trimMargin(">"))

        when (option) {
            1 -> listAllPlayers()
            2 -> listByRetired()
            3 -> listByTeam()
            else -> println("Invalid option entered: " + option)
        }
    } else {
        println("Option Invalid - No Player stored")
    }
}
fun listByRetired() {
    println(playerAPI.listByRetired())
}
fun listAllPlayers() {
    println(playerAPI.listAllPlayers())
}
fun listByTeam() {
    val team = readNextLine("Enter the players team: ")
    println(playerAPI.listByTeam(team))
}

fun searchPlayer() {
    val searchByName = readNextLine("Enter Player name to search: ")
    val searchResults = playerAPI.searchByName(searchByName)
    if (searchResults.isEmpty()) {
        println("No Player found")
    } else {
        println(searchResults)
    }
}

fun updatePlayer() {

    listPlayers()
    if (playerAPI.numberOfPlayers() > 0) {
        //only ask the user to choose the note if notes exist
        val indexToUpdate = readNextInt("Enter the index of the player to update: ")
        if (playerAPI.isValidIndex(indexToUpdate)) {
            val playerName = readNextLine("Enter name of the Player: ")
            val playerNum = readNextInt("Enter Player number: ")
            print(TeamUtility.listofTeam())
            val team = readNextLine("Enter the players Team: ")
            val height  = readNextInt("Enter the players height in cm: ")
            val position = readNextLine("Enter the players positions: ")
            val retired = readNextLine("Is PLAYER RETIRED: ")
            var isRetired = false
            if (retired.uppercase().startsWith("Y"))
                isRetired = true

            //pass the index of the note and the new note details to NoteAPI for updating and check for success.
            if (playerAPI.updatePlayer(indexToUpdate, Player(playerName, playerNum, team, height, position,isRetired))) {
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("There are no players for this index number")
        }
    }
}

fun deletePlayer() {

    listPlayers()
    if (playerAPI.numberOfPlayers() > 0) {
        //only ask the user to choose the note to delete if notes exist
        val indexToDelete = readNextInt("Enter the index of the player you want to delete: ")
        //pass the index of the note to NoteAPI for deleting and check for success.
        val playerToDelete = playerAPI.deletePlayer(indexToDelete)
        if (playerToDelete != null) {
            println("Delete Successful! Deleted player: ${playerToDelete.playerName}")
        } else {
            println("Delete NOT Successful")
        }
    }
}

//------------------------------------
// PERSISTENCE METHODS for loading saving and exiting
// ------------------------------------

fun save() {
    try {
        playerAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

fun load() {
    try {
        playerAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}

fun exitApp() {
    println("Exiting...bye")
    System.exit(0)
}


