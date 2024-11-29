package au.com.krynj.aoc.util

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.math.BigDecimal
import java.math.BigInteger

object AoCUtil {

    val numberWords: Set<Pair<Char, String>> = setOf(
        Pair('0', "zero"),
        Pair('1', "one"),
        Pair('2', "two"),
        Pair('3', "three"),
        Pair('4', "four"),
        Pair('5', "five"),
        Pair('6', "six"),
        Pair('7', "seven"),
        Pair('8', "eight"),
        Pair('9', "nine"),
    )

    fun readResourceFile(fileName: String): List<String> {
        // Using the ClassLoader to load the resource file
        val inputStream: InputStream? = object {}.javaClass.classLoader.getResourceAsStream(fileName)

        // Check if the file exists
        if (inputStream != null) {
            val reader = BufferedReader(InputStreamReader(inputStream))
            return reader.readLines()
        } else {
            throw IllegalArgumentException("File not found: $fileName")
        }
    }

    fun readLineAsInt(line: String, delimiter: Char): List<BigInteger> {
        return line.split(delimiter).map { BigInteger(it) }
    }

    fun readLineAsFloat(line: String, delimiter: Char): List<BigDecimal> {
        return line.split(delimiter).map { BigDecimal(it) }
    }

    fun digitStringToInt(numString: String): BigInteger? {
        val matchingNumbers = numberWords.filter { it.second == numString }
        if (matchingNumbers.isNotEmpty())
            return BigInteger(matchingNumbers.first().first.toString())
        return null
    }

    fun digitIntToString(numInt: BigInteger): String? {
        val matchingNumbers = numberWords.filter { BigInteger(it.first.toString()) == numInt }
        if (matchingNumbers.isNotEmpty())
            return matchingNumbers.first().second
        return null
    }

    fun getStringDigits(): List<Char> {
        return numberWords.map { it.first }
    }

    fun getDigitWords(): List<String> {
        return numberWords.map { it.second }
    }

}