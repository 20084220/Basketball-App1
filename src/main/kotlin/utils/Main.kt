
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
         > |   5) List Player by teams      |
         > ----------------------------------
         > |   6) Search Player             |
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
    val noteTitle = readNextLine("Enter a title for the note: ")
    val notePriority = readNextInt("Enter a priority (1-low, 2, 3, 4, 5-high): ")
    val noteCategory = readNextLine("Enter a category for the note: ")
    val isAdded = noteAPI.add(Note(noteTitle, notePriority, noteCategory, false))

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}
fun save() {
    try {
        playerAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}
//------------------------------------
// PERSISTENCE METHODS for loading saving and exiting
// ------------------------------------

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

