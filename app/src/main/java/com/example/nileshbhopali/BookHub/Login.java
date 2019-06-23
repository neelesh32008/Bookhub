package com.example.nileshbhopali.BookHub;
import android.content.Intent;
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
public class Login extends AppCompatActivity {

    private static final String TAG ="event" ;
    EditText t1,t2;
    Button b1;
    String email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        t1 = (EditText) findViewById(R.id.username);
        t2 = (EditText) findViewById(R.id.userpass);
        b1 = (Button) findViewById(R.id.loginbtn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email= t1.getText().toString();
                pass = t2.getText().toString();
                if (email.equals("") || pass.equals("")) {
                    Toast.makeText(Login.this, "Plz fill all the filds", Toast.LENGTH_SHORT).show();
                } else {
                    volleyCall(email,pass);

                }


            }
        });
    }

    private void volleyCall(final String email, String pass) {


        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "http://192.168.43.140:8080/Demo/rest/hello/login";
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("email",email);
        jsonParams.put("pass", pass);
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

                        if (msg.equals("success")) {
                            Toast.makeText(Login.this, "Login Sucess...", Toast.LENGTH_SHORT).show();

                            User user=new User(Login.this);
                            user.setName(email);
                            Intent intent = new Intent(Login.this, sellandbuyActivity.class);
                            intent.putExtra("name",email);
                            startActivity(intent);
                        } else  {
                            Toast.makeText(Login.this, "Invalid USer name or pass...", Toast.LENGTH_SHORT).show();


                            Log.d(TAG, "Json" + response);
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //   Handle Error
                        Log.d(TAG, "Error: " + error
                                + "\nmessage" + error.getMessage());
                    }
                });
        //    postRequest.setShouldCache(false);
        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        postRequest.setRetryPolicy(policy);
        queue.add(postRequest);
    }


    public void next(View view)
    {
        Intent intent=new Intent(Login.this,Register.class);
        startActivity(intent);


    }
}
