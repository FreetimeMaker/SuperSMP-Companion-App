package com.freetime.ssmpc

import android.app.Application
import com.freetime.sdk.DeveloperConfig
import com.freetime.sdk.FreetimePay

class SuperSMPApplication : Application() {

    companion object {
        lateinit var freetimePay: FreetimePay
            private set
    }

    override fun onCreate() {
        super.onCreate()

        // Initialize FreetimeSDK
        val config = DeveloperConfig(
            developerId = "FreetimeMaker"
        )
        
        freetimePay = FreetimePay(config)

        // Register default crypto providers with your addresses
        val addresses = mapOf(
            "BTC" to "1DsCAVrzvGokrzXpe6YR33QuTo5EppiKRE",
            "ETH" to "0x3d3eee5b542975839d2dccbf2f97139debc711bc",
            "LTC" to "LU2ERRXKTeKnzpuieQcpsBteViEY7ff5Wg",
            "BCH" to "qz5klapp9c4kq97psu5rg7sq9quu3vcv7qan8dn6ts",
            "DOGE" to "DFZtQ1SedQFGijrR7LJ55RFBNFVQpbGULn",
            "SOL" to "6K6gpBF9nyrSL2vzSaFDZgAJQurkoEzPGtK67WAg6FjX",
            "TRX" to "TKUNwoQMyLuJzUzWPKwA7yw4qujz2Pz6gS",
            "MATIC" to "0x3d3eee5b542975839d2dccbf2f97139debc711bc",
            "ARB" to "0x3d3eee5b542975839d2dccbf2f97139debc711bc",
            "OP" to "0x3d3eee5b542975839d2dccbf2f97139debc711bc",
            "BASE" to "0xba8bBaE3168062699E668Be7d99AB10B790aB467",
            "TON" to "UQANB5nn0Oinom7IFkbClwRWRpK2zfal6sO11988Y85AamDS",
            "SUI" to "0x366f1e1d6d404351cbf9836494206aab43264fd60228b15c06e275bd7b161b78",
            "XMR" to "49szz88CqMWGgyDxp7VqvBS62pGLQcV4YPSBHcLwtxAXLz1Wngf8vW6is4w13Au7C2RovrTiJQaGDV5VBhFnyMBsM44Pn2P",
            "DASH" to "Xhr4Nirm7AZVtSF8ovsy5nEeXhS8Tv24pV",
            "ZEC" to "u14l4cu9m4z8r92ut4j6fqz99wuttrq2u7gtlvgm84j3g7p32a74257c5882nd6emzdwkx97had5tfhaz0k7mr9urpp4nf9fq7wcj2txggl5ttxu8xnz8khxpnhuj24r29av00egp59jzxsule409apmul3uskny566hfkhz3lgfkxwavpjf37sf64jpdnht6sf759e09043je7z7kdje",
            "XRP" to "rwPRMisBbDWd8841TNk1JjrWLmHL7ffjuV",
            "ADA" to "0xC112f59eeC15de98906a8BAaC3a08a41D80cf946"
        )

        freetimePay.registerDefaultCryptoProviders(addresses)
    }
}
