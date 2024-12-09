package au.com.krnyj.aoc.framework

import au.com.krynj.aoc.framework.AoCObserver
import au.com.krynj.aoc.framework.AoCObserverContext
import au.com.krynj.aoc.framework.SimpleObserverContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.math.BigInteger

class TestAoCObserver(private val resultsQueue: MutableList<BigInteger>): AoCObserver<AoCObserverContext> {
    override fun notify(context: AoCObserverContext) {
        assertTrue(resultsQueue.size > 0)
        assertEquals(resultsQueue.removeFirst(), context.getPartialResult())
    }

}