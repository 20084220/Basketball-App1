package utils

object TeamUtility {
    @JvmStatic
    val teams = setOf ("Warriors", "Celtics", "Lakers", "Nets", "Suns", "Raptors", "Bucks", "76ers", "Bulls", "Heat", "Mavericks", "TimberWolves", "Grizzlies", "Pelicans", "Nuggets", "Hawks", "Jazz", "Spurs", "Knicks", "Cavaliers", "Hornets", "Clippers", "Kings", "Rockets", "Thunder", "Wizards", "Pistons"
        , "Pacers", "Magic")  //add more categories in here.

    @JvmStatic
    fun isValidTeam(teamToCheck: String?): Boolean {
       for (team in teams) {
           if (team.equals(teamToCheck, ignoreCase = true)) {
                return true
            }
       }
       return false
    }
}