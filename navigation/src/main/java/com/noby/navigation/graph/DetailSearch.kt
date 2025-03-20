package com.noby.navigation.graph

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import com.noby.navigation.utils.ArgsScreen
import com.noby.navigation.utils.DestinationRoute

object DetailSearch :ArgsScreen<Unit>{
    override fun destination(arg: Unit): DestinationRoute= route

    override val route: String = "detail/search"
    override val arguments: List<NamedNavArgument> = emptyList()

    override fun objectParser(entry: NavBackStackEntry) {}
}