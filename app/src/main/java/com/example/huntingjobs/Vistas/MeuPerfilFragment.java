package com.example.huntingjobs.Vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MeuPerfilFragment extends Fragment {

    private TextView tvNome, tvMail;
    private ImageView imageViewProfile;
    private SharedPreferences prefs;


    public MeuPerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        SharedPreferences sharedPreference = getContext().getSharedPreferences(SingletonGestorAnuncios.DADOS_USER, Context.MODE_PRIVATE);
        String mailUser = sharedPreference.getString(SingletonGestorAnuncios.MAIL, "Sem email: Algum erro ocorreu");
        String username = sharedPreference.getString(SingletonGestorAnuncios.USERNAME, "Sem email: Algum erro ocorreu");

        View view = inflater.inflate(R.layout.fragment_meu_perfil, container, false);

        imageViewProfile = view.findViewById(R.id.iv_profile_picture);
        tvNome = view.findViewById(R.id.tv_name);
        tvMail = view.findViewById(R.id.tv_email);

        tvNome.setText(username);
        tvMail.setText(mailUser);

        prefs = getContext().getSharedPreferences("profile_image", Context.MODE_PRIVATE);

        if(getProfileImage() != null){
            Bitmap bitmap = getProfileImage();
            imageViewProfile.setImageBitmap(bitmap);
        }

        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                int PICK_IMAGE_REQUEST = 1;
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && data.getData() != null) {
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                // Display the selected image to the ImageView
                imageViewProfile.setImageBitmap(bitmap);
                //Save the image to the SharedPreferences
                saveProfileImage(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap getProfileImage() {
        String imageString = prefs.getString("profile_image", null);
        if (imageString == null) {
            return null;
        }
        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void saveProfileImage(Bitmap bitmap) {
        byte[] byteArray = bitmapToByteArray(bitmap);
        prefs.edit().putString("profile_image", Base64.encodeToString(byteArray, Base64.DEFAULT)).apply();
    }



}