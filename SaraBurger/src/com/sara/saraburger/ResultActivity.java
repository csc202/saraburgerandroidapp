package com.sara.saraburger;

//import org.apache.poi.poifs.property.Parent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.view.Menu;
//import android.view.MenuItem;

public class ResultActivity extends Activity {

	private Button returnButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		String testList = getIntent().getStringExtra("com.sara.saraburger.saraList");

		TextView etFoodName = (TextView) findViewById(R.id.resultText);
		
		etFoodName.setText(testList);

		returnButton = (Button) findViewById(R.id.backButton);
		returnButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// finish();
				ResultActivity.this.finish();
			}
		});

		//
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
}
