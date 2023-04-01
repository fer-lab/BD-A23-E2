package movieRiew.utilities

import kotlinx.coroutines.runBlocking

class GoToAction : GoToEntity {
    constructor(action: () -> Unit)
    {
        this._gotoAction = action
    }

    companion object {
        fun action(action: () -> Unit, delay: Int = 0, delayMessage: String = "") {

            runBlocking {

                if (delay > 0) {
                    print(delayMessage.ifEmpty { "Porfavor espera" })

                    repeat(delay) {
                        kotlinx.coroutines.delay(1000)
                        print(".")
                    }
                }

                Tools.newPage()
                action()

            }




        }

        fun setDelay(delay: Int, delayMessage: String = "")
        {
            runBlocking {
                print(delayMessage.ifEmpty { "Porfavor espera" })

                repeat(delay) {
                    kotlinx.coroutines.delay(1000)
                    print(".")
                }
            }
        }
    }

}