package fo.utilities

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor
import kotlin.random.Random

class Tools {
    companion object
    {

        fun hasDecimal(number: Float): Boolean {
            val roundedNumber = floor(number)
            return number - roundedNumber > 0
        }

        fun dateFormat(timestamp: Long): String
        {
            val dateFormat = SimpleDateFormat("d/M/yyyy")
            return dateFormat.format(Date(timestamp * 1000) )

        }

        fun currentDate(): Long
        {
            return Date().time / 1000
        }

        fun randomDate(): Long
        {
            val random = Random(System.currentTimeMillis()) // Create a new Random object
            val sixtyDaysAgoMs = 60 * 24 * 60 * 60 * 1000L // Calculate 60 days ago in milliseconds

            val randomMs = random.nextLong(sixtyDaysAgoMs) // Generate a random number of milliseconds within the time range
            return (System.currentTimeMillis() - sixtyDaysAgoMs + randomMs) / 1000


        }

        fun uuid(): String
        {
            return UUID.randomUUID().toString()
        }

    }


}