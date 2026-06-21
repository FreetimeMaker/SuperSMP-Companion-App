package com.freetime.ssmpc.data.status

import kotlinx.serialization.Serializable

@Serializable
data class McSrvStatResponse(
    val online: Boolean,
    val players: PlayersInfo? = null,
    val version: String? = null,
    val motd: MotdInfo? = null
)

@Serializable
data class PlayersInfo(
    val online: Int,
    val max: Int
)

@Serializable
data class MotdInfo(
    val clean: List<String>? = null
)
