package utils

object PositionUtility {

    @JvmStatic
    val positions = setOf("PG", "SG", "SF", "PF", "C")  //add more categories in here.

    @JvmStatic
    fun isValidPosition(positionToCheck: String?): Boolean {
        for (position in positions) {
            if (position.equals(positionToCheck, ignoreCase = true)) {
                return true
            }
        }
return false
    }
}