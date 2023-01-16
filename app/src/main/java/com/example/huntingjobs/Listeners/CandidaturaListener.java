package com.example.huntingjobs.Listeners;

import android.content.Context;

public interface CandidaturaListener {
    void onValidateCandidatura(final String mensagem, final String id_anuncio, final String id_user, final Context context);

}
