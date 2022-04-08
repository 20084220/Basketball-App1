import controller.PlayerAPI
import models.Player
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine


var playerAPI: PlayerAPI()
fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1 -> addPlayer()
            2 -> listPlayers()
            3 -> updatePlayer()
            4 -> deletePlayer()
            5 -> listPlayerbyTeam()
            6 -> searchPlayer()
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
         > | NOTE MENU                      |
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
    val team = readNextLine("Enter the players team: ")
    val height = readNextInt("Enter the players height in measure:")
    val position = readNextLine("Enter the players position: ")
    val isAdded = playerAPI.add(Player(playerName, playerNum, team,height, position, false ))

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
                  >     2) View ALL Players listed by teams|
                  > ----------------------------------------
         > ==>> """.trimMargin(">"))

        when (option) {
            1 -> listAllPlayers()
            2 -> listByRetired()
            3 -> listByTeam()

            else -> println("Invalid option entered: " + option)
        }
    } else {
        println("Option Invalid - No notes stored")
    }
}

fun listAllPlayers() {
    println(playerAPI.listAllPlayers())
}
fun listPlayerbyTeam() {
    println(playerAPI.listByTeam())
}
fun save() {
    try {
        PlayerAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}
//------------------------------------
// PERSISTENCE METHODS for loading saving and exiting
// ------------------------------------

fun load() {
    try {
        PlayerAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}

fun exitApp() {
    println("Exiting...bye")
    System.exit(0)
}

