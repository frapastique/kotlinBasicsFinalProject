abstract class Combatant(var name: String, var hp: Int) {
    open fun attack(): Int {
        return 0
    }
    fun checkDefeat(): Boolean {
        return this.hp <= 0
    }
}