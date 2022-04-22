package controller

import models.Player
import persistence.Serializer
import utils.Utilities


class PlayerAPI(serializerType: Serializer)
{
    private var serializer: Serializer = serializerType
    private var players = ArrayList<Player>()

  //crud methods add, delete update
    fun add(player: Player): Boolean {
        return players.add(player)
    }


    fun deletePlayer(indexToDelete: Int): Player? {
        return if (Utilities.isValidListIndex(indexToDelete, players)) {
            players.removeAt(indexToDelete)
        } else null
    }


    fun updatePlayer(indexToUpdate: Int, player: Player?): Boolean {
        //find the player object by the index number
        val foundPlayer = findPlayer(indexToUpdate)

        //if the player exists, use the player details passed as parameters to update the found note in the ArrayList.
        if ((foundPlayer != null) && (player != null)) {
            foundPlayer.playerName = player.playerName
            foundPlayer.playerNum = player.playerNum
            foundPlayer.team = player.team
            foundPlayer.height = player.height
            foundPlayer.position = player.position
            foundPlayer.retired = player.retired
            store()
            return true
        }
         return false
    }

    //others---------------------------------------------------------------------------------
    fun numberOfPlayers(): Int {
        return players.size
    }

//search for my player by name--------------------------------------------------------------------------------
    fun searchByName (searchString : String) =
        formatListString(
            players.filter { player -> player.playerName.contains(searchString, ignoreCase = true) })


    fun isValidIndex(index: Int) :Boolean {
        return Utilities.isValidListIndex(index, players);
    }

        fun findPlayer(index: Int): Player? {
            return if (Utilities.isValidListIndex(index, players)) {
                players[index]
            } else null
        }
    //list the players by team-----------------------------------------------------------------
    fun listByTeam(teamName : String) =
        formatListString(
            players.filter { player -> player.team.equals(teamName, ignoreCase = true) })

    fun listByRetired() =
        formatListString(
            players.filter { player -> player.retired})
    fun listAllPlayers(): String =
        if  (players.isEmpty()) "No players stored"
        else formatListString(players)




    //to-string in a nice way------------------------------------------------------------
    private fun formatListString(playerToFormat : List<Player>) :String =
        playerToFormat
            .joinToString (separator = "\n") { player ->
                players.indexOf(player).toString() + ": " + player.toString() }
    //----------------------------------------------
//  PERSISTENCE METHODS
//----------------------------------------------
    @Throws(Exception::class)
    fun load() {
        players = serializer.read() as ArrayList<Player>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(players)
    }
}


