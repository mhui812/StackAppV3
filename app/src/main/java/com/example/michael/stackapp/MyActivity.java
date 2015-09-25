package com.example.michael.stackapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Stack;



public class MyActivity extends AppCompatActivity {

    Stack st = new Stack();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        Button push_button = (Button)findViewById(R.id.push_button);
        push_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                EditText myEdit = (EditText) findViewById(R.id.entry);
                String myEditValue = "";
                int myEditNum = 0;

                try {
                    myEditValue = myEdit.getText().toString();
                    myEditNum = Integer.parseInt(myEditValue);
                }catch(NumberFormatException e){
                    //Toast.makeText(getApplicationContext(), "Your input is empty!", Toast.LENGTH_LONG).show();
                    TextView t = (TextView) findViewById(R.id.result);
                    t.setText("Your input is empty! Current Stack is " + st);
                    return;
                }



                if(st.size() < 3 ) {
                    if (myEditNum > 0 && myEditNum < 10) {
                        st.push(myEditNum);

                        //Toast.makeText(getApplicationContext(), "Pushing " + myEditNum, Toast.LENGTH_LONG).show();
                        TextView t = (TextView) findViewById(R.id.result);
                        t.setText("Stack is " + st);

                    } else {
                        //Toast.makeText(getApplicationContext(), "Number is invalid!", Toast.LENGTH_LONG).show();
                        TextView t = (TextView) findViewById(R.id.result);
                        t.setText("Your input is invalid! The number needs to be between 1 to 9. Current Stack is " + st);
                    }
                } else {
                    //Toast.makeText(getApplicationContext(), "Stack is full!", Toast.LENGTH_LONG).show();
                    TextView t = (TextView) findViewById(R.id.result);
                    t.setText("Stack is full! " + st);
                }
                ((EditText) findViewById(R.id.entry)).getText().clear();

            }
        });

        Button pop_button = (Button)findViewById(R.id.pop_button);
        pop_button.setOnClickListener(pop_buttonOnClickListener);

        Button exit_button = (Button)findViewById(R.id.exit_button);
        exit_button.setOnClickListener(exit_buttonOnClickListener);

    }

        OnClickListener pop_buttonOnClickListener = new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (!st.isEmpty()) {
                    //Toast.makeText(getApplicationContext(), "Popping " + st.peek(), Toast.LENGTH_LONG).show();
                    TextView t = (TextView) findViewById(R.id.result);
                    Object peek = st.peek();
                    st.pop();
                    t.setText(peek + " is popped, Stack is " + st);

                } else {
                    //Toast.makeText(getApplicationContext(), "Stack is empty!", Toast.LENGTH_LONG).show();
                    TextView t = (TextView) findViewById(R.id.result);
                    t.setText("Stack is empty!");
                }
            }
        };

        OnClickListener exit_buttonOnClickListener = new OnClickListener() {
            public void onClick(View v) {
                exit();
            }
        };


    public void exit() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MyActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            Toast.makeText(getApplicationContext(),
                    "This is a demo of StackApp project on CPSC544.",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.exit) {
           exit();
        }

        return super.onOptionsItemSelected(item);
    }
}
