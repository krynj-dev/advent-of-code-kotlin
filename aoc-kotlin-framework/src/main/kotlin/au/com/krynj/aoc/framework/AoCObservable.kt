package au.com.krynj.aoc.framework

import java.math.BigInteger

interface AoCObservable {
    fun addObserver(observer: AoCObserver)

    fun broadcast(partialResult: BigInteger)
}