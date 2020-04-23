package id.ac.polinema.myretrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    //disini terdapat @GET("mahasiswa") artinya kita mengambil data mahasiswa
    @GET("mahasiswa")
    Call<List<Post>> getPost();

    //terdapat tambahan untuk mengambil data mahasiswa berdasarkan nim nya
    @GET("mahasiswa")
    Call<List<Post>> getPostById(
            @Query("nim") String nim
    );

    //lalu disini terdapat @POST("mahasiswa") artinya kita membuat pengiriman data siswa dengan method post
    @POST("mahasiswa") //
    Call<ResponseBody> createPost(@Body Post post);
}
