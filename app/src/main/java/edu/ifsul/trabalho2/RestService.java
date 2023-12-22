package edu.ifsul.trabalho2;

import edu.ifsul.trabalho2.model.DeputadoResultArray;
import edu.ifsul.trabalho2.model.PartidoResultArray;
import edu.ifsul.trabalho2.model.PerfilDeputadoResult;
import edu.ifsul.trabalho2.model.PerfilPartidoResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestService {

    @GET("partidos/")
    Call<PartidoResultArray> getPartidoResultArray();

    @GET("deputados/")
    Call<DeputadoResultArray> getDeputadoResultArray();

    @GET("deputados/{id}/")
    Call<PerfilDeputadoResult> getPerfilDeputadoResult(@Path("{id}") Long id);

    @GET("partidos/{id}/")
    Call<PerfilPartidoResult> getPerfilPartidoResult(@Path("{id}") Long id);

}
