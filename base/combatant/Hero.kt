package combatant

import inventory.Inventory
import utils.Input
import inventory.Item

abstract class Hero(name: String, hp: Int) : Combatant(name, hp) {
    open val hasMana: Boolean = false

    fun chooseAction(): Boolean {
        println("""
            
            Wähle eine Aktion:
            (1) -> Inventar öffnen
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

    fun chooseItem(inventory: Inventory, hero: Hero): Boolean {
        println("\nWähle ein Trank:")
        var k: Int = 1
        for (element in inventory.items) {
            println("""
                ($k) -> ${element.name}
                       ${element.quantity} im Inventar
                       ${element.description}
                        """.trimIndent())
            k++
        }
        println("(3) -> Inventar schließen und attackieren")
        when (Input().checkInput()) {
            1 -> {
                if (inventory.useItem(inventory.items[0], hero)) {
                    return true
                } else {
                    return chooseItem(inventory, hero)
                }
            }
            2 -> {
                if (inventory.useItem(inventory.items[1], hero)) {
                    return true
                } else {
                    return chooseItem(inventory, hero)
                }
            }
            3 -> return false
            else -> {
                println("Eingabe nicht möglich.")
                return chooseItem(inventory, hero)
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