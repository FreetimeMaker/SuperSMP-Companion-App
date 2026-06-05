package com.freetime.ssmpc

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient

class PrivacyWebView(context: Context, attrs: AttributeSet? = null) :
    WebView(context, attrs) {

    init {
        setupPrivacySettings()
        clearAllData()
        webViewClient = PrivacyClient()
    }

    private fun setupPrivacySettings() {
        settings.apply {
            javaScriptEnabled = false
            domStorageEnabled = false
            allowFileAccess = false
            allowContentAccess = false
            userAgentString = "Mozilla/5.0"
        }

        CookieManager.getInstance().apply {
            setAcceptCookie(false)
            setAcceptThirdPartyCookies(this@PrivacyWebView, false)
        }
    }

    private fun clearAllData() {
        clearCache(true)
        clearHistory()

        CookieManager.getInstance().apply {
            removeAllCookies(null)
            flush()
        }
    }

    private class PrivacyClient : WebViewClient() {

        private val blockedHosts = listOf(
            "google-analytics.com",
            "doubleclick.net",
            "facebook.net",
            "googletagmanager.com",
            "adservice.google.com",
            "ads.yahoo.com",
            "bing.com/track"
        )

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url = request?.url ?: return false
            val scheme = url.scheme?.lowercase()

            // Keep regular web pages inside the in-app view.
            if (scheme == "http" || scheme == "https") {
                return false
            }

            // Other schemes (mailto, tel, geo, ...) cannot be shown in the WebView,
            // so hand them to the system instead of failing on a blank page.
            val context = view?.context ?: return false
            return try {
                context.startActivity(Intent(Intent.ACTION_VIEW, url))
                true
            } catch (e: ActivityNotFoundException) {
                false
            }
        }

        override fun shouldInterceptRequest(
            view: WebView?,
            request: WebResourceRequest?
        ): WebResourceResponse? {

            val url = request?.url ?: return super.shouldInterceptRequest(view, request)
            val host = url.host ?: return super.shouldInterceptRequest(view, request)

            // 1. Tracker blockieren
            if (blockedHosts.any { host.contains(it) }) {
                return WebResourceResponse("text/plain", "utf-8", null)
            }

            // 2. Set-Cookie Header entfernen
            val response = super.shouldInterceptRequest(view, request)
            response?.responseHeaders?.remove("Set-Cookie")

            return response
        }
    }
}
