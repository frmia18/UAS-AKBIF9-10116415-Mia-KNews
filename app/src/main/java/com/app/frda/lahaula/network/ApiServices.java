package com.app.frda.lahaula.network;
import com.app.frda.lahaula.response.ResponseBerita;

import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiServices {
    //@TIPEMETHOD("API_END_POINT")
    @GET("tampil_berita.php")
    Call<ResponseBerita> request_show_all_berita();
}
