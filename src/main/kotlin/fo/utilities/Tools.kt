package fo.utilities

import kotlin.math.floor

class Tools {
    companion object
    {

        fun hasDecimal(number: Float): Boolean {
            val roundedNumber = floor(number)
            return number - roundedNumber > 0
        }
    }
}