package au.com.krynj.aoc.framework

import java.math.BigInteger

interface AoCObserverContext {
    fun getPartialResult(): BigInteger
}