package au.com.krynj.aoc.framework

interface AoCObserver<T: AoCObserverContext> {
    fun notify(context: T)
}