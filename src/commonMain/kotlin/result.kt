val options = arrayOf("Rock", "Paper", "Scissors")

fun getGameChoice (option: Array<String>, random: Int) = option[random]

fun getResult (userChoice: String) : String {
    var random = (0..2).random()
    val gameChoice = getGameChoice(options, random)
    return if (userChoice == gameChoice) {
        "Tie"
    }
    else if (((userChoice == "Rock") && (gameChoice == "Scissors")) ||
        ((userChoice == "Paper") && (gameChoice == "Rock")) ||
        ((userChoice === "Scissors") && (gameChoice == "Paper"))) "You win!"
    else "You lose!"
}

