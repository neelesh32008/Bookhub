package com.example.nileshbhopali.BookHub;



import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class VarifyUser extends AppCompatActivity {

    private static final String TAG ="Event" ;
    EditText t1;
    String otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varify_user);
        t1=(EditText)findViewById(R.id.otp);

    }


    public void press(View view) {

        otp = t1.getText().toString();
        if (otp.equals("")) {
            Toast.makeText(this, "Pleaase Eneter Otp", Toast.LENGTH_SHORT).show();

        } else {

            volleyCall(otp);
        }
    }


    private void volleyCall(String otp) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "http://192.168.43.140:8080/Demo/rest/hello/verify";
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("otp",otp);

        final ProgressDialog progressDialog=new ProgressDialog(VarifyUser.this);
        progressDialog.setMessage("Work in is Progress...");
        progressDialog.setTitle("Please Wait...!");
        progressDialog.setMax(1000);
        progressDialog.setCancelable(false);
        progressDialog.show();

        final AlertDialog.Builder builder=new AlertDialog.Builder(VarifyUser.this);



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
                        if (msg.equals("useractivated")) {

                            progressDialog.dismiss();
                            builder.setMessage(msg);
                            builder.setIcon(R.mipmap.ic_launcher);
                            builder.setTitle("Alert..!!");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(VarifyUser.this, Login.class);
                                    startActivity(intent);

                                }
                            });

                            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(VarifyUser.this, VarifyUser.class);
                                    startActivity(intent);

                                }
                            });
                            AlertDialog alertDialog=builder.create();
                            alertDialog.setCancelable(false);
                            alertDialog.show();

                        }

                        else if (msg.equals("usernotactivated")) {

                            progressDialog.dismiss();
                            builder.setMessage(msg);
                            builder.setIcon(R.mipmap.ic_launcher);
                            builder.setTitle("Alert..!!");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(VarifyUser.this, VarifyUser.class);
                                    startActivity(intent);

                                }
                            });

                            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(VarifyUser.this, VarifyUser.class);
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

                                    Intent intent = new Intent(VarifyUser.this, VarifyUser.class);
                                    startActivity(intent);

                                }
                            });

                            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(VarifyUser.this, Register.class);
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




