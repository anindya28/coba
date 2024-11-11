package com.example.perpusonline;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvBuku;
    private Button btRefresh;
    private List<Buku> bukus;
    private BukuAdapter adapterBuku;
    private RequestQueue antrian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rvBuku = findViewById(R.id.rvBuku);
        this.btRefresh = findViewById(R.id.btRefresh);
        this.btRefresh.setOnClickListener(this);

        this.bukus = new ArrayList<>();
        this.adapterBuku = new BukuAdapter(this, this.bukus);
        this.rvBuku.setLayoutManager(new LinearLayoutManager(this));
        this.rvBuku.setAdapter(this.adapterBuku);

        this.antrian = Volley.newRequestQueue(this);



    }

    @Override
    public void onClick(View view) {
        String url = "https://mgm.ub.ac.id/book.php";
        StringRequest req = new StringRequest(
                Request.Method.GET,
                url,
                response -> {
                    Gson gson = new Gson();
                    Buku[] bukus = gson.fromJson(response, Buku[].class);
                    this.bukus.clear();
                    for (Buku b : bukus){
                        this.bukus.add(b);
                    }
                    this.adapterBuku.notifyDataSetChanged();
                },
                error -> {
                    Toast.makeText(this, error.getMessage(),
                            Toast.LENGTH_LONG).show();
                });
        this.antrian.add(req);
    }
}