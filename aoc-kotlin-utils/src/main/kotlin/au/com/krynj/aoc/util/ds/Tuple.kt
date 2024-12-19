package au.com.krynj.aoc.util.ds

/**
 * Like a pair but with three values. Can't believe I'm needing something like this enough to write it
 */
data class Tuple<out A, out B, out C>(val first: A, val second: B, val third: C)