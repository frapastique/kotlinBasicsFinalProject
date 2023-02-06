class Input {
    fun checkInput(): Int {
        print("\nDeine Entscheidung: ")
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
                        5 -> {
                            return 5
                        }
                        6 -> {
                            return 6
                        }
                        7 -> {
                            return 7
                        }
                        8 -> {
                            return 8
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