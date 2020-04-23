package id.ac.polinema.myretrofit;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

//dilakukan extends dari class Abstract Item untuk pemodelan data dan view holder
public class Post extends AbstractItem<Post, Post.ViewHolder> {
    private String nim;
    private String nama;
    private String alamat;
    private String jenis_kelamin;
    private String no_telp;

    //ditambahkan konstruktor untuk pengiriman data
    public Post(String nim, String nama, String alamat, String jenis_kelamin, String no_telp) {
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.no_telp = no_telp;
    }

    public String getNIm() { return nim; }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getNo_telp() {
        return no_telp;
    }

    //3 method dibawah merupakan method implementasi dari class Abstract Item,
    //referensi nya dari modul fast adapter di polinema-mobile.github.io
    @NonNull
    @Override
    public Post.ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.rv_siswa;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_siswa;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<Post> {
        TextView TextId, TextNama, TextAlamat, TextJk, TextNo;

        public ViewHolder(View itemView) {
            super(itemView);
            //di bawah ini merupakan proses binding atribut
            TextId = itemView.findViewById(R.id.txt_id);
            TextNama = itemView.findViewById(R.id.txt_nama);
            TextAlamat = itemView.findViewById(R.id.txt_alamat);
            TextJk = itemView.findViewById(R.id.txt_jk);
            TextNo = itemView.findViewById(R.id.txt_no_telp);
        }

        //kode dibawah ini fungsinya untuk melakukan bind model ke layout
        @Override
        public void bindView(Post item, List<Object> payloads) {
            TextId.setText(item.nim);
            TextNama.setText(item.nama);
            TextAlamat.setText(item.alamat);
            TextJk.setText(item.jenis_kelamin);
            TextNo.setText(item.no_telp);
        }

        @Override
        public void unbindView(Post item) {
            TextId.setText(null);
            TextNama.setText(null);
            TextAlamat.setText(null);
            TextJk.setText(null);
            TextNo.setText(null);
        }
    }
}
