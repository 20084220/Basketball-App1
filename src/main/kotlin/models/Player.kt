package models
data class Player(var playerName: String, var playerNum: Int, var team : String, var height: Int, var position: String, var retired :Boolean){
    override fun toString(): String {
        return "Player(\tplayer Name: '$playerName', \n\t player number: $playerNum,\n\t team: '$team',\n\t height:$height,\n\t position: '$position',\n\t retired:$retired)"
    }
}