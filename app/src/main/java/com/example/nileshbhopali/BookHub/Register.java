package com.example.nileshbhopali.BookHub;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
public class Register extends AppCompatActivity {
    private static final String TAG = "event";
    EditText t1, t2,t3;
    Button b1;
    String name, pass,email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        t1 = (EditText) findViewById(R.id.username);
        t2 = (EditText) findViewById(R.id.userpass);
        t3 = (EditText) findViewById(R.id.useremail);


        b1 = (Button) findViewById(R.id.regbtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = t1.getText().toString();
                pass = t2.getText().toString();
                email = t3.getText().toString();

                if (name.equals("") || pass.equals("")||email.equals("") ) {
                    Toast.makeText(Register.this, "Plz fill all the filds", Toast.LENGTH_SHORT).show();
                } else {
                    volleyCall(name, pass,email);

                }


            }
        });
    }

    private void volleyCall(String name, String pass,String email) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "http://192.168.43.140:8080/Demo/rest/hello/register";
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("name", name);
        jsonParams.put("pass", pass);
        jsonParams.put("email",email);
        final ProgressDialog progressDialog=new ProgressDialog(Register.this);
        progressDialog.setMessage("Work in is Progress...");
        progressDialog.setTitle("Please Wait...!");
        progressDialog.setMax(1000);
        progressDialog.setCancelable(false);
        progressDialog.show();

        final AlertDialog.Builder builder=new AlertDialog.Builder(Register.this);



        Log.d(TAG, "Json:" + new JSONObject(jsonParams));
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String msg = null;
                        try {
                            msg = (String) response.get("msg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // String msg=response.toString();
                        if (msg.equals("sucess")) {

                            progressDialog.dismiss();
                            builder.setMessage(msg);
                            builder.setIcon(R.mipmap.ic_launcher);
                            builder.setTitle("Alert..!!");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(Register.this, VarifyUser.class);
                                    startActivity(intent);

                                }
                            });

                            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(Register.this, Register.class);
                                    startActivity(intent);

                                }
                            });
                            AlertDialog alertDialog=builder.create();
                            alertDialog.setCancelable(false);
                            alertDialog.show();

                        }
                        else {

                            progressDialog.dismiss();
                            builder.setMessage(msg);
                            builder.setIcon(R.mipmap.ic_launcher);
                            builder.setTitle("Alert..!!");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(Register.this, VarifyUser.class);
                                    startActivity(intent);

                                }
                            });

                            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(Register.this, Register.class);
                                    startActivity(intent);

                                }
                            });
                            AlertDialog alertDialog=builder.create();
                            alertDialog.setCancelable(false);
                            alertDialog.show();






                        }    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //   Handle Error
                        Log.d(TAG, "Error: " + error
                                + "\nmessage" + error.getMessage());
                    }
                });
        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        postRequest.setRetryPolicy(policy);
        queue.add(postRequest);

    }
}