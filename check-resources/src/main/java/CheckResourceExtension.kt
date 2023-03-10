open class CheckResourceExtension() {


    var app = false

    var module = true

    var maven = false

    constructor(app: Boolean, module: Boolean, maven: Boolean) : this() {
        this.app = app
        this.module = module
        this.maven = maven
    }


    fun app(value: Boolean) {
        app = value
    }

    fun module(value: Boolean) {
        module = value
    }

    fun maven(value: Boolean) {
        maven = value
    }


}