package au.com.krynj.aoc.framework

class PrintfAoCObserver(private val formatString: String): AoCObserver<SimpleObserverContext> {
    override fun notify(context: SimpleObserverContext) {
        println(String.format(formatString, context.getPartialResult()))
    }
}