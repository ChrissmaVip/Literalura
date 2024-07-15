package com.alura.literalura.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Libro")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long    id;
    @Column (unique = true)
    private String titulo;
    @OneToMany(mappedBy = "libroAutores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autor;
    @OneToMany(mappedBy = "variosIdiomas", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Idioma> idioma;
    private int numeroDeDescarga;


    public Libro(DtoBusquedaLibro dtoBusquedaLibro) {
        if (dtoBusquedaLibro != null && dtoBusquedaLibro.results() != null && !dtoBusquedaLibro.results().isEmpty()) {
            DtoLibro dtoLibro = dtoBusquedaLibro.results().get(0);
            this.titulo = dtoLibro.titulo();
            this.numeroDeDescarga = dtoLibro.numeroDeDescarga();
            this.autor = dtoLibro.dtoAutor().stream().
                    map(dtoAutor -> new Autor(
                    0,
                    dtoAutor.nombre(),
                    dtoAutor.fechaDeNacimiento(),
                    dtoAutor.fechDeFallecimiento(),
                    this )).collect(Collectors.toList());
            this.idioma = dtoLibro.idioma().stream().
                    map(idioma -> new Idioma(0, idioma, this )).collect(Collectors.toList());
        }
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idioma=" + idioma +
                ", numeroDeDescarga=" + numeroDeDescarga  ;
    }
}
