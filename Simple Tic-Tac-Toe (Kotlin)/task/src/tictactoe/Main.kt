package tictactoe
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val input = "_________".toCharArray()

    println("> $input")
    println("---------")
    println("| ${input[0]} ${input[1]} ${input[2]} |")
    println("| ${input[3]} ${input[4]} ${input[5]} |")
    println("| ${input[6]} ${input[7]} ${input[8]} |")
    println("---------")
    scanner.close()

    var oWins = false
    val oCount = input.count { it == 'O'}
    var xWins = false
    val xCount = input.count { it == 'X'}
    val winningTriples = listOf(
        // HORIZONTAL
        listOf(0, 1, 2),
        listOf(3, 4 ,5),
        listOf(6, 7 ,8),
        // VERTICAL
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8),
        // DIAGONAL
        listOf(0, 4 ,8),
        listOf(2, 4 ,6)
    )
    // Check if input is Int or not
    var row: Int = 0
    var col: Int = 0
    var turnOfX = true


    do {
        var validInput = false
        do {
            try {
                val rowAndColumn = readln().split(" ").map { it.toInt() }
                row = rowAndColumn[0]
                col = rowAndColumn[1]
            } catch (e: Exception) {
                println("You should enter numbers!")
                continue
            }

            val coordinates = (row - 1) * 3 + (col - 1) // Converts coordinates to index

            if(row > 3 || row < 1 || col > 3 || col < 1) {
                println("Coordinates should be from 1 to 3!")
            } else if(input[coordinates] == 'X' || input[coordinates] == 'O') {
                println("This cell is occupied! Choose another one!")
            } else {
                validInput = true
                input[coordinates] = if(turnOfX) 'X' else 'O'
                if(turnOfX) {
                    turnOfX = false
                } else {
                    turnOfX = true
                }
            }
        } while (!validInput)


        println("> $row $col")
        println("---------")
        println("| ${input[0]} ${input[1]} ${input[2]} |")
        println("| ${input[3]} ${input[4]} ${input[5]} |")
        println("| ${input[6]} ${input[7]} ${input[8]} |")
        println("---------")



        // GAME STATE WINNER X
        for(triples in winningTriples) {

            val a = triples[0]
            val b = triples[1]
            val c = triples[2]

            if(input[a] == input[b] && input[b] == input[c] && input[a] != '_' && input[a] == 'X') {
                xWins = true
            } else if(input[a] == input[b] && input[b] == input[c] && input[a] != '_' && input[a] == 'O') {
                oWins = true
            }
        }

        // GAME STATE NOT FINISHED
        if(input.contains('_') && !oWins && !xWins && xCount == oCount + 1 || xCount == oCount) println("Game not finished")

        // GAME STATE BOTH IMPOSSIBLE
        if(xWins && oWins || input.contains('_') && !xWins && !oWins && xCount != oCount || xCount < oCount) println("Impossible")

        // GAME STATE DRAW
        if(!input.contains('_') && !oWins && !xWins) println("Draw")

        // GAME WINNER
        if(xWins && !oWins) println("X wins")
        if(oWins && !xWins) println("O wins")

    } while (!xWins && !oWins && input.contains('_'))



}