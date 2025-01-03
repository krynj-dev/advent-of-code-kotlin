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

    fun readResourceFile(fileName: String, delimiter: String): List<List<String>> {
        val lines = readResourceFile(fileName)
        val sections: MutableList<List<String>> = ArrayList()
        var section: MutableList<String> = ArrayList()
        lines.forEach {
            if (it == delimiter) {
                sections.add(section)
                section = ArrayList()
            } else section.add(it)
        }
        sections.add(section)
        return sections
    }

    fun readLinesAsInt(lines: List<String>, delimiter: Char): List<List<Int>> {
        return lines.map { readLineAsInt(it, delimiter) }
    }

    fun readLinesAsInt(lines: List<String>, delimiter: String): List<List<Int>> {
        return lines.map { readLineAsInt(it, delimiter) }
    }

    fun readLinesAsInt(lines: List<String>): List<List<Int>> {
        return lines.map { readLineAsInt(it) }
    }

    fun readLinesAsBigInt(lines: List<String>, delimiter: Char): List<List<BigInteger>> {
        return lines.map { readLineAsBigInt(it, delimiter) }
    }

    fun readLinesAsBigInt(lines: List<String>, delimiter: String): List<List<BigInteger>> {
        return lines.map { readLineAsBigInt(it, delimiter) }
    }
    inline fun <reified T> transpose(xs: List<List<T>>): List<List<T>> {
        val cols = xs[0].size
        val rows = xs.size
        return List(cols) { j ->
            List(rows) { i ->
                xs[i][j]
            }
        }
    }

    fun readLineAsInt(line: String, digits: Boolean = true): List<Int> {
        return """(\d${if (digits) ")" else """+)(?=\s|${'$'})"""}""".toRegex().findAll(line).map { it.groupValues[1].toInt() }.toList()
    }

    fun readLineAsInt(line: String, delimiter: Char): List<Int> {
        return line.split(delimiter).map { it.toInt() }
    }

    fun readLineAsInt(line: String, delimiter: String): List<Int> {
        return line.split(delimiter).map { it.toInt() }
    }

    fun readLineAsBigInt(line: String, delimiter: Char): List<BigInteger> {
        return line.split(delimiter).map { BigInteger(it) }
    }

    fun readLineAsBigInt(line: String, delimiter: String): List<BigInteger> {
        return line.split(delimiter).map { BigInteger(it) }
    }

    fun readLineAsFloat(line: String, delimiter: Char): List<BigDecimal> {
        return line.split(delimiter).map { BigDecimal(it) }
    }

    fun readLineAsFloat(line: String, delimiter: String): List<BigDecimal> {
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