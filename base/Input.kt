class Input {
    fun checkInput(): Int{
        print("\n\nDeine Entscheidung: ")
        val input = readln()
        while (true) {
            try {
                if (input.isNotEmpty()) {
                    when (input.toInt()) {
                        1 -> {
                            return 1
                        }
                        2 -> {
                            return 2
                        }
                        3 -> {
                            return 3
                        }
                        4 -> {
                            return 4
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