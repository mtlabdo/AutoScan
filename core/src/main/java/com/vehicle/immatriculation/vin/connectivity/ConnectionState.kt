
package com.vehicle.immatriculation.vin.connectivity

sealed class ConnectionState {

    object Unset : ConnectionState()

    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}
