package au.com.krynj.aoc.framework

interface AoCObservable<T: AoCObserverContext> {
    fun addObserver(observer: AoCObserver<T>)

    fun broadcast(context: T)
}