package controller

import models.Player


class PlayerAPI
{

    private var players = ArrayList<Player>()

  //crud methods add, delete update
    fun add(player: Player): Boolean {
        return players.add(player)
    }
    fun numberOfPlayers(): Int {
        return players.size
    }
    fun listAllPlayers(): String =
        if  (players.isEmpty()) "No players stored"
        else formatListString(players)

//search for my player by name
    fun searchByName (searchString : String) =
        formatListString(
            players.filter { player -> player.playerName.contains(searchString, ignoreCase = true) })
    //list the players by team
    fun listByTeam(teamName : String) =
        formatListString(
            players.filter { player -> player.team.equals(teamName, ignoreCase = true) })

    fun listByRetired () =
        formatListString(
            players.filter { player -> player.retired })

    //to-string in a nice way
    private fun formatListString(playerToFormat : List<Player>) :String =
        playerToFormat
            .joinToString (separator = "\n") { player ->
                players.indexOf(player).toString() + ": " + player.toString() }

}
