package com.doubleclick.snapchatclone.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.util.Size;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.doubleclick.snapchatclone.FindUsersActivity;
import com.doubleclick.snapchatclone.ProfileActivity;
import com.doubleclick.snapchatclone.R;
import com.doubleclick.snapchatclone.ShowCaptureActivity;
import com.doubleclick.snapchatclone.loginRegistration.SplashScreenActivity;
import com.doubleclick.snapchatclone.recyclerViewFollow.FollowAdapter;
import com.doubleclick.snapchatclone.recyclerViewFollow.FollowObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CameraFragment extends
        Fragment implements SurfaceHolder.Callback{
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    Camera camera;
//    Camera.PictureCallback jpegCallback;
//    SurfaceView mSurfaceView;
//    SurfaceHolder mSurfaceHolder;
//
//    final int CAMERA_REQUEST_CODE = 1;
//    final int EXTERNAL_STORAGE_CODE = 2;
//    final int EXTERNAL_STORAGE_READ_CODE = 3;
//
//    private final double MAX_ASPECT_DISTORTION = 0.15;
//    private final float ASPECT_RATIO_TOLERANCE = 0.01f;
//
//    private ImageButton profileButton;
//    private int screenWidth;
//    private int screenHeight;
//    private Size previewSize;
//    private CameraDevice.StateCallback stateCallback;
//
//    private Handler backgroundHandler;
//    private HandlerThread backgroundThread;
//
//    private CameraDevice cameraDevice;
//    public static CameraFragment newInstance(){
//        CameraFragment fragment = new CameraFragment();
//        return fragment;
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_camera , container, false);
//
//        mSurfaceView = view.findViewById(R.id.surfaceView);
//        mSurfaceHolder = mSurfaceView.getHolder();
//
//        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(getActivity(), new String[] {android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
//        }else{
//            mSurfaceHolder.addCallback(this);
//            mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//        }
//
////
//        Button mCapture = view.findViewById(R.id.capture);
//        mCapture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                captureImage();
//            }
//        });
//
//
//        jpegCallback = new Camera.PictureCallback(){
//            @Override
//            public void onPictureTaken(byte[] bytes, Camera camera) {
//
//                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//
//                Bitmap rotateBitmap = rotate(decodedBitmap);
//
//                String fileLocation = SaveImageToStorage(rotateBitmap);
//                if(fileLocation!= null){
//                    Intent intent = new Intent(getActivity(), ShowCaptureActivity.class);
////                    intent.putExtra("Captuer",bytes);
//                    startActivity(intent);
//                    return;
//                }
//            }
//        };
//        profileButton = view.findViewById(R.id.imageButtonProfile);
//        profileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), ProfileActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        return view;
//    }
//
//
//    public String SaveImageToStorage(Bitmap bitmap){
//        String fileName = "imageToSend";
//        try{
//            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//            FileOutputStream fileOutputStream = getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
//            fileOutputStream.write(bytes.toByteArray());
//            fileOutputStream.close();
//        }catch(Exception e){
//            e.printStackTrace();
//            fileName = null;
//        }
//        return fileName;
//    }
//
//    //to rotate Image 90 degree
//    private Bitmap rotate(Bitmap decodedBitmap) {
//        int Width = decodedBitmap.getWidth();
//        int Height = decodedBitmap.getHeight();
//        Matrix matrix = new Matrix();
//        matrix.setRotate(90);
//
//        return Bitmap.createBitmap(decodedBitmap, 0, 0, Width, Height, matrix, true);
//
//    }
//
//    private void captureImage() {
//        camera.takePicture(null, null, jpegCallback);
//    }
//
//
//    @Override
//    public void surfaceCreated(SurfaceHolder surfaceHolder) {
//        camera = Camera.open();
//
//        Camera.Parameters parameters;
//        parameters = camera.getParameters();
//
//        camera.setDisplayOrientation(90);
//        parameters.setPreviewFrameRate(30);
//        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
//
//
//        Camera.Size bestSize = null;
//        List<Camera.Size> sizeList = camera.getParameters().getSupportedPreviewSizes();
//        bestSize = sizeList.get(0);
//        for(int i = 1; i < sizeList.size(); i++){
//            if((sizeList.get(i).width * sizeList.get(i).height) > (bestSize.width * bestSize.height)){
//                bestSize = sizeList.get(i);
//            }
//        }
//
//        parameters.setPictureSize(bestSize.width, bestSize.height);
//
//
//        camera.setParameters(parameters);
//
//        try {
//            camera.setPreviewDisplay(surfaceHolder);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        camera.startPreview();
//
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//    }
//    @Override
//    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case CAMERA_REQUEST_CODE:{
//                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    mSurfaceHolder.addCallback(this);
//                    mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//                }else{
//                    Toast.makeText(getContext(), "Please provide the permission", Toast.LENGTH_LONG).show();
//                }
//                break;
//            }
//        }
//    }
//
//    private void FindUsers() {
//        Intent intent = new Intent(getContext(), FindUsersActivity.class);
//        startActivity(intent);
//        return;
//    }
//
//    private void LogOut() {
//        FirebaseAuth.getInstance().signOut();
//        Intent intent = new Intent(getContext(), SplashScreenActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//        return;
//    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//}

    final int CAMERA_REQUEST_CODE = 1;
    final int EXTERNAL_STORAGE_CODE = 2;
    final int EXTERNAL_STORAGE_READ_CODE = 3;

    private final double MAX_ASPECT_DISTORTION = 0.15;
    private final float ASPECT_RATIO_TOLERANCE = 0.01f;

    private CameraManager cameraManager;
    private int cameraFacing;
    private String cameraId;

    private TextureView.SurfaceTextureListener surfaceTextureListener;

    private int screenWidth;
    private int screenHeight;
    private Size previewSize;

    private CameraDevice.StateCallback stateCallback;

    private Handler backgroundHandler;
    private HandlerThread backgroundThread;

    private CameraDevice cameraDevice;

    private TextureView textureView;
    private CameraCaptureSession cameraCaptureSession;
    private CaptureRequest.Builder captureRequestBuilder;

    private File galleryFolder;

    private CaptureRequest captureRequest;

    public static String currentPicture;

    private ImageButton cameraSwitch, flashButton, profileButton;

    Boolean isTourchOn = false;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    EditText searchUsers;

    private ArrayList<FollowObject> results = new ArrayList<>();

    public static byte[] imageByte;

    public static CameraFragment newInstance() {
        CameraFragment fragment = new CameraFragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        textureView = view.findViewById(R.id.surfaceView);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);

        }

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_CODE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

            if (result != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_READ_CODE);
            }
        } else {
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_READ_CODE);
        }


        cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
        cameraFacing = CameraCharacteristics.LENS_FACING_BACK;

        surfaceTextureListener = initSurfaceTextureListener();
        stateCallback = initStateCallback();

        createImageGallery();

        ImageButton mFindUsers = view.findViewById(R.id.searchUsers);


        cameraSwitch = view.findViewById(R.id.imageButtonCameraSwitch);
        flashButton = view.findViewById(R.id.imageButtonFlashSwitch);
        profileButton = view.findViewById(R.id.imageButtonProfile);
//        Button cupture = view.findViewById(R.id.capture);
//        cupture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                captureImage();
//            }
//        });

        cameraSwitch.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (cameraFacing == CameraCharacteristics.LENS_FACING_FRONT) {
                    cameraFacing = CameraCharacteristics.LENS_FACING_BACK;
                } else {
                    cameraFacing = CameraCharacteristics.LENS_FACING_FRONT;
                }
                cameraDevice.close();

                startOpenCamera();
            }
        });

        flashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (isTourchOn) {
                            captureRequestBuilder.set(CaptureRequest.FLASH_MODE, CameraMetadata.FLASH_MODE_OFF);
                        } else {
                            captureRequestBuilder.set(CaptureRequest.FLASH_MODE, CameraMetadata.FLASH_MODE_TORCH);
                        }
                        cameraCaptureSession.setRepeatingRequest(captureRequestBuilder.build(), null, backgroundHandler);
                        isTourchOn = !isTourchOn;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        mFindUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findUsers();
            }
        });

        searchUsers = view.findViewById(R.id.cameraFindUsers);

        mRecyclerView = view.findViewById(R.id.recyclerViewFindUsersCamera);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new FollowAdapter(results, getActivity());

        mRecyclerView.setAdapter(adapter);

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            imm.hideSoftInputFromWindow(textureView.getWindowToken(), 0);
        }

        searchUsers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (searchUsers.getText().length() > 0) {
                    clear();
                    listenForData();
                }
            }
        });

        textureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchUsers.setText("");

                if (results.size() > 0) {
                    results.clear();
                    adapter.notifyDataSetChanged();
                }

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isAcceptingText()) {
                    imm.hideSoftInputFromWindow(textureView.getWindowToken(), 0);
                }
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startOpenCamera() {
        if (textureView.isAvailable()) {
            setupCamera(screenWidth, screenHeight);
            openCamera();
        } else {
            textureView.setSurfaceTextureListener(surfaceTextureListener);
        }
    }

    public void captureImage() {
        FileOutputStream outputPhoto = null;
        try {
            outputPhoto = new FileOutputStream(createImageFile(galleryFolder));

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            textureView.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, out);

            out.writeTo(outputPhoto);

            switchFragment(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputPhoto != null) {
                    outputPhoto.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void switchFragment(byte[] img) {
        Intent intent = new Intent(getContext(), ShowCaptureActivity.class);
        imageByte = img;
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private CameraDevice.StateCallback initStateCallback() {
        return new CameraDevice.StateCallback() {
            @Override
            public void onOpened(@NonNull CameraDevice camera) {
                cameraDevice = camera;
                createCameraPreviewSession();
            }

            @Override
            public void onDisconnected(@NonNull CameraDevice cameraDevice) {
                cameraDevice.close();
                CameraFragment.this.cameraDevice = null;
            }

            @Override
            public void onError(@NonNull CameraDevice cameraDevice, int i) {
                cameraDevice.close();
                CameraFragment.this.cameraDevice = null;
            }
        };
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createCameraPreviewSession() {
        try {
            SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
            surfaceTexture.setDefaultBufferSize(previewSize.getWidth(), previewSize.getHeight());
            Surface previewSurface = new Surface(surfaceTexture);
            captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.set(CaptureRequest.FLASH_MODE, CameraMetadata.FLASH_MODE_OFF);

            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON);
            captureRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, CaptureRequest.CONTROL_AWB_MODE_AUTO);
            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_START);
            captureRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, CameraMetadata.CONTROL_AE_PRECAPTURE_TRIGGER_START);
            captureRequestBuilder.set(CaptureRequest.COLOR_CORRECTION_MODE, CameraMetadata.COLOR_CORRECTION_MODE_TRANSFORM_MATRIX);

            captureRequestBuilder.addTarget(previewSurface);

            cameraDevice.createCaptureSession(Collections.singletonList(previewSurface),
                    new CameraCaptureSession.StateCallback() {

                        @Override
                        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                            if (cameraDevice == null) {
                                return;
                            }

                            try {
                                captureRequest = captureRequestBuilder.build();
                                CameraFragment.this.cameraCaptureSession = cameraCaptureSession;
                                CameraFragment.this.cameraCaptureSession.setRepeatingRequest(captureRequest,
                                        null, backgroundHandler);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {

                        }
                    }, backgroundHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TextureView.SurfaceTextureListener initSurfaceTextureListener() {
        return new TextureView.SurfaceTextureListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                setupCamera(screenWidth, screenHeight);
                openCamera();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

            }
        };
    }

    private void createImageGallery() {
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        galleryFolder = new File(storageDirectory, getResources().getString(R.string.app_name));
        if (!galleryFolder.exists()) {
            boolean wasCreated = galleryFolder.mkdirs();
            if (!wasCreated) {
                Log.e("CapturedImages", "Failed to create directory");
            }
        }
    }

    private File createImageFile(File galleryFolder) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "image_" + timeStamp + "_";
        currentPicture = imageFileName;
        return File.createTempFile(imageFileName, ".png", galleryFolder);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openCamera() {
        try {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                cameraManager.openCamera(cameraId, stateCallback, backgroundHandler);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupCamera(int width, int height) {
        try {
            for (String camId: cameraManager.getCameraIdList()) {
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(camId);
                if (cameraCharacteristics.get(CameraCharacteristics.LENS_FACING) == cameraFacing) {
                    StreamConfigurationMap streamConfigurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                    previewSize = chooseOptimalSize(streamConfigurationMap.getOutputSizes(SurfaceTexture.class),
                            streamConfigurationMap.getOutputSizes(ImageFormat.JPEG), width, height);
                    cameraId = camId;
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Size chooseOptimalSize(Size[] supportedPreviewSizes, Size[] supportedPictureSizes, int width, int height) {
        double scrrenAspectRatio = width / (double) height;
        Size optimalSize = supportedPreviewSizes[0];
        double currentMinDistortion = MAX_ASPECT_DISTORTION;
        for (Size previewSize: supportedPreviewSizes) {
            float previewAspectRatio = (float) previewSize.getWidth() / (float) previewSize.getHeight();
            for (Size pictureSize: supportedPictureSizes) {
                float pictureAspectRatio = (float) pictureSize.getWidth() / (float) pictureSize.getHeight();
                if (Math.abs(previewAspectRatio - pictureAspectRatio) < ASPECT_RATIO_TOLERANCE) {
                    Size tempSize = previewSize;
                    if (pictureSize != null) {
                        tempSize = pictureSize;
                    }

                    double aspectRatio = tempSize.getWidth() / (double) tempSize.getHeight();
                    double distortion = Math.abs(aspectRatio - scrrenAspectRatio);
                    if (distortion < currentMinDistortion) {
                        currentMinDistortion = distortion;
                        optimalSize = tempSize;
                    }
                    break;
                }
            }
        }
        return optimalSize;
    }

    private void openBackgroundTread() {
        backgroundThread = new HandlerThread("camera_background_thread");
        backgroundThread.start();
        backgroundHandler = new Handler(backgroundThread.getLooper());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResume() {
        super.onResume();

        openBackgroundTread();

        if (textureView.isAvailable()) {
            setupCamera(screenWidth, screenHeight);
            openCamera();
        } else {
            textureView.setSurfaceTextureListener(surfaceTextureListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        closeCamera();
        closeBackgroundThread();
    }

    private void closeBackgroundThread() {
        if (backgroundHandler != null) {
            backgroundThread.quitSafely();
            backgroundThread = null;
            backgroundHandler = null;
        }
    }

    private void closeCamera() {
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            cameraCaptureSession = null;
        }

        if (cameraDevice != null) {
            cameraDevice.close();
            cameraDevice = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(this).attach(this).commit();
                    }
                }
                break;
            case EXTERNAL_STORAGE_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(this).attach(this).commit();
                    }
                }
                break;
            case EXTERNAL_STORAGE_READ_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(this).attach(this).commit();
                    }
                }
                break;
        }
    }

    private void findUsers() {
        Intent intent = new Intent(getContext(), FindUsersActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("currentUsername", searchUsers.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void listenForData() {
        DatabaseReference usersDB = FirebaseDatabase.getInstance().getReference().child("users");
        Query query = usersDB.orderByChild("email").startAt(searchUsers.getText().toString()).endAt(searchUsers.getText().toString() + "\uf8ff");

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String username = "";
                String profileImageUrl = "";
                String uid = dataSnapshot.getRef().getKey();

                if (dataSnapshot.child("email").getValue() != null) {
                    username = dataSnapshot.child("email").getValue().toString();
                }

                if (dataSnapshot.child("profileImageUrl").getValue().toString() != null) {
                    profileImageUrl = dataSnapshot.child("profileImageUrl").getValue().toString();
                    Toast.makeText(getContext(),"CameraFragment At 802 "+profileImageUrl,Toast.LENGTH_LONG).show();
                }
                /*if uid you have get it equal my id
                * don't store it in ArrayList
                * */
                if (!uid.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                    FollowObject obj = new FollowObject(username, uid, profileImageUrl);
                    results.add(obj);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void clear() {
        int size = this.results.size();
        this.results.clear();
        adapter.notifyItemRangeChanged(0, size);
    }

    private ArrayList<FollowObject> getDataset() {
        listenForData();
        return results;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
