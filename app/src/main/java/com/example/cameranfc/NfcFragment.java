package com.example.cameranfc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cameranfc.databinding.FragmentNfcBinding;
import com.example.cameranfc.nfc.NFCAdapter;

public class NfcFragment extends Fragment {

    private FragmentNfcBinding binding;
    private NFCAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNfcBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new NFCAdapter(getActivity());
        adapter.EnableReaderMode();

        binding.buttonNfcBack.setOnClickListener(view1 -> NavHostFragment.findNavController(NfcFragment.this)
                .navigate(R.id.action_ThirdFragment_to_FirstFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        adapter = null;
    }
}