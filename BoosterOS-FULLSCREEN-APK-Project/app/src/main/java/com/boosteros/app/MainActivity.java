package com.boosteros.app;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.webkit.WebViewAssetLoader;
import androidx.webkit.WebViewClient;

public class MainActivity extends Activity {
 private WebView webView;
 private void immersive() {
  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
  getWindow().getDecorView().setSystemUiVisibility(
    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|View.SYSTEM_UI_FLAG_FULLSCREEN|
    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
 }
 @Override public void onCreate(Bundle b){
  super.onCreate(b); immersive();
  webView=new WebView(this);
  WebView.setWebContentsDebuggingEnabled(false);
  WebSettings s=webView.getSettings();
  s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setDatabaseEnabled(true);
  s.setMediaPlaybackRequiresUserGesture(false); s.setAllowFileAccess(true); s.setAllowContentAccess(true);
  s.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
  WebViewAssetLoader loader=new WebViewAssetLoader.Builder().addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(this)).build();
  webView.setWebViewClient(new WebViewClient(){ @Override public android.webkit.WebResourceResponse shouldInterceptRequest(WebView v,String u){ return loader.shouldInterceptRequest(android.net.Uri.parse(u)); }});
  setContentView(webView);
  webView.loadUrl("https://appassets.androidplatform.net/assets/index.html");
 }
 @Override public void onWindowFocusChanged(boolean h){super.onWindowFocusChanged(h);if(h)immersive();}
 @Override public void onBackPressed(){if(webView!=null && webView.canGoBack()) webView.goBack(); else super.onBackPressed();}
}
