class Invokable {
    var numberOfInvocations: Int = 0
        private set

    operator fun invoke(): Invokable {
        this.numberOfInvocations++
        return this
    }
}

fun invokeTwice(invokable: Invokable) = invokable()()
