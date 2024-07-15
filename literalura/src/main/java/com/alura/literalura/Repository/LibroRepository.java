package com.alura.literalura.Repository;
import com.alura.literalura.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;




public interface LibroRepository extends JpaRepository<Libro, Long> {


}

