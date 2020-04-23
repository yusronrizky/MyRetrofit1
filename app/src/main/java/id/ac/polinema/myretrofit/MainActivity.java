package id.ac.polinema.myretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_title);

        //kode berikut digunakan untuk menampilkan recyclerview dengan fastadapter
        final RecyclerView siswaView = findViewById(R.id.rv_siswa);
        final ItemAdapter itemAdapter = new ItemAdapter<>();
        final FastAdapter fastAdapter = FastAdapter.with(itemAdapter);

        //array list untuk menampung data siswa
        final List siswa = new ArrayList<>();

        //kode berikut digunakan untuk menginstansiasi retrofit pada base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://yusronrizky.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call;
        if (TextUtils.isEmpty(getIntent().getStringExtra("nis"))) {
            call = jsonPlaceHolderApi.getPost();
        } else {
            call = jsonPlaceHolderApi.getPostById(getIntent().getStringExtra("nis"));
        }
        //kemudian memanggil method getPost dari class JsonPlaceHolderApi untuk mengambil data dari rest server
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                //ini adalah pengkondisian jika ada respon namun responya bukan 200 (sukses)
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                //dan jika server merespon, maka data yang didapat akan dimasukkan ke dalam array list siswa
                List<Post> posts = response.body();
                for(Post post:posts) {
                    //data ditambahkan dengan perulangan dan dimasukkan ke instansiasi konstruktor
                    siswa.add(new Post(post.getNIm(), post.getNama(), post.getAlamat(), post.getJenis_kelamin(), post.getNo_telp()));
                }
                //terakhir data pada array list siswa ditambahkan ke adapter untuk ditampilkan dengan recyclerview
                itemAdapter.add(siswa);
                siswaView.setAdapter(fastAdapter);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                siswaView.setLayoutManager(layoutManager);
            }

            //ini kode jika koneksi ke rest server gagal
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });


    }

    //kode untuk berpindah ke halaman tambah data siswa
    public void myOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(intent);
    }
}
