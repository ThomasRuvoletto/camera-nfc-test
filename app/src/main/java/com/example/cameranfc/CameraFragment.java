package com.example.cameranfc;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cameranfc.camera.CameraSource;
import com.example.cameranfc.databinding.FragmentCameraBinding;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class CameraFragment extends Fragment {

    private FragmentCameraBinding binding;
    private CameraSource cameraSource;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCameraBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BarcodeDetector detector = new BarcodeDetector.Builder(requireActivity()).build();
        this.cameraSource = new CameraSource.Builder(getActivity(), detector).build();

        binding.buttonCameraBack.setOnClickListener(view1 -> NavHostFragment.findNavController(CameraFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment));

        binding.cameraSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MainActivity.CAMERA_PERMISSION_CODE);
                        return;
                    }
                    cameraSource.start(holder);
                } catch (IOException ignored) {
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                cameraSource.release();
                cameraSource = null;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}