import com.example.restaurant.model.dataClasses.DessertClass
import com.example.restaurant.model.dataClasses.DrinkClass
import com.example.restaurant.model.dataClasses.FastFoodClass
import com.example.restaurant.model.dataClasses.FoodClass
import com.google.gson.JsonObject

const val BASE_URL = "http://192.168.198.62:8080/"


fun fastFoodToJsonObject(fastFoodClass: FastFoodClass) : JsonObject {

    val jsonObject = JsonObject()
    jsonObject.addProperty("id" , fastFoodClass.id)
    jsonObject.addProperty("name" , fastFoodClass.name)
    jsonObject.addProperty("price" , fastFoodClass.price)
    jsonObject.addProperty("description" , fastFoodClass.description)
    jsonObject.addProperty("time" , fastFoodClass.time)
    jsonObject.addProperty("kcal" , fastFoodClass.kcal)
    jsonObject.addProperty("imageUrl" , fastFoodClass.imageUrl)

    return jsonObject
}

fun dessertToJsonObject(dessertClass: DessertClass) : JsonObject {

    val jsonObject = JsonObject()
    jsonObject.addProperty("id" , dessertClass.id)
    jsonObject.addProperty("name" , dessertClass.name)
    jsonObject.addProperty("price" , dessertClass.price)
    jsonObject.addProperty("description" , dessertClass.description)
    jsonObject.addProperty("imageUrl" , dessertClass.imageUrl)

    return jsonObject
}

fun drinkToJsonObject(drinkClass: DrinkClass) : JsonObject {

    val jsonObject = JsonObject()
    jsonObject.addProperty("id" , drinkClass.id)
    jsonObject.addProperty("name" , drinkClass.name)
    jsonObject.addProperty("price" , drinkClass.price)
    jsonObject.addProperty("description" , drinkClass.description)
    jsonObject.addProperty("imageUrl" , drinkClass.imageUrl)

    return jsonObject
}

fun foodToJsonObject(foodClass: FoodClass) : JsonObject {

    val jsonObject = JsonObject()
    jsonObject.addProperty("id" , foodClass.id)
    jsonObject.addProperty("name" , foodClass.name)
    jsonObject.addProperty("price" , foodClass.price)
    jsonObject.addProperty("description" , foodClass.description)
    jsonObject.addProperty("time" , foodClass.time)
    jsonObject.addProperty("imageUrl" , foodClass.imageUrl)

    return jsonObject
}
