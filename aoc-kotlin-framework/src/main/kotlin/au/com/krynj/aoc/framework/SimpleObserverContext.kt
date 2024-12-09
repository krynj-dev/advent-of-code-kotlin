package au.com.krynj.aoc.framework

import java.math.BigInteger

class SimpleObserverContext(private val partialResult: BigInteger): AoCObserverContext {
    override fun getPartialResult(): BigInteger {
        return partialResult
    }
}