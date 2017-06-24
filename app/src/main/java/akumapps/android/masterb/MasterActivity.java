package akumapps.android.masterb;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class MasterActivity extends AppCompatActivity {

    private String fileName = "montantCourant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        try
        {
            TextView depense = (TextView) findViewById(R.id.montantTotal);
            FileInputStream fis = openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;

            line=br.readLine();
            if(line==null)
            {
                line="0";

            }
            depense.setText(line);
            br.close();
        }
        catch(java.io.IOException e)
        {
           e.getMessage();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "kikouuuuuuuuuuuuuuuuuuuuuuuuuuu", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Button buttonAdd= (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             EditText montant = (EditText) findViewById(R.id.montant);
                                             TextView montantTotal = (TextView) findViewById(R.id.montantTotal);

                                             String montantIString=montant.getText().toString();

                                             if(montantIString.isEmpty())
                                             {
                                                 montantIString="0.0";
                                             }


                                             Float montantI= Float.parseFloat(montantIString);
                                             if( montantI > 9999999 | montantI< 0)
                                             {
                                                montantI = 0.0f;
                                                 Toast.makeText(this,'Pas possible',Toast.LENGTH_LONG);
                                             }

                                             String montantTotalString=montantTotal.getText().toString();
                                             if(montantTotalString.isEmpty())
                                             {
                                                 montantTotalString="0.0";

                                             }

                                             Float montantTotalI= Float.parseFloat(montantTotal.getText().toString());

                                             montantTotalI+=montantI;

                                             setText(montantTotal,montantTotalI.toString(), MODE_PRIVATE);

                                             montant.setText(null);
                                         }
                                     }
        );

        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    try{
                        FileOutputStream fos = openFileOutput(fileName,MODE_PRIVATE);
                        PrintWriter pw = new PrintWriter( new OutputStreamWriter(fos));
                        pw.close();
                        TextView montantTotal= (TextView) findViewById(R.id.montantTotal);
                        montantTotal.setText("0");
                    }
                    catch(java.io.IOException e){}
                }

        }

        );
    }

    private void setText(TextView montantTotal, String montantTotalI, int mode)  {

        montantTotal.setText(montantTotalI);


        try
        {
            FileOutputStream fos = openFileOutput(fileName,mode);
            PrintWriter pw = new PrintWriter( new OutputStreamWriter(fos));

            pw.print(montantTotalI);
            pw.close();


        }catch(java.io.IOException  e)
        {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_master, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
