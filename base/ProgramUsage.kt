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

        playTime += elapsedTime

        val hours = MILLISECONDS.toHours(elapsedTime)
        val minutes = MILLISECONDS.toMinutes(elapsedTime) % 60
        val seconds = MILLISECONDS.toSeconds(elapsedTime) % 60

        val result = if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds) + " Stunden"
        } else if (minutes > 0) {
            String.format("%02d:%02d", minutes, seconds) + " Minuten"
        } else {
            String.format("%02d", seconds) + " Sekunden"
        }

        return result
    }

    fun calculatePlayTime(playTime: Long): String {
        val hours = MILLISECONDS.toHours(playTime)
        val minutes = MILLISECONDS.toMinutes(playTime) % 60
        val seconds = MILLISECONDS.toSeconds(playTime) % 60

        val result = if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds) + " Stunden"
        } else if (minutes > 0) {
            String.format("%02d:%02d", minutes, seconds) + " Minuten"
        } else {
            String.format("%02d", seconds) + " Sekunden"
        }

        return result
    }
}