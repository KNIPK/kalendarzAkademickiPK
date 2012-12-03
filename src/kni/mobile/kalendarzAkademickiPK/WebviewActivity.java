package kni.mobile.kalendarzAkademickiPK;

import kni.mobile.kalendarzAkademickiPK.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebviewActivity extends Activity{
	WebView webview;
	ProgressDialog pd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum);
		
		webview = (WebView)findViewById(R.id.wv1);
		
		webview.setWebViewClient(new WebViewClient() {
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Toast.makeText(getApplicationContext() , "Błąd podczas wczytywania strony",
						Toast.LENGTH_LONG).show();
			}
		});
		
		pd = ProgressDialog.show(this, "Proszę czekać", "wczytuję stronę początkową");
		
		webview.loadUrl("http://www.ambitni.eu/");
		
		WebViewClient webViewClient = new WebViewClient(){
			public void onPageFinished(WebView v, String url){
				super.onPageFinished(v, url);
				pd.dismiss();
			}
		};
		
		webview.setWebViewClient(webViewClient);
	}
}
