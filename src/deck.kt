import java.util.Random

/** Created by Broken on 11/5/17. */

private val rand = Random()

class Card constructor(private val faceValue: Int, private val suit: String) {
    val cardValue: Int = when (faceValue) {
        1 -> 11
        11 -> 10
        12 -> 10
        13 -> 10
        else -> faceValue
    }
    override fun toString(): String = when (faceValue) {
        1 -> "${suit}A"
        11 -> "${suit}J"
        12 -> "${suit}Q"
        13 -> "${suit}K"
        else -> "$suit$faceValue"
    }
}

class Deck {
    private var cards = mutableListOf<Card>()
    var discardPile = mutableListOf<Card>()
    init {
        setDeck()
        reshuffle()
    }

    private fun setDeck() {
        cards = mutableListOf()
        discardPile = mutableListOf()
        (1..13).mapTo(cards) { Card(it, "♠") }
        (1..13).mapTo(cards) { Card(it, "♥") }
        (1..13).mapTo(cards) { Card(it, "♣") }
        (1..13).mapTo(cards) { Card(it, "♦") }
    }

    override fun toString(): String = cards.toString()

    fun drawTopCard(): Card = cards.removeAt(0)

    fun burnCard() {
        discardPile.add(cards.removeAt(0))
    }

    fun reshuffle() {
        print("Shuffling...")
        for (i in 2 until cards.size) {
            cards.add(rand.nextInt(i), cards.removeAt(i))
        }
        print("Done\n\n")
    }
}

val deck = Deck()

class Hand constructor(numOfCards: Int = 5) {
    var cardsInHand = mutableListOf<Card>()
    fun drawCard() {
        cardsInHand.add(cardsInHand.size, deck.drawTopCard())
    }
    init {
        for (i in 1..numOfCards)
            drawCard()
    }
    fun discard() {
        deck.discardPile.add(cardsInHand.removeAt(0))
    }
    override fun toString(): String {
        var myCards = ""
        for (card in cardsInHand)
            myCards += "$card "
        return myCards
    }
}

fun main() {
    println("Omaha Hi-lo Split Simulation\n")
    val numPlayers = 6
    for (h in 1..numPlayers) {
        println("Player $h: ${Hand(4)}")
    }
    println("\nField:    ${Hand(5)}")
}
