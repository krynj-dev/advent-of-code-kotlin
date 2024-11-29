package au.com.krynj.aoc.framework

import org.reflections.Reflections
import java.lang.reflect.Modifier

class AoCApplication(private var classPath: String) {

    fun start() {
        val annotatedClasses = getImplementationsOfInterface<AoCDay>(classPath)
        val filteredClasses = annotatedClasses.filter { !it.isInterface && !Modifier.isAbstract(it.modifiers) }.toSet()

        var instances = filteredClasses.map { it.getDeclaredConstructor().newInstance() }
        instances = instances.sortedBy { it.getDay() }

        for (day in instances) {
            day.run()
        }
    }

    private inline fun <reified T> getImplementationsOfInterface(packageName: String): Set<Class<out T>> {
        val reflections = Reflections(packageName) // Specify the package to scan
        // Get all subtypes of T, which are classes that implement the interface or extend the class
        return reflections.getSubTypesOf(T::class.java).filter { T::class.java.isAssignableFrom(it) }.toSet()
    }
}