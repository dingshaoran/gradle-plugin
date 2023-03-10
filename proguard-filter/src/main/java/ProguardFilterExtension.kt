import java.util.*

open class ProguardFilterExtension() {

    var config: Map<String, String?> = Collections.emptyMap()


    fun config(value: Map<String, String?>) {
        config = value
    }
}