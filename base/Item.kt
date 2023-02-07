open class Item(var name: String, var description: String, var quantity: Int) {
    fun increaseQuantity(amount: Int) {
        this.quantity += amount
    }
    fun decreaseQuantity(amount: Int) {
        this.quantity -= amount
    }
}