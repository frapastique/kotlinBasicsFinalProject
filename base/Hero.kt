open class Hero(name: String, hp: Int) : Combatant(name, hp) {
    open fun useItem(item: Item) {}
    fun move(direction: String) {}
}