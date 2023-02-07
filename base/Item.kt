open class Item(var name: String, var description: String, var quantity: Int) {
    fun increaseQuantity(amount: Int) {
        this.quantity += amount
    }
    fun decreaseQuantity() {
        this.quantity -= 1
    }
    open fun use(hero: Hero, item: Item): Boolean {
        return false
    }
}