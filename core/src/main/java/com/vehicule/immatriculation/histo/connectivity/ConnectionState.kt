
package com.vehicule.immatriculation.histo.connectivity

sealed class ConnectionState {

    object Unset : ConnectionState()

    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}
