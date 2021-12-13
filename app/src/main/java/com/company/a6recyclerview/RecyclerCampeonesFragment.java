package com.company.a6recyclerview;

import android.os.Bundle;
import android.view.*;
import android.widget.RatingBar;

import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.*;
import androidx.navigation.*;
import androidx.recyclerview.widget.*;


import com.bumptech.glide.Glide;
import com.company.a6recyclerview.databinding.*;

import java.util.List;


public class RecyclerCampeonesFragment extends Fragment {

    private FragmentRecyclerCampeonesBinding binding;
    private CampeonesViewModel campeonesViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerCampeonesBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        campeonesViewModel = new ViewModelProvider(requireActivity()).get(CampeonesViewModel.class);
        navController = Navigation.findNavController(view);

        binding.irANuevoCampeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_recyclerCampeonesFragment_to_nuevoCampeonFragment);
            }
        });

        CampeonesAdapter campeonesAdapter = new CampeonesAdapter();
        binding.recyclerView.setAdapter(campeonesAdapter);

        campeonesViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Campeon>>() {
            @Override
            public void onChanged(List<Campeon> campeones) {
                campeonesAdapter.establecerLista(campeones);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Campeon campeon = campeonesAdapter.obtenerCampeon(posicion);
                campeonesViewModel.eliminar(campeon);

            }
        }).attachToRecyclerView(binding.recyclerView);
    }

    class CampeonViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderCampeonBinding binding;

        public CampeonViewHolder(ViewholderCampeonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class CampeonesAdapter extends RecyclerView.Adapter<CampeonViewHolder> {

        List<Campeon> campeones;

        @NonNull
        @Override

        public CampeonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CampeonViewHolder(ViewholderCampeonBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CampeonViewHolder holder, int position) {

            Campeon campeon = campeones.get(position);

            holder.binding.nombre.setText(campeon.nombre);
            holder.binding.valoracion.setRating(campeon.valoracion);

            Glide.with(RecyclerCampeonesFragment.this).load(campeon.imagen).into(holder.binding.imagen);

            holder.binding.valoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(fromUser) {
                        campeonesViewModel.actualizar(campeon, rating);
                    }
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    campeonesViewModel.seleccionar(campeon);
                    navController.navigate(R.id.action_recyclerCampeonesFragment_to_mostrarCampeonesFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return campeones != null ? campeones.size() : 0;
        }

        public void establecerLista(List<Campeon> campeones){
            this.campeones = campeones;
            notifyDataSetChanged();
        }
        public Campeon obtenerCampeon(int posicion){
            return campeones.get(posicion);
        }
    }
}