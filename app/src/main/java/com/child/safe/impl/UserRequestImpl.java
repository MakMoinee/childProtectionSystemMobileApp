package com.child.safe.impl;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.child.safe.common.Constants;
import com.child.safe.interfaces.UserRequest;
import com.child.safe.interfaces.VolleyCallback;
import com.child.safe.models.Users;
import com.child.safe.utility.LocalUtil;
import com.google.gson.Gson;

public class UserRequestImpl implements UserRequest {

    @Override
    public void login(Context mContext, String un, String pw, ProgressDialog dialog, VolleyCallback callback) {
        if (un.equals("") || pw.equals("")) {
            VolleyError err = new VolleyError("Empty username or password");
            callback.onError(err);
            return;
        }

        boolean isConnected = LocalUtil.isNetworkConnect(mContext);

        if (!isConnected) {
            VolleyError err = new VolleyError("Please connect first to a network");
            callback.onError(err);
            return;
        }

        Users users = new Users();
        users.setUsername(un);
        users.setPassword(pw);
        String finalBody = new Gson().toJson(users);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        }) {
            @Override
            public String getBodyContentType() {
                return Constants.ContentBody;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return finalBody.getBytes();
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);
    }
}
