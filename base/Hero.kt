abstract class Hero(name: String, hp: Int) : Combatant(name, hp) {
    abstract val hasMana: Boolean
    abstract fun useSpecialMove()
    fun move(direction: String) {}
    //    abstract fun useItem(item: Item)
}