package combatant

import utils.Input
import inventory.Item

abstract class Hero(name: String, hp: Int) : Combatant(name, hp) {
    open val hasMana: Boolean = false

    fun chooseAction(): Boolean {
        println("""
            Wähle eine Aktion:
            (1) -> inventory.Item nutzen
            (2) -> Attackieren
        """.trimIndent())
        when (Input().checkInput()) {
            1 -> {
                return true
            }
            2 -> {
                return false
            }
            else -> {
                println("Eingabe nicht möglich.\n")
                return chooseAction()
            }
        }
    }

    abstract fun useItem(item: Item): Boolean

    override fun toString(): String {
        Thread.sleep(100)
        val info: String = (
                "\nName:      ${this.name}\n" +
                "HP:        ${this.hp}\n" +
                "Atk 1:     ${this.attacks.entries.elementAt(0).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(0).value}\n" +
                "Atk 2:     ${this.attacks.entries.elementAt(1).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(1).value}\n" +
                "Atk 3:     ${this.attacks.entries.elementAt(2).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(2).value}\n" +
                "Atk 4:     ${this.attacks.entries.elementAt(3).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(3).value}")
        return info
    }

    abstract fun checkStats(): Boolean

    open fun resetStats() {}
}