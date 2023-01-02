package com.example.huntingjobs.Listeners;

import android.content.Context;

public interface RegistoListenener {
    void onValidateRegisto(final String username, final String email, final String password, final Context context);
}
