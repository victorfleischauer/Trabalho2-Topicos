package edu.ifsul.trabalho2;

import edu.ifsul.trabalho2.model.PartidoResultArray;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestService {

    @GET("partidos")
    Call<PartidoResultArray> getPartidosLista();
}
