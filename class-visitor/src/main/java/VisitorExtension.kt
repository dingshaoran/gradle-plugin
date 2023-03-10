import java.util.*

open class VisitorExtension {
    var visit: Map<String, Map<String, Map<String, Map<String, List<String>?>?>?>?> = Collections.emptyMap()

    fun visit(param: Map<String, Map<String, Map<String, Map<String, List<String>?>?>?>?>?) {
        visit = param ?: Collections.emptyMap()
    }
}