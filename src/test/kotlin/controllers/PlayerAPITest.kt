
package controllers

import controller.PlayerAPI
import models.Player
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class PlayerAPITest {

    private var KyrieIr: Player? = null
    private var LebronJames: Player? = null
    private var michealJordon: Player? = null
    private var populatedPlayers: PlayerAPI? = PlayerAPI()
    private var emptyPlayer: PlayerAPI? = PlayerAPI()

    @BeforeEach
    fun setup(){
        KyrieIr = Player("kyrie Irving", 11, "brooklyn nets", 193, "PG", false)
        LebronJames = Player("lebron James", 23, "LA Lakers", 203, "SF", false)
        michealJordon = Player("Micheal Jordon", 23, "chicago Bulls", 198, "SG", true)


        //adding 3 Note to the Player api
        populatedPlayers!!.add(KyrieIr!!)
        populatedPlayers!!.add(LebronJames!!)
        populatedPlayers!!.add(michealJordon!!)

    }

    @AfterEach
    fun tearDown(){
        KyrieIr = null
        LebronJames = null
        michealJordon = null
        populatedPlayers = null
        emptyPlayer = null
    }

    @Test
    fun `adding a Player to a populated list adds to ArrayList`(){
        val newPlayer = Player("Kevin durant", 7, "brooklyn nets", 210, "SF", false )
        assertTrue(populatedPlayers!!.add(newPlayer))
    }

    @Test
    fun `adding a Player to an empty list adds to ArrayList`(){
        val newPlayer = Player("Kevin durant", 7, "brooklyn nets", 210, "SF", false )
        assertTrue(populatedPlayers!!.add(newPlayer))
    }
}