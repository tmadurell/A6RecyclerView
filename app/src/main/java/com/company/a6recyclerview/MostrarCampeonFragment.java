package com.company.a6recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.company.a6recyclerview.databinding.FragmentMostrarCampeonBinding;


public class MostrarCampeonFragment extends Fragment {
    private FragmentMostrarCampeonBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarCampeonBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CampeonesViewModel campeonesViewModel = new ViewModelProvider(requireActivity()).get(CampeonesViewModel.class);

        campeonesViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Campeon>() {
            @Override
            public void onChanged(Campeon campeon) {
                binding.imagen.setImageResource(campeon.imagen);
                binding.nombre.setText(campeon.nombre);
                binding.descripcion.setText(campeon.descripcion);
                binding.valoracion.setRating(campeon.valoracion);

                binding.valoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        if(fromUser){
                            campeonesViewModel.actualizar(campeon, rating);
                        }
                    }
                });
            }
        });
    }
}