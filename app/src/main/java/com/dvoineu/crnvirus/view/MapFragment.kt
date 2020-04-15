package com.dvoineu.crnvirus.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.dvoineu.crnvirus.R
import kotlinx.android.synthetic.main.fragment_map.*


/**
 * A simple [Fragment] subclass.
 */
class MapFragment : Fragment() {

    private val URL: String = "https://google.org/crisisresponse/covid19-map"
    private val test = "hello"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView: WebView = webview
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.setSupportZoom(true)
        webSettings.allowContentAccess = true
        webSettings.useWideViewPort = true
        webSettings.setAppCacheEnabled(true)
        webSettings.domStorageEnabled = true

        webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }

        webView.loadUrl(URL)
    }


}
