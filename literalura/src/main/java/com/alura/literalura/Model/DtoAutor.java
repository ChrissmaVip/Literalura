package com.alura.literalura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DtoAutor(

        @JsonAlias("name")
        String nombre,

        @JsonAlias("birth_year")
        int fechaDeNacimiento,

         @JsonAlias("death_year")
        int fechDeFallecimiento

) {
        @Override
        public String toString() {
                return
                         nombre + '\'' +
                        ", FechaDeNacimiento :" + fechaDeNacimiento +
                        ", FechDeFallecimiento :" + fechDeFallecimiento +
                        ", ListaDeLibros :"
                        ;
        }
}
