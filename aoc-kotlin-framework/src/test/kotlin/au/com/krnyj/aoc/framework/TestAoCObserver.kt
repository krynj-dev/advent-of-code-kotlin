package au.com.krnyj.aoc.framework

import au.com.krynj.aoc.framework.AoCObserver
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.math.BigInteger

class TestAoCObserver(private val resultsQueue: MutableList<BigInteger>): AoCObserver {
    override fun notify(partialResult: BigInteger) {
        assertTrue(resultsQueue.size > 0)
        assertEquals(resultsQueue.removeFirst(), partialResult)
    }

}