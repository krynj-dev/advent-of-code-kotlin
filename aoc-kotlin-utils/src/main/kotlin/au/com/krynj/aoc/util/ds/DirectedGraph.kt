package au.com.krynj.aoc.util.ds

import java.util.PriorityQueue

class DirectedGraph<T: Comparable<T>> {
    val nodes: MutableMap<T, GraphNode<T>> = mutableMapOf()
    val edges: MutableList<DirectedGraphEdge<T>> = mutableListOf()


    fun addNode(nodeValue: T): GraphNode<T> {
        val createdNode = GraphNode(nodeValue)
        if (nodes[nodeValue] != null) throw Exception("CLASH!")
        nodes.putIfAbsent(nodeValue, createdNode)
        return createdNode
    }

    fun addEdge(from: GraphNode<T>, to: GraphNode<T>): DirectedGraphEdge<T> {
        val createdEdge = DirectedGraphEdge(from, to)
        edges.add(createdEdge)
        return createdEdge
    }

    fun addEdge(from: GraphNode<T>, to: GraphNode<T>, weight: Int): DirectedGraphEdge<T> {
        val createdEdge = DirectedGraphEdge(from, to, weight)
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

    class DirectedGraphEdge<T: Comparable<T>>(val from: GraphNode<T>, val to: GraphNode<T>, val weight: Int = 1) {
        override fun toString(): String {
            return "($from, $to): $weight"
        }
    }

    fun hasCycle(): Boolean {
        val finished: MutableMap<GraphNode<T>, Boolean> = mutableMapOf()
        val visited: MutableMap<GraphNode<T>, Boolean> = mutableMapOf()
        for (node in nodes.values) {
            finished[node] = false
            visited[node] = false
        }
        for (node in nodes.values) {
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
        val edgesCopy = mutableListOf<DirectedGraphEdge<T>>()
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

    fun parentNodes(): List<GraphNode<T>> {
        val parents: MutableList<GraphNode<T>> = ArrayList(nodes.values)

        nodes.forEach {
            nodeChildren(it.value).forEach { child ->
                parents.remove(child)
            }
        }

        return parents
    }

    fun nodeChildren(from: GraphNode<T>): List<GraphNode<T>> {
        return edges.filter { it.from == from }.map { it.to }
    }

    fun nodeChildren(from: GraphNode<T>, edgeSubset: List<DirectedGraphEdge<T>>): List<GraphNode<T>> {
        return edgeSubset.filter { it.from == from }.map { it.to }
    }

    fun nodeParents(to: GraphNode<T>): List<GraphNode<T>> {
        return edges.filter { it.to == to }.map { it.from }
    }

    fun nodeParents(to: GraphNode<T>, edgeSubset: Set<DirectedGraphEdge<T>>): List<GraphNode<T>> {
        return edgeSubset.filter { it.to == to }.map { it.from }
    }

    fun getNodeByValue(value: T): GraphNode<T>? {
        return nodes[value]
    }

    fun childrenAtDistance(node: GraphNode<T>, depth: Int, distance: Int): List<GraphNode<T>> {
        val children = nodeChildren(node)
        if (depth == distance) {
            return children.toList()
        }
        return nodeChildren(node).flatMap {
            childrenAtDistance(it, depth + 1, distance)
        }
    }
}