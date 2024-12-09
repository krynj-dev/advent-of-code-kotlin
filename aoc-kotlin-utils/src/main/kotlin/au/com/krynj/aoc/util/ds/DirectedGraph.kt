package au.com.krynj.aoc.util.ds

import java.util.PriorityQueue

class DirectedGraph<T: Comparable<T>> {
    val nodes: MutableSet<GraphNode<T>> = mutableSetOf()
    val edges: MutableSet<DirectedGraphEdge<T>> = mutableSetOf()


    fun addNode(nodeValue: T): GraphNode<T> {
        val createdNode = GraphNode(nodeValue)
        nodes.add(createdNode)
        return createdNode
    }

    fun addEdge(from: GraphNode<T>, to: GraphNode<T>): DirectedGraphEdge<T> {
        val createdEdge = DirectedGraphEdge(from, to)
        edges.add(createdEdge)
        return createdEdge
    }
    class GraphNode<T: Comparable<T>>(val value: T): Comparable<T> {
        override fun compareTo(other: T): Int {
            return other.compareTo(value)
        }

        override fun toString(): String {
            return value.toString()
        }
    }

    class DirectedGraphEdge<T: Comparable<T>>(val from: GraphNode<T>, val to: GraphNode<T>) {
        override fun toString(): String {
            return "($from, $to)"
        }
    }

    fun hasCycle(): Boolean {
        val finished: MutableMap<GraphNode<T>, Boolean> = mutableMapOf()
        val visited: MutableMap<GraphNode<T>, Boolean> = mutableMapOf()
        for (node in nodes) {
            finished[node] = false
            visited[node] = false
        }
        for (node in nodes) {
            if (dfs((finished), visited, node)) return true
        }
        return false
    }

    private fun dfs(finished: MutableMap<GraphNode<T>, Boolean>, visited: MutableMap<GraphNode<T>, Boolean>, v: GraphNode<T>): Boolean {
        if (finished[v]!!) {
            return false
        }
        if (visited[v]!!) return true
        visited[v] = true
        val found = nodeChildren(v).map { dfs(finished, visited, it) }.any { it }
        finished[v] = true
        return found
    }

    fun topologicalSort(): List<GraphNode<T>>? {
        val sorted: MutableList<GraphNode<T>> = ArrayList()
        val parents = PriorityQueue(parentNodes())
        val edgesCopy = mutableSetOf<DirectedGraphEdge<T>>()
        edgesCopy.addAll(edges)

        while (parents.isNotEmpty()) {
            val node = parents.poll()
            sorted.add(node)
            edgesCopy.filter { it.from == node }.forEach {
                edgesCopy.remove(it)
                if (edgesCopy.none { e -> e.to == it.to }) {
                    parents.add(it.to)
                }
            }
        }

        if (edgesCopy.isNotEmpty()) {
            return null
        }
        return sorted
    }

    fun parentNodes(): Set<GraphNode<T>> {
        val parents: MutableSet<GraphNode<T>> = HashSet(nodes)

        nodes.forEach {
            nodeChildren(it).forEach { child ->
                parents.remove(child)
            }
        }

        return parents
    }

    fun nodeChildren(from: GraphNode<T>): Set<GraphNode<T>> {
        return edges.filter { it.from == from }.map { it.to }.toSet()
    }

    fun nodeChildren(from: GraphNode<T>, edgeSubset: Set<DirectedGraphEdge<T>>): Set<GraphNode<T>> {
        return edgeSubset.filter { it.from == from }.map { it.to }.toSet()
    }

    fun nodeParents(to: GraphNode<T>): Set<GraphNode<T>> {
        return edges.filter { it.to == to }.map { it.from }.toSet()
    }

    fun nodeParents(to: GraphNode<T>, edgeSubset: Set<DirectedGraphEdge<T>>): Set<GraphNode<T>> {
        return edgeSubset.filter { it.to == to }.map { it.from }.toSet()
    }

}