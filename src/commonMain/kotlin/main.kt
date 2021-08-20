import com.soywiz.korge.Korge
import com.soywiz.korge.input.Gestures
import com.soywiz.korge.input.onClick
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.font.BitmapFont
import com.soywiz.korim.font.readBitmapFont
import com.soywiz.korim.format.readBitmap
import com.soywiz.korim.text.TextAlignment
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.vector.roundRect
import getResult

var gameResult = 0
var userResult = 0

var userChoice = ""

fun Container.printButton(leftIndent: Double, cellPosition: Double, cellSize: Double, font: BitmapFont, text: String) {
    val background = graphics {
        position(leftIndent + 10, cellPosition + 10)
        fill(Colors["#97C1A9"]) {
            roundRect(0.0, 0.0, cellSize, cellSize / 2, rx = 5.0)
        }
    }
    text("$text", cellSize * 0.3, Colors.WHITE, font, alignment = TextAlignment.MIDDLE_CENTER).centerOn(background)
}


suspend fun main() = Korge(width = 480, height = 640, title = "Rock Paper Scissors", bgcolor = Colors["#CCE2CB"]) {
    val font = resourcesVfs["clear_sans.fnt"].readBitmapFont()

    val cellSize = views.virtualWidth / 4.0

    val fieldSize = 40 + 3 * cellSize
    val leftIndent = (views.virtualWidth - fieldSize) / 2

    val topIndent = 150.0

    val bgField = roundRect(fieldSize, fieldSize, 5.0, fill = Colors["#B6CFB6"]) {
        position(leftIndent, topIndent)
    }

    val cellPosition = topIndent + (fieldSize - cellSize) / 2
    text("Please choose one", cellSize * 0.4, Colors["#97C1A9"], font, alignment = TextAlignment.BASELINE_CENTER) {
        centerXOn(bgField)
        alignTopToTopOf(bgField, 30.0)
    }
    text("of the following:", cellSize * 0.4, Colors["#97C1A9"], font, alignment = TextAlignment.BASELINE_CENTER) {
        centerXOn(bgField)
        alignTopToTopOf(bgField, 60.0)
    }

    val rock = container {
        val rockContainer = this
        val button = container {
            printButton(leftIndent, cellPosition, cellSize, font, "rock")
            onClick {
                println(5555)
                userChoice = "Rock"
                val bgFieldTop =
                    rockContainer.roundRect(fieldSize, fieldSize, 5.0, fill = RGBA(0, 0, 0, 150)) {
                        position(leftIndent, topIndent)

                    }
                val result = getResult(userChoice)
                rockContainer.text(
                    "$result",
                    cellSize * 0.5,
                    Colors["#97C1A9"],
                    font,
                    alignment = TextAlignment.BASELINE_CENTER
                ) {
                    centerXOn(bgFieldTop)
                    alignBottomToBottomOf(bgFieldTop, 40.0)
                }
            }
        }
    }
    val paper = container {
        val paperContainer = this
        val button = container {
            printButton(leftIndent + cellSize + 10, cellPosition, cellSize, font, "paper")
            onClick {
                userChoice = "Paper"
                val bgFieldTop =
                    paperContainer.roundRect(fieldSize, fieldSize, 5.0, fill = RGBA(0, 0, 0, 150)) {
                        position(leftIndent, topIndent)
                    }
                val result = getResult(userChoice)
                paperContainer.text(
                    "$result",
                    cellSize * 0.4,
                    Colors["#97C1A9"],
                    font,
                    alignment = TextAlignment.BASELINE_CENTER
                ) {
                    centerXOn(bgFieldTop)
                    alignBottomToBottomOf(bgFieldTop, 30.0)
                }
                paperContainer.printButton(leftIndent, cellPosition, cellSize, font, "rock")
            }
        }
    }


    val scissors = container {
        val scissorContainer = this
        val button = container {
            printButton(leftIndent + (cellSize + 10) * 2, cellPosition, cellSize, font, "scissors")
            onClick {
                userChoice = "Scissors"
                val bgFieldTop = scissorContainer.roundRect(fieldSize, fieldSize, 5.0, fill = RGBA(0, 0, 0, 150)) {
                    position(leftIndent, topIndent)
                }
                val result = getResult(userChoice)
                scissorContainer.text("$result", cellSize * 0.4, Colors["#97C1A9"], font, alignment = TextAlignment.BASELINE_CENTER) {
                    centerXOn(bgFieldTop)
                    alignBottomToBottomOf(bgFieldTop, 40.0)
                }
                scissorContainer.printButton(leftIndent, cellPosition, cellSize, font, "rock")
                scissorContainer.printButton(leftIndent + cellSize + 10, cellPosition, cellSize, font, "paper")
            }
        }
    }
    val bgLogo = roundRect(cellSize * 1.05, cellSize * 1.05, 5.0, fill = Colors["#97C1A9"]) {
        position(leftIndent, 20.0)
    }
    text("Rock\nPaper\nScissors", cellSize * 0.3, Colors.WHITE, font).centerOn(bgLogo)

    val bgScore = roundRect(cellSize * 1.5, cellSize * 0.7, 5.0, fill = Colors["#97C1A9"]) {
        alignRightToRightOf(bgField)
        alignTopToTopOf(bgLogo)
    }
    text("SCORE", cellSize * 0.25, Colors["#CCE2CB"], font) {
        centerXOn(bgScore)
        alignTopToTopOf(bgScore, 3)
    }
    text("you - i", cellSize * 0.20, Colors["#CCE2CB"], font) {
        centerXOn(bgScore)
        alignTopToTopOf(bgScore, 25.0)
    }
    text("$userResult : $gameResult", cellSize * 0.4, Colors.WHITE, font) {
        setTextBounds(Rectangle(0.0, 0.0, bgScore.width, cellSize - 24.0))
        alignment = TextAlignment.MIDDLE_CENTER
        centerXOn(bgScore)
        alignTopToTopOf(bgScore, 16.0)
    }

    val restartImg = resourcesVfs["restart.png"].readBitmap()
    val btnSize = cellSize * 0.3
    val restartBlock = container {
        val background = roundRect(btnSize, btnSize, 5.0, fill = Colors["#97C1A9"])
        image(restartImg) {
            size(btnSize * 0.8, btnSize * 0.8)
            centerOn(background)
        }
        alignTopToBottomOf(bgScore, 5)
        alignRightToRightOf(bgField)
    }


}