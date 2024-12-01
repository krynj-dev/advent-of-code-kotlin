package au.com.krynj.aoc.framework

import java.math.BigInteger

interface AoCDay {
    fun run()
    fun getDay(): Int
    fun partOne(inputLines: List<String>): BigInteger
    fun partTwo(inputLines: List<String>): BigInteger
}