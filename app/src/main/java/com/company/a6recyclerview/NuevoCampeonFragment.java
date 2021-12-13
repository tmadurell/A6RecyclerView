package com.company.a6recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.*;


import com.company.a6recyclerview.databinding.FragmentNuevoCampeonBinding;


public class NuevoCampeonFragment extends Fragment {
    private FragmentNuevoCampeonBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNuevoCampeonBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CampeonesViewModel campeonesViewModel = new ViewModelProvider(requireActivity()).get(CampeonesViewModel.class);
        NavController navController = Navigation.findNavController(view);
        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();
                String descripcion = binding.descripcion.getText().toString();



                campeonesViewModel.insertar(new Campeon(nombre, descripcion));

                navController.popBackStack();
            }
        });


    }
}