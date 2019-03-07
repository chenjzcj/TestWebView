package cn.cbg.testwebview;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private FrameLayout fullVideo;
    private View customView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        fullVideo = findViewById(R.id.full_video);

        setWebView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
        }
    }

    private void setWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.loadUrl("http://play.ykreadvip.com//player_m3u8.php?url=https://cdn-4.haku99.com/hls/2019/03/01/IE3eyJLE/playlist.m3u8");
    }

    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onHideCustomView() {
            if (customView == null) {
                return;
            }
            fullVideo.removeView(customView);
            fullVideo.setVisibility(View.GONE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            //清除全屏
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            customView = view;
            fullVideo.setVisibility(View.VISIBLE);
            fullVideo.addView(customView);
            fullVideo.bringToFront();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            //设置全屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

    }
}
