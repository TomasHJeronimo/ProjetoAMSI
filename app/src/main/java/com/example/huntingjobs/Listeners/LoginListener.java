package com.example.huntingjobs.Listeners;

import android.content.Context;

public interface LoginListener {

void onValidateLogin(final String username, final String password, final Context context);
}
