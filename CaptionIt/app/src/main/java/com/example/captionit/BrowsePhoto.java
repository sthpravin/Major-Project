package com.example.captionit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class BrowsePhoto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_photo);


        //invoke the image gallery using an implicit intent
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        //where do we want to find the data
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();

        //finally get a URI representation
        Uri data = Uri.parse(pictureDirectoryPath);

        //set the data and type

        photoPickerIntent.setDataAndType(data, "image/*");
        startActivityForResult(photoPickerIntent, 95);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView imageView = (ImageView) findViewById(R.id.imageView_browseImage);

        if (resultCode == RESULT_OK) {

            if(requestCode == 95) {
                //the address of image in sd card
                Uri returnImageUri = data.getData();
                //declare a stream to read data form sd card
                InputStream inputStream;
                /* we are getting an input stream based on URI of the image */
                try {
                    inputStream = getContentResolver().openInputStream(returnImageUri);
                    //image is loaded to the program
                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    imageView.setImageBitmap(image);
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                    //show a message to the user indicating error
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
