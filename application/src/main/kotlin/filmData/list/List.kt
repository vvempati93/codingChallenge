package filmData.list

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import jdk.nashorn.internal.objects.NativeArray.forEach
import java.util.*
import kotlin.collections.List

/**
 * Created by vvempati on 3/21/2017.
 */
data class filmObject(var id: Int,val title:String, val description:String,
                      val url_slug:String, val year:Int, val related_film_ids:Any){
    var averageRating:Int ? = null
}
data class ratings(var id: Int, val description: String)
var allFilms:List<filmObject> = mutableListOf()
var ratingsStore:MutableList<ratings> = mutableListOf()
val jsonFilms = """
{
  "films": [
    {
      "id": 1,
      "title": "A Wonderful Film",
      "description": "A cute film about lots of wonderful stuff.",
      "url_slug": "a_wonderful_film",
      "year": 1973,
      "related_film_ids": [2, 4, 7]
    },
    {
      "id": 2,
      "title": "All About Fandor",
      "description": "Documentary telling the tail of Fandor.",
      "url_slug": "all_about_fandor",
      "year": 2001,
      "related_film_ids": [1, 4, 7]
    },
    {
      "id": 3,
      "title": "Dancing with Elephants",
      "description": "The classic tale of Dancing with Wolves, but with elephants!",
      "url_slug": "dancing_with_elephants",
      "year": 2016,
      "related_film_ids": []
    },
    {
      "id": 4,
      "title": "The Story of George",
      "description": "A true story outlining the adventures of George",
      "url_slug": "the_story_of_george",
      "year": 1974,
      "related_film_ids": [1, 2, 7]
    },
    {
      "id": 5,
      "title": "Zee and Bee Go Skiiing",
      "description": "The tragic story of Zee and Bee on their snowy adventures.",
      "url_slug": "zee_and_bee_go_skiing",
      "year": 1991,
      "related_film_ids": [6, 8, 9, 10]
    },
    {
      "id": 6,
      "title": "Ahead of Its Time",
      "description": "A story about the future.",
      "url_slug": "ahead_of_its_time",
      "year": 2088,
      "related_film_ids": [8, 9, 10]
    },
    {
      "id": 7,
      "title": "My Name is Jerry",
      "description": "The life of the cat named Jerry.",
      "url_slug": "my_name_is_jerry",
      "year": 1989,
      "related_film_ids": [1, 2, 4]
    },
    {
      "id": 8,
      "title": "Gems and Trestle",
      "description": "Is it about web development? Mining history? You'll never know.",
      "url_slug": "gems_and_trestle",
      "year": 2011,
      "related_film_ids": [9, 10]
    },
    {
      "id": 9,
      "title": "How To React Natively",
      "description": "A step by step journey through developing a React Native application.",
      "url_slug": "how_to_react_natively",
      "year": 2000,
      "related_film_ids": [10]
    },
    {
      "id": 10,
      "title": "Perry Hotter: A Lizard's Tale",
      "description": "It's a story all about a lizard named Perry.",
      "url_slug": "perry_hotter_a_lizards_tale",
      "year": 2017,
      "related_film_ids": []
    }
  ]
}
    """
fun transformJSONObjectToPOJO( json: String ):List<filmObject>
{
    val jsonObject:JsonObject = JsonObject(json)
    val JSONArray:JsonArray = jsonObject.getJsonArray("films")
    var listOfFilmObjects:List<filmObject> = emptyList()
    for(i in 0..(JSONArray.size() -1))
    {
        val film:JsonObject = JSONArray.getJsonObject(i)
        val ids = film.getValue("related_film_ids")
        val filmFromList:filmObject = filmObject(film.getInteger("id"),film.getString("title"),film.getString("description"),film.getString("url_slug"),
                film.getInteger("year"),ids)
        with(filmFromList)
        {
            this.averageRating = film.getInteger("averageRating")
        }
        listOfFilmObjects += filmFromList
    }
    allFilms = listOfFilmObjects
    return listOfFilmObjects
}
fun view( filmID: String):filmObject{
    //val film:Int = 3
    val films = transformJSONObjectToPOJO(jsonFilms)
    val emptyFilm:filmObject = filmObject(1,"empty","nothing","none",1,8)
    for(film in films)
    {
        if(film.id == filmID.toInt())
        {
            return film
        }
    }
    return emptyFilm
    //return film // iterate through listOfFilmObjects and get the correct film
}
fun rate(rating:String, id:String):filmObject{
    //val filmID = rating.getInteger("id")
    //val review = rating.getString("rating")
    //val rating:ratings = ratings(filmID,review)
    val films = transformJSONObjectToPOJO(jsonFilms)
    val emptyFilm:filmObject = filmObject(1,"empty","nothing","none",1,8)
    for(film in films)
    {
        if(film.id == id.toInt())
        {
            //print("this is the id: ${film.id}")
            if(film.averageRating != null)
            {
                val intialRating = film.averageRating
                film.averageRating =  intialRating?.plus(rating.toInt())
            }
            else
            {
                film.averageRating = rating.toInt()
            }
           return film

        }
    }

    return emptyFilm
}