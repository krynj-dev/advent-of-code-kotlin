package au.com.krynj.aoc.framework

import java.math.BigInteger

class PrintfAoCObserver(private val formatString: String): AoCObserver {
    override fun notify(partialResult: BigInteger) {
        println(String.format(formatString, partialResult))
    }
}