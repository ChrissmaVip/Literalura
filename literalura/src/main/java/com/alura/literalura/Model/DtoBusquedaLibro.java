package com.alura.literalura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DtoBusquedaLibro(

      @JsonAlias("results")
      List<DtoLibro> results
) {

   @Override
   public String toString() {
      return "Busqueda: " + '\n'+
               results + "\n" ;
   }



}
