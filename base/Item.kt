open class Item(var name: String, var description: String, var quantity: Int) {

    fun decreaseQuantity() {
        this.quantity -= 1
        if (this.name == "Manatrank") {
            usedManaPotions++
        } else {
            usedHealPotions++
        }
    }
    open fun use(hero: Hero, item: Item): Boolean {
        return false
    }
}