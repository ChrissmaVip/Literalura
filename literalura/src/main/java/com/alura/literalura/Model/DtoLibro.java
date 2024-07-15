package com.alura.literalura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DtoLibro(

    @JsonAlias("title")
    String titulo,

    @JsonAlias("authors")
    List<DtoAutor> dtoAutor,
    @JsonAlias("languages")
    List<String> idioma,
    @JsonAlias("download_count")
    int numeroDeDescarga


) {

    @Override
    public String toString() {
        return
                "titulo :" + titulo + '\n' +
                " autor :" + dtoAutor + '\n' +
                " idioma :" + idioma +'\n'+
                " numeroDeDescarga :" + numeroDeDescarga +
                '\n'+'\n';
    }
}
