package com.ohgo.ohgo.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.activities.NewEmployeeActivity;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Ruben on 6/6/15.
 */
public class CameraFragment extends Fragment
{
    public static final String TAG = "CameraFragment";

    private Camera camera;
    private ParseFile photoFile;

    @InjectView(R.id.camera_photo_button)
    ImageButton photoButton;
    @InjectView(R.id.camera_surface_view)
    SurfaceView surfaceView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_camera, parent, false);
        ButterKnife.inject(this,v);

        if (camera == null)
        {
            try
            {
                camera = Camera.open();
                photoButton.setEnabled(true);
            } catch (Exception e) {
                Log.e(TAG, "No camera with exception: " + e.getMessage());
                photoButton.setEnabled(false);
                Toast.makeText(getActivity(), "No camera detected",
                        Toast.LENGTH_LONG).show();
            }
        }

        photoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (camera == null)
                    return;
                camera.takePicture(new Camera.ShutterCallback()
                {
                    @Override
                    public void onShutter()
                    {}

                }, null, new Camera.PictureCallback()
                {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera)
                    {
                        saveScaledPhoto(data);
                    }
                });
            }
        });
        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback()
        {
            public void surfaceCreated(SurfaceHolder holder)
            {
                try {
                    if (camera != null)
                    {
                        camera.setDisplayOrientation(90);
                        camera.setPreviewDisplay(holder);
                        camera.startPreview();
                    }
                } catch (IOException e)
                {
                    Log.e(TAG, "Error setting up preview", e);
                }
            }

            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height)
            {
                // nothing to do here
            }

            public void surfaceDestroyed(SurfaceHolder holder)
            {
                // nothing here
            }

        });

        return v;
    }

    /*
     * ParseQueryAdapter loads ParseFiles into a ParseImageView at whatever size
     * they are saved. Since we never need a full-size image in our app, we'll
     * save a scaled one right away.
     */
    private void saveScaledPhoto(byte[] data)
    {
        // Resize photo from camera byte array
        Bitmap mealImage = BitmapFactory.decodeByteArray(data, 0, data.length);
        Bitmap mealImageScaled = Bitmap.createScaledBitmap(mealImage, 200, 200
                * mealImage.getHeight() / mealImage.getWidth(), false);
        // Override Android default landscape orientation and save portrait
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedScaledMealImage = Bitmap.createBitmap(mealImageScaled, 0,
                0, mealImageScaled.getWidth(), mealImageScaled.getHeight(),
                matrix, true);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        rotatedScaledMealImage.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        byte[] scaledData = bos.toByteArray();

        // Save the scaled image to Parse
        Log.d("photo", String.valueOf(scaledData));
        photoFile = new ParseFile("employee_photo.jpg", scaledData);
        photoFile.saveInBackground(new SaveCallback()
        {
            public void done(ParseException e)
            {
                if (e != null) {
                    Toast.makeText(getActivity(), "Error saving: " + e.getMessage(), Toast.LENGTH_LONG).show();
                } else
                {
                    addPhotoToEmployAndReturn(photoFile);
                }
            }
        });
    }

    private void addPhotoToEmployAndReturn(ParseFile photoFile)
    {
        ((NewEmployeeActivity) getActivity()).getEmployee().setPhotoFile(photoFile);
        FragmentManager fm = getActivity().getFragmentManager();
        fm.popBackStack("AddWorkerFragment",FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (camera == null)
        {
            try {
                camera = Camera.open();
                photoButton.setEnabled(true);
            } catch (Exception e) {
                Log.i(TAG, "No camera: " + e.getMessage());
                photoButton.setEnabled(false);
                Toast.makeText(getActivity(), "No camera detected",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onPause()
    {
        if (camera != null)
        {
            camera.stopPreview();
            camera.release();
        }
        super.onPause();
    }

}