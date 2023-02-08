import java.util.concurrent.TimeUnit.*

class ProgramUsage {

    fun start() {
        startTime = System.currentTimeMillis()
    }

    fun stop() {
        endTime = System.currentTimeMillis()
    }

    fun getElapsedTime(): String {
        val elapsedTime = endTime - startTime
        val hours = MILLISECONDS.toHours(elapsedTime)
        val minutes = MILLISECONDS.toMinutes(elapsedTime) % 60
        val seconds = MILLISECONDS.toSeconds(elapsedTime) % 60

        val result = if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else if (minutes > 0) {
            String.format("%02d:%02d", minutes, seconds)
        } else {
            String.format("%02d", seconds)
        }

        return result
    }
}