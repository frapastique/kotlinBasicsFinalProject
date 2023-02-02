abstract class Combatant(var name: String, var hp: Int) {
    abstract val attacks: Map<String, Int>
    abstract val hasMana: Boolean

    abstract fun attack(target: Combatant): Int
    abstract fun takeDamage(damage: Int)
    abstract fun useSpecialMove()
    abstract fun printStatus()
//    abstract fun useItem(item: Item)
    abstract fun move(direction: String)
}
