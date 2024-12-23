package au.com.krynj.aoc.framework

import java.math.BigInteger

interface AoCDay<T, U> {
    fun run()
    fun getDay(): Int
    fun partOne(inputLines: T): U
    fun partTwo(inputLines: T): U
}