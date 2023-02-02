fun generateName(): String {
    val namesList: List<String> = listOf(
        "Skelett", "Krieger", "Zombie", "Elf",
        "Reaper", "Assassine", "Bestie", "Viper",
        "Golem",
        )
    return namesList.random()
}
fun generateHP(): Int {
    return (175 .. 300).random()
}