package com.luiyi.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "movies") // anotar esta clase como una coleccion
@Data // implementa todos los geter y setters para las propiedades de la clase
@AllArgsConstructor // agrega un constructor que toma todos los parametros definidos en la clase como argumentos
@NoArgsConstructor // agrega un constructor que no toma ningun parametro
public class Movie {
    @Id
    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;
    // crea una relacion entre las collecciones en mongodb
    @DocumentReference // esto hara que la base de datos almacene solo los IDs de la review y los review van a ser una collection separada
    private List<Review> reviewIds;
}
