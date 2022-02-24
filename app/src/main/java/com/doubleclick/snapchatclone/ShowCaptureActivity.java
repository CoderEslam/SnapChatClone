package com.doubleclick.snapchatclone;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.doubleclick.snapchatclone.fragment.CameraFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileNotFoundException;

public class ShowCaptureActivity extends AppCompatActivity {

    String Uid;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_capture);

//        Bundle bundle = getIntent().getExtras();
//        assert bundle !=null ;
//        byte[] bytes = bundle.getByteArray("Captuer");
        ImageView mImage = findViewById(R.id.imageCaptured);
//        if (bytes!=null){
//            Bitmap decodeBitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//            Bitmap rotatoBitmap = rotatoBitmap(decodeBitmap);
//            mImage.setImageBitmap(rotatoBitmap);
//
//        }

        try {
            bitmap = BitmapFactory.decodeStream(getApplication().openFileInput("imageToSend"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            finish();
            return;
        }



        mImage.setImageBitmap(bitmap);

        Uid = FirebaseAuth.getInstance().getUid();

        Button mSend = findViewById(R.id.send);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChooseReceiverActivity.class);
                startActivity(intent);
                return;
            }
        });


    }

    private Bitmap rotatoBitmap(Bitmap decodeBitmap) {
        int Width = decodeBitmap.getWidth();
        int Height  = decodeBitmap.getHeight();
        // to how much to rotate the Image
        Matrix matrix = new Matrix();
        matrix.setRotate(90);                  //0,0 => from Bigan , Width*Height => dimentunal of Image
        return Bitmap.createBitmap(decodeBitmap,0,0,Width,Height,matrix,true);

    }

//    String Uid;
//    Bitmap bitmap;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_capture);
//
//        final byte[] img = CameraFragment.imageByte;
//
//        try {
//            bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
//        } catch (Exception e) {
//            e.printStackTrace();
//            finish();
//        }
//
//        ImageView imgView = findViewById(R.id.imageViewCapturedImageShowCapture);
//        imgView.setImageBitmap(bitmap);
//
//        Uid = FirebaseAuth.getInstance().getUid();
//
//        FloatingActionButton mSend = findViewById(R.id.buttonStorySend);
//        mSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), ChooseReceiverActivity.class);
//                startActivity(intent);
//            }
//        });
//    }


}
