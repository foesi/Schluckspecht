package de.wurstsushi.schluckspecht;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import de.wurstsushi.schluckspecht.dialogs.BeerBoughtDialog;

public class BeerList extends AppCompatActivity {

    private void addLatestBeerRow(TableLayout table, String name, String date) {

        TableRow row= new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView nameView = new TextView(this);
        nameView.setText(name);
        nameView.setTextSize(20);
        nameView.setPadding(5,5,5,5);

        TextView dateView = new TextView(this);
        dateView.setText(date);
        dateView.setTextSize(20);
        nameView.setPadding(5,5,5,5);
        dateView.setGravity(Gravity.END);

        row.addView(nameView);
        row.addView(dateView);

        table.addView(row);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                BeerBoughtDialog newFragment = new BeerBoughtDialog();

                newFragment.show(fragmentManager, "dialog");
            }
        });

        TableLayout latestBeerTable = (TableLayout) findViewById(R.id.latestBeerTable);

        addLatestBeerRow(latestBeerTable, "Peter Lustig", "05.06.2017");
        addLatestBeerRow(latestBeerTable, "Biene Maja", "10.06.2017");
        addLatestBeerRow(latestBeerTable, "Leia Skywalker", "24.06.2017");
        addLatestBeerRow(latestBeerTable, "James Bond", "01.07.2017");

        ImageButton button = (ImageButton) findViewById(R.id.buttonBeerLeer);
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar.make(view, "Es gib kein Bier mehr, es muss Bier her!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beer_list, menu);
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
