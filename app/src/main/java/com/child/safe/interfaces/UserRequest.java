package com.child.safe.interfaces;

import android.app.ProgressDialog;
import android.content.Context;

public interface UserRequest {
    void login(Context mContext, String un, String pw, ProgressDialog dialog, VolleyCallback callback);
}
