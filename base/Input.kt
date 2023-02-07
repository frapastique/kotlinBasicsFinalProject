class Input {
    fun checkInput(): Int {
        print("Deine Entscheidung: ")
        val input = readln()
        while (true) {
            try {
                if (input.isNotEmpty()) {
                    when (input.toInt()) {
                        input.toInt() -> {
                            return input.toInt()
                        }
                        else -> {
                            return 0
                        }
                    }
                } else {
                    return 0
                }
            } catch (e: Exception) {
                return 0
            }
        }
    }
}