package com.example.weather1.services;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherServices {

    @GET("public-api/gfs/json?_ll=48.85341,2.3488&_auth=VU8EE1IsBCZUeVFmBHIGLwVtVWAOeAYhAn4HZAtuVShROgBhUjJcOgdpUy5QfwcxBSgPbFxnADBTOFYuXy1TMlU%2FBGhSOQRjVDtRNAQrBi0FK1U0Di4GIQJnB2ILeFU0UTEAelI5XDoHdlMwUGEHNwUpD3BcYgA9UzZWNV82UzhVNwRkUjEEYVQkUSwEMQZmBTJVNQ4wBjkCZgc2C2ZVMFFnADVSOFw7B3ZTOFBhBzQFMg9vXGYAO1MwVi5fLVNJVUUEfVJxBCRUblF1BCkGZwVoVWE%3D&_c=88bf0afd6ef9730869e30eced820d420")
    Call<Object> getWeather();
}
