package oss.anitha.digiassist;

import java.util.StringTokenizer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class ListMedicationActivity extends Activity {

	String strTratamiento;

	public void onCreate(Bundle icicle) 
	{
		super.onCreate(icicle);
        setContentView(R.layout.activity_list_medication);
	}

    public void goToDa (View view) {
        goToUrl ( "http://www.drugs.com/mtm/dalteparin.html");
    }

    public void goToEn (View view) {
        goToUrl ( "http://www.drugs.com/cons/enoxaparin-subcutaneous-injection.html");
    }
    
    public void goToHe (View view) {
        goToUrl ( "http://www.drugs.com/cons/heparin-intravenous-subcutaneous.html");
    }
    
    public void goToTi (View view) {
        goToUrl ( "http://www.drugs.com/mtm/tinzaparin.html");
    }
    
    public void goToWa (View view) {
        goToUrl ( "http://www.drugs.com/cdi/warfarin.html");
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
	
	
	
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_lsit_medication, menu);
        return true;
    }
    
    
    
    
    

    
}
