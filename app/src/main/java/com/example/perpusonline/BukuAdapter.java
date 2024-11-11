package com.example.perpusonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BukuAdapter extends RecyclerView.Adapter {

    private final Context ctx;
    private final List<Buku> bukus;

    public BukuAdapter(Context ctx, List<Buku>  bukus){
        this.ctx = ctx;
        this.bukus = bukus;
    }

    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvAuthor;
        private final TextView tvJudul;
        private Buku buku;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.tvAuthor = itemView.findViewById(R.id.tvAuthor);
            this.tvJudul = itemView.findViewById(R.id.tvJudul);

            itemView.setOnClickListener(this);
        }

        public  void setBuku (Buku b){
            this.buku = b;
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(ctx, this.buku.judul, Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.ctx).inflate(R.layout.item_buku, parent, false);
        VH vh = new VH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Buku b = this.bukus.get(position);
        VH vh = (VH) holder;
        vh.setBuku(b);
        vh.tvJudul.setText(b.judul);
        vh.tvAuthor.setText(b.author);
    }

    @Override
    public int getItemCount() {
        return this.bukus.size();
    }
}
