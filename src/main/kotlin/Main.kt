import kotlin.math.sqrt

fun main() {
    while (true) {
        println(
            """
            |==============================
            |  Lab work #0
            |  Choose a task:
            |==============================
            |  1  - Sum of first and last digit
            |  2  - Input numbers until 0 (count, sum, average)
            |  3  - "Guess the number" game
            |  4  - First n prime numbers
            |  5  - Array elements greater than neighbors
            |  6  - Product, min, max of array (5 ways)
            |  7  - Quadratic equation
            |  8  - Class with an array
            |  9  - Vector class (3D)
            |  10 - Vehicle hierarchy
            |  0  - Exit
            |==============================
            """.trimMargin()
        )
        print("Your choice: ")
        when (readln().toIntOrNull()) {
            1 -> task01()
            2 -> task02()
            3 -> task03()
            4 -> task04()
            5 -> task05()
            6 -> task06()
            7 -> task07()
            8 -> task08()
            9 -> task09()
            10 -> task10()
            0 -> {
                println("Exiting the program.")
                return
            }
            else -> println("Invalid input. Try again.\n")
        }
        println()
    }
}

// ============================================================
// Task 1. Sum of the first and last digit of a number
// ============================================================
fun task01() {
    println("--- Task 1 ---")
    println("Enter a positive integer:")
    val input = readln()

    if (input.isEmpty() || !input.all { it.isDigit() }) {
        println("Invalid input.")
        return
    }

    val number = input.toInt()

    // Method 1: using strings (first / last)
    val sumStr = input.first().digitToInt() + input.last().digitToInt()
    println("Method 1 (strings): sum of first and last digit = $sumStr")

    // Method 2: using arithmetic (% and /)
    val lastDigit = number % 10
    var firstDigit = number
    while (firstDigit >= 10) {
        firstDigit /= 10
    }
    println("Method 2 (arithmetic): sum of first and last digit = ${firstDigit + lastDigit}")
}

// ============================================================
// Task 2. Input numbers until 0
// ============================================================
fun task02() {
    println("--- Task 2 ---")
    var count = 0
    var sum = 0.0
    println("Enter numbers one by one (0 — to finish):")

    while (true) {
        val num = readln().toDoubleOrNull() ?: continue
        if (num == 0.0) break
        count++
        sum += num
    }

    println("Count of entered numbers: $count")
    println("Sum: $sum")
    if (count > 0) println("Average: ${sum / count}")
    else println("No numbers were entered")
}

// ============================================================
// Task 3. "Guess the number" game
// ============================================================
fun task03() {
    println("--- Task 3 ---")
    val a = (0..10).random()
    println("The program has picked a number from 0 to 10. Try to guess:")

    while (true) {
        val b = readln().toIntOrNull() ?: continue
        when {
            b > a -> println("Too much")
            b < a -> println("Too little")
            else -> {
                println("Guessed!")
                break
            }
        }
    }
}

// ============================================================
// Task 4. First n prime numbers
// ============================================================
fun isPrime(x: Int): Boolean {
    if (x < 2) return false
    var i = 2
    while (i * i <= x) {
        if (x % i == 0) return false
        i++
    }
    return true
}

fun task04() {
    println("--- Task 4 ---")
    println("Enter the count of prime numbers n:")
    val n = readln().toIntOrNull() ?: return

    if (n < 1) {
        println("n must be >= 1")
        return
    }

    var count = 0
    var num = 2
    while (count < n) {
        if (isPrime(num)) {
            count++
            println("Prime #$count: $num")
        }
        num++
    }
}

// ============================================================
// Task 5. Array elements greater than their neighbors
// ============================================================
fun task05() {
    println("--- Task 5 ---")
    val arr = intArrayOf(1, 5, 3, 7, 2, 9, 4, 6, 8)
    println("Array: ${arr.joinToString()}")

    println("Elements greater than their neighbors:")
    var found = false
    for (i in 1 until arr.size - 1) {
        if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
            println(arr[i])
            found = true
        }
    }
    if (!found) println("No such elements")
}

// ============================================================
// Task 6. Product, min, max of array — 5 ways
// ============================================================
fun task06() {
    println("--- Task 6 ---")
    val arr = intArrayOf(2, 5, 3, 8, 1, 4)
    println("Array: ${arr.joinToString()}")

    // 1. for loop
    var productFor = 1L
    var minFor = arr[0]
    var maxFor = arr[0]
    for (el in arr) {
        productFor *= el
        if (el < minFor) minFor = el
        if (el > maxFor) maxFor = el
    }
    println("for:     product=$productFor, min=$minFor, max=$maxFor")

    // 2. while loop
    var productWhile = 1L
    var minWhile = arr[0]
    var maxWhile = arr[0]
    var i = 0
    while (i < arr.size) {
        productWhile *= arr[i]
        if (arr[i] < minWhile) minWhile = arr[i]
        if (arr[i] > maxWhile) maxWhile = arr[i]
        i++
    }
    println("while:   product=$productWhile, min=$minWhile, max=$maxWhile")

    // 3. forEach
    var productEach = 1L
    var minEach = Int.MAX_VALUE
    var maxEach = Int.MIN_VALUE
    arr.forEach { el ->
        productEach *= el
        if (el < minEach) minEach = el
        if (el > maxEach) maxEach = el
    }
    println("forEach: product=$productEach, min=$minEach, max=$maxEach")

    // 4. reduce() + min()/max()
    val productReduce = arr.reduce { acc, el -> acc * el }
    println("reduce:  product=$productReduce, min=${arr.min()}, max=${arr.max()}")

    // 5. Only min() and max()
    println("min()/max(): min=${arr.min()}, max=${arr.max()}")
}

// ============================================================
// Task 7. Quadratic equation
// ============================================================
fun sqr(n: Double): Double = n * n

fun discriminant(a: Double, b: Double, c: Double): Double = sqr(b) - 4 * a * c

fun rootsNumber(a: Double, b: Double, c: Double): Int {
    val d = discriminant(a, b, c)
    return when {
        d > 0 -> 2
        d == 0.0 -> 1
        else -> 0
    }
}

fun quadraticRoot(a: Double, b: Double, c: Double) {
    val d = discriminant(a, b, c)
    when (rootsNumber(a, b, c)) {
        2 -> {
            val x1 = (-b + sqrt(d)) / (2 * a)
            val x2 = (-b - sqrt(d)) / (2 * a)
            println("Two roots: x1 = $x1, x2 = $x2")
        }
        1 -> {
            val x = -b / (2 * a)
            println("One root: x = $x")
        }
        else -> println("No roots (D < 0)")
    }
}

fun task07() {
    println("--- Task 7 ---")
    println("Enter coefficients a, b, c (one per line):")
    val a = readln().toDoubleOrNull() ?: return
    val b = readln().toDoubleOrNull() ?: return
    val c = readln().toDoubleOrNull() ?: return

    if (a == 0.0) {
        println("This is not a quadratic equation (a = 0)")
        return
    }
    quadraticRoot(a, b, c)
}

// ============================================================
// Task 8. Class with an array
// ============================================================
class ArrayProcessor(private val arr: IntArray) {

    fun sumPositive(): Int {
        var sum = 0
        for (el in arr) if (el > 0) sum += el
        return sum
    }

    fun product(): Long {
        var p = 1L
        for (el in arr) p *= el
        return p
    }

    fun average(): Double {
        if (arr.isEmpty()) return 0.0
        return arr.sum().toDouble() / arr.size
    }
}

fun task08() {
    println("--- Task 8 ---")
    val arr = intArrayOf(1, -2, 3, -4, 5)
    println("Array: ${arr.joinToString()}")

    val processor = ArrayProcessor(arr)
    println("Sum of positive elements: ${processor.sumPositive()}")
    println("Product:                  ${processor.product()}")
    println("Average:                  ${processor.average()}")
}

// ============================================================
// Task 9. Vector class (3D)
// ============================================================
class Vector(val x: Double, val y: Double, val z: Double) {

    // Vector length
    fun length(): Double = sqrt(x * x + y * y + z * z)

    // Dot product (method)
    fun dot(other: Vector): Double = x * other.x + y * other.y + z * other.z

    // Infix function
    infix fun scalar(other: Vector): Double = this.dot(other)

    // Operator * overload
    operator fun times(other: Vector): Double = this.dot(other)

    override fun toString(): String = "Vector($x, $y, $z)"
}

// External function for dot product
fun scalarProduct(v1: Vector, v2: Vector): Double =
    v1.x * v2.x + v1.y * v2.y + v1.z * v2.z

fun task09() {
    println("--- Task 9 ---")
    val v1 = Vector(1.0, 2.0, 3.0)
    val v2 = Vector(3.0, 2.0, 1.0)

    println("v1 = $v1")
    println("v2 = $v2")
    println("Length of v1:                ${v1.length()}")
    println("dot() method:                ${v1.dot(v2)}")
    println("infix (v1 scalar v2):        ${v1 scalar v2}")
    println("operator * (v1 * v2):        ${v1 * v2}")
    println("External function:           ${scalarProduct(v1, v2)}")
}

// ============================================================
// Task 10. Vehicle hierarchy
// ============================================================
open class Vehicle {
    open val name: String = "Vehicle"
    open val speed: Int = 0

    open fun start() {
        println("$name started moving at $speed km/h")
    }

    open fun stop() {
        println("$name stopped")
    }
}

class Boat : Vehicle() {
    override val name = "Boat"
    override val speed = 30

    override fun start() = println("$name started moving at $speed km/h")
    override fun stop() = println("$name stopped")
}

class Plane : Vehicle() {
    override val name = "Plane"
    override val speed = 800

    override fun start() = println("$name started moving at $speed km/h")
    override fun stop() = println("$name stopped")
}

class Tank : Vehicle() {
    override val name = "Tank"
    override val speed = 50

    override fun start() = println("$name started moving at $speed km/h")
    override fun stop() = println("$name stopped")
}

fun task10() {
    println("--- Task 10 ---")
    val vehicles: List<Vehicle> = listOf(Boat(), Plane(), Tank())
    for (v in vehicles) {
        v.start()
        v.stop()
        println()
    }
}