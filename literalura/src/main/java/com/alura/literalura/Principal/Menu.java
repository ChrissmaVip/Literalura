package com.alura.literalura.Principal;
import com.alura.literalura.Model.DtoBusquedaLibro;
import com.alura.literalura.Model.Libro;
import com.alura.literalura.Repository.LibroRepository;
import com.alura.literalura.Service.ConsultaApi;
import com.alura.literalura.Service.ConvierteDatos;



import java.util.List;
import java.util.Scanner;

public class Menu {

    private LibroRepository libroRepositorio;
    Scanner lectura = new Scanner(System.in);

    public Menu(LibroRepository libroRepository) {
        this.libroRepositorio = libroRepository;

    }


    public void menu() {

        int opcion = -1;

        while (opcion != 0) {

            System.out.println("""
                     Elija una opción a través de sus números
                     
                     1) buscar libro por título
                     2) listar libros registrados
                     3) listar autores registrados
                     4) listar autores vivos en un determinado año
                     5) listar libros por idioma
                     0) salir
                    """

            );

            int op = lectura.nextInt();
            lectura.nextLine();

            switch (op) {

                case 1:
                    buscarLibroPorTitulo();
                    break;

                case 2:
                    listarLibrosRegistrados();
                    break;

                case 3:
                    listarAutoresRegistrados();
                    break;

                case 4:
                    listarAutoresVivosEnUnDeterminadoAño();
                    break;

                case 5:
                    listarLibroPorIdioma();
                    break;
                case 0:
                    System.out.println("***Hasta pronto***");
                    opcion = 0;
                    break;

                default:
                    System.out.println("No es una opción válida, intente nuevamente");

            }

        }

    }


    public void buscarLibroPorTitulo() {
        System.out.println("Ingrese el Titulo del Libro");
        String busqueda = lectura.nextLine();
        ConsultaApi consultaApi = new ConsultaApi(busqueda);
        String json = consultaApi.consulta();


        //Verificamos que el Json no sea nulo

        ConvierteDatos convierteDatos = new ConvierteDatos();
        DtoBusquedaLibro dtoBusquedaLibro = convierteDatos.obtenerDatos(json, DtoBusquedaLibro.class);

        // Se guarda libro en la base de datos
        Libro libro1 = new Libro(dtoBusquedaLibro);
        if (libro1.getTitulo() == null) {
            System.out.println("Libro no encontrado");
        } else {
                libroRepositorio.save(libro1);
                System.out.println("Libro guardado con exito" + " \n" + libro1);
            }
        }

          public void listarLibrosRegistrados(){
        List<Libro> listaDeLibrosBuscados = libroRepositorio.findAll();

        System.out.println("LIBROS REGISTRADOS: " );
        for (Libro libro : listaDeLibrosBuscados) {
            System.out.println(libro);
        }
    }

    public void listarAutoresRegistrados(){
          List < Libro> autoresRegistrados = libroRepositorio.findAll();
        System.out.println("AUTORES REGISTRADOS");
        for(Libro libro : autoresRegistrados) {
            System.out.println(libro.getAutor());
        }

    }
    private void listarAutoresVivosEnUnDeterminadoAño() {

        System.out.println("Ingrese el año de autores vivos que desea buscar: ");
        int autoresVivosBuscados = lectura.nextInt();
        lectura.nextLine();
        List < Libro> autoresVivos = libroRepositorio.findAll();
        System.out.println("LIBROS DE AUTORES VIVOS EN: " + autoresVivosBuscados);
        for(Libro libro : autoresVivos){

            if(autoresVivos.isEmpty()) {
                System.out.println("No hay Libros encontrados");
            } else {


                  libro.getAutor().stream()
                          .filter(l -> l.getFechaDeFallecimiento() >= autoresVivosBuscados)
                          .forEach(System.out::println); }

        }

    }
    private void listarLibroPorIdioma() {
        System.out.println("""
                Ingrese el idioma para buscar los libros
                es - español
                en - ingles
                fr - frances
                pt - portugues
                           """ );
        String idiomaElegido = lectura.nextLine();

        switch (idiomaElegido) {

            case "es":
                List < Libro> idiomaLibros = libroRepositorio.findAll();
                System.out.println("Lista de libros en '" + idiomaLibros + "'");
           for (Libro libro : idiomaLibros) {
               libro.getIdioma().stream()
                       .filter(a-> a.getLenguaje().contains("es"))
                       .forEach(System.out::println);
           }

                break;

            case "en":
                List < Libro> idiomaLibros2 = libroRepositorio.findAll();
                System.out.println("Lista de libros en '" + idiomaLibros2 + "'");
                for (Libro libro : idiomaLibros2) {
                    libro.getIdioma().stream()
                            .filter(a-> a.getLenguaje().contains("en"))
                            .forEach(System.out::println);
                }
                break;

            case "fr":
                List < Libro> idiomaLibros3 = libroRepositorio.findAll();
                System.out.println("Lista de libros en '" + idiomaLibros3 + "'");
                for (Libro libro : idiomaLibros3) {
                    libro.getIdioma().stream()
                            .filter(a-> a.getLenguaje().contains("fr"))
                            .forEach(System.out::println);
                }
                break;

            case "pt":
                List < Libro> idiomaLibros4 = libroRepositorio.findAll();
                System.out.println("Lista de libros en '" + idiomaLibros4 + "'");
                for (Libro libro : idiomaLibros4) {
                    libro.getIdioma().stream()
                            .filter(a-> a.getLenguaje().contains("pt"))
                            .forEach(System.out::println);
                }
                break;
            default:
                System.out.println("no es una opción valida");


        }

    }

}
