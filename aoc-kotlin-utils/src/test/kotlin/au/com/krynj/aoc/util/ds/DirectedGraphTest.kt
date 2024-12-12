package au.com.krynj.aoc.util.ds

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DirectedGraphTest {

    @Test
    fun hasCycleTrue() {
        val g: DirectedGraph<Int> = DirectedGraph()
        val n1: DirectedGraph.GraphNode<Int> = g.addNode(1)
        val n2: DirectedGraph.GraphNode<Int> = g.addNode(2)
        val n3: DirectedGraph.GraphNode<Int> = g.addNode(3)
        val n4: DirectedGraph.GraphNode<Int> = g.addNode(4)
        val n5: DirectedGraph.GraphNode<Int> = g.addNode(5)
        g.addEdge(n1, n2)
        g.addEdge(n1, n5)
        g.addEdge(n2, n3)
        g.addEdge(n3, n1)
        g.addEdge(n4, n5)
        assertTrue(g.hasCycle())
    }

    @Test
    fun hasCycleFalse() {
        val g: DirectedGraph<Int> = DirectedGraph()
        val n1: DirectedGraph.GraphNode<Int> = g.addNode(1)
        val n2: DirectedGraph.GraphNode<Int> = g.addNode(2)
        val n3: DirectedGraph.GraphNode<Int> = g.addNode(3)
        val n4: DirectedGraph.GraphNode<Int> = g.addNode(4)
        val n5: DirectedGraph.GraphNode<Int> = g.addNode(5)
        g.addEdge(n1, n2)
        g.addEdge(n1, n5)
        g.addEdge(n2, n3)
        g.addEdge(n3, n5)
        g.addEdge(n4, n5)
        assertFalse(g.hasCycle())
    }

    @Test
    fun hasTopoSortCycleFree() {
        val g: DirectedGraph<Int> = DirectedGraph()
        val n1: DirectedGraph.GraphNode<Int> = g.addNode(1)
        val n2: DirectedGraph.GraphNode<Int> = g.addNode(2)
        val n3: DirectedGraph.GraphNode<Int> = g.addNode(3)
        val n4: DirectedGraph.GraphNode<Int> = g.addNode(4)
        val n5: DirectedGraph.GraphNode<Int> = g.addNode(5)
        g.addEdge(n1, n2)
        g.addEdge(n1, n5)
        g.addEdge(n2, n3)
        g.addEdge(n3, n5)
        g.addEdge(n4, n5)
        val sorted = g.topologicalSort()
        assertNotNull(sorted)
    }

    @Test
    fun hasNullTopoSortWithCycle() {
        val g: DirectedGraph<Int> = DirectedGraph()
        val n1: DirectedGraph.GraphNode<Int> = g.addNode(1)
        val n2: DirectedGraph.GraphNode<Int> = g.addNode(2)
        val n3: DirectedGraph.GraphNode<Int> = g.addNode(3)
        val n4: DirectedGraph.GraphNode<Int> = g.addNode(4)
        val n5: DirectedGraph.GraphNode<Int> = g.addNode(5)
        g.addEdge(n1, n2)
        g.addEdge(n1, n5)
        g.addEdge(n2, n3)
        g.addEdge(n3, n1)
        g.addEdge(n4, n5)
        assertNull(g.topologicalSort())
    }

    @Test
    fun getNodeByValue() {
        val g: DirectedGraph<Int> = DirectedGraph()
        val n1: DirectedGraph.GraphNode<Int> = g.addNode(1)
        val n2: DirectedGraph.GraphNode<Int> = g.addNode(2)
        val n3: DirectedGraph.GraphNode<Int> = g.addNode(3)
        val n4: DirectedGraph.GraphNode<Int> = g.addNode(4)
        val n5: DirectedGraph.GraphNode<Int> = g.addNode(5)
        g.addEdge(n1, n2)
        g.addEdge(n1, n5)
        g.addEdge(n2, n3)
        g.addEdge(n3, n1)
        g.addEdge(n4, n5)
        assertNull(g.getNodeByValue(6))
        assertEquals(n2, g.getNodeByValue(2))
    }
}