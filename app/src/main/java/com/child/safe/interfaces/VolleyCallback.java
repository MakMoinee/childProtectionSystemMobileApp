package com.child.safe.interfaces;

import com.android.volley.VolleyError;

public interface VolleyCallback {
    /**
     *
     * @param response - response from the request
     */
    void onSuccess(String response);

    /**
     *
     * @param error - error from the request
     */
    void onError(VolleyError error);
}
