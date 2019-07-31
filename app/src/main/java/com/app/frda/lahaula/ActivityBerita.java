package com.app.frda.lahaula;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;

import com.app.frda.lahaula.network.ApiServices;
import com.app.frda.lahaula.network.InitRetrofit;
import com.app.frda.lahaula.response.BeritaItem;
import com.app.frda.lahaula.response.ResponseBerita;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityBerita extends AppCompatActivity {

    private TextView Hasil;

    // Deklarasi Widget
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Hasil = findViewById(R.id.outout);
        // Inisialisasi Widget
        recyclerView = (RecyclerView) findViewById(R.id.rvListBerita);
        // RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Eksekusi method
        tampilBerita();
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            //Memanggil/Memasang menu item pada toolbar dari layout menu_bar.xml
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_bar, menu);
            MenuItem searchIem = menu.findItem(R.id.search);
            final SearchView searchView = (SearchView) searchIem.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Hasil.setText("Hasil Pencarian: "+query);
                    Toast.makeText(getApplicationContext(),query, Toast.LENGTH_SHORT).show();

                    searchView.clearFocus();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
            return true;
        }



    private void tampilBerita() {
        ApiServices api = InitRetrofit.getInstance();
        // Siapkan request
        Call<ResponseBerita> beritaCall = api.request_show_all_berita();
        // Kirim request
        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<BeritaItem> data_berita = response.body().getBerita();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
                    if (status){
                        // Buat Adapter untuk recycler view
                        AdapterBerita adapter = new AdapterBerita(ActivityBerita.this, data_berita);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // kalau tidak true
                        Toast.makeText(ActivityBerita.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });
    }
}