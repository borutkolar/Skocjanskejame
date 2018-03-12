package android.parkskocjanskejame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Reward extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("file:///android_asset/reward.html");
    }
}
