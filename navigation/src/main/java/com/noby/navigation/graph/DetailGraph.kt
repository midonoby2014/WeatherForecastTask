package com.noby.navigation.graph

import com.noby.navigation.graph.DetailMain
import com.noby.navigation.graph.DetailSearch
import com.noby.navigation.utils.NavigationGraph

object DetailGraph : NavigationGraph {
    override val route: String
        get() = "detailgraph"
    override val startDestination: String
        get() = detailMain.destination(Unit)

    val detailMain = DetailMain
    val detailSearch = DetailSearch
}