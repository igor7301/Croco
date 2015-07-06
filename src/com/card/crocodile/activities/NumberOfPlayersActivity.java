package com.card.crocodile.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.card.crocodile.R;
import com.card.crocodile.Utils;
import com.startapp.android.publish.StartAppAd;

public class NumberOfPlayersActivity extends Activity implements View.OnClickListener {
    private final static  String MY_LOG = "MY_LOG";
    private EditText numberOfPlayers;
    private ImageButton btnContinue;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        numberOfPlayers = (EditText) findViewById(R.id.eT_numberOfPlayers);
        btnContinue = (ImageButton) findViewById(R.id.bntContinue);

        btnContinue.setOnClickListener(this);


//



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bntContinue:
                if(!numberOfPlayers.getText().toString().isEmpty()){
                    Intent intent = new Intent(this, NameOfPlayersActivity.class);
                    intent.putExtra(Utils.NUMBER_OF_PLAYERS, numberOfPlayers.getText().toString());
                    startActivity(intent);

                }
                else {
                    Toast.makeText(this, getString(R.string.numberOfPlayers), Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
