fun generateName(): String {
    val namesList: String = listOf(
        "Skelett", "Krieger", "Zombie", "Elf",
        "Reaper", "Assassine", "Bestie", "Viper",
        "Golem",
        ).random()
    return namesList
}
fun generateHP(): Int {
    return (175 .. 300).random()
}

fun generateAttacks(attacksCount: Int): Map<String, Int> {
    val attacks: MutableMap<String, Int> = mutableMapOf()
    for (i in 1 .. attacksCount) {
        val attackKey: String = listOf(
            "Schwert", "Axt", "Pfeil", "Armbrust",
            "Messer", "Dolch", "Speer", "Keule",
            "Schleuder", "Stein", "Schild", "Stab",
            "Schaufel", "Wurfstern", "Kriegshammer",
            "Kratzer", "Biss",
        ).random()
        val attackValue: Int = (20 .. 150).random()
        attacks.put(attackKey, attackValue)
    }
    return attacks
}