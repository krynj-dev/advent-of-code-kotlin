package au.com.krynj.aoc.framework

import java.math.BigInteger

interface AoCObserver {
    fun notify(partialResult: BigInteger)
}