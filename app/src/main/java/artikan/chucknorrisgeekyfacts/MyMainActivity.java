package artikan.chucknorrisgeekyfacts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class MyMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_main);

        // show welcome toast
        Toast t = Toast.makeText(getApplicationContext(), "Chuck Norris approves this app!", Toast.LENGTH_LONG);
        t.setGravity(Gravity.TOP, 0, 300);
        t.show();

        // change facts
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextFact();
            }
        });

        nextFact();
    }

    private void nextFact() {
        TextView txt = (TextView) findViewById(R.id.textView);

        try
        {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray facts = obj.getJSONArray("facts");
            Random rnd = new Random();
            int i = rnd.nextInt(facts.length());
            JSONObject riddle = facts.getJSONObject(i);

            txt.setText(riddle.getString("fact"));
        }
        catch (JSONException ex)
        {
            ex.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

}
