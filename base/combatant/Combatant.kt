package combatant

abstract class Combatant(var name: String, var hp: Int) {
    abstract val attacks: Map<String, Int>

    abstract fun attack(target: Combatant, factor: Double): Int
    abstract fun takeDamage(damage: Int, factor: Double)
    abstract fun printStatus(): Boolean

    abstract fun showStatsSmall(): List<Any>

    fun checkDefeat(hp: Any): Boolean {
        return hp.toString() == "0"
    }
}
