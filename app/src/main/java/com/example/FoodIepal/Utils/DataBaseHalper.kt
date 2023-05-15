package com.example.FoodIepal.Utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHalper (Context : Context) : SQLiteOpenHelper(Context, DB_NAME, null, DB_VERSION) {

    companion object {
         val Table_Name = "Person"
         val Table_Name_Food = "Recipes"
         val Table_Name_Favorite = "Favorite"
         val Table_Name_Basket = "Basket"

         val _ID = "_id"
         val Login = "Login"
         val Password = "Password"

        val Res1 = listOf("Быстрый Багет", "Смешайте все ингредиенты и дайте тесту подняться в течение 1 часа.\n" +
                "Выложите тесто на посыпанный мукой стол, растяните и разделите на две части.\n" +
                "Сформируйте багет, затем положите его на противень. Дайте подняться в течение 20 минут.\n" +
                "Разогреть духовку до 250°С, поставить на дно емкость с водой, выпекать 20 минут, а за 5 минут до окончания удалить воду из духовки.", "1/2 ч.л. соль\n" +
                "500 мл вода\n" +
                "2 г дрожжи быстродействующие\n" +
                "370 г мука пшеничная хлебопекарная высший сорт", 633, 40)
        val Res2 = listOf("Вафли С Рикоттой И Творогом", "Смешайте казеин и муку.\n" +
                "Добавить яйца, затем творог и рикотту.\n" +
                "Добавьте сироп и перемешайте.\n" +
                "Готовьте в вафельнице в течение 5 минут или до золотистого цвета.", "100 г яйцо\n" +
                "250 г сыр рикотта\n" +
                "40 г пшеничная мука\n" +
                "250 г творог 5%\n" +
                "5 мл сироп банан\n" +
                "40 г казеин", 101, 25)
        val Res3 = listOf("Овсяно-Банановые Блины", "Размять бананы.\n" +
                "Добавить яйцо, соду и ванилин.\n" +
                "Всыпать овсяную муку.\n" +
                "Обжарить на сковороде на среднем огне до золотистого цвета.", "1 средний яйцо\n" +
                "2 маленький бананы\n" +
                "1/2 ч.л. пищевая сода\n" +
                "2 г ванилин\n" +
                "100 г мука овсяная", 310, 45)
        val Res4 = listOf("Макароны По Флотски", "Фарш, лук, морковь потушить без масла. Сварить макароны. Смешать приготовленное.", "330 г говядина\n" +
                "1 средний яйцо\n" +
                "115 г морковь\n" +
                "150 г лук\n" +
                "250 г спагетти", 397, 70)

         val ID = "_id"
         val Recipe_NAme = "recipe_name"
         val Description = "description"
         val Recipe_Items = "items"
         val Calories = "calories"
         val Cook_time = "cook_time"
         val Image_parh = "image_path"
         val Recipe_Dose = "recipe_dose"


         val DB_NAME = "FoodIepal.db"
         val DB_VERSION = 19

        val CREATE_TABLE = "CREATE TABLE $Table_Name ($_ID INTEGER PRIMARY KEY AUTOINCREMENT, $Login TEXT, $Password TEXT);"
        val CREATE_TABLE_FOOD = "CREATE TABLE $Table_Name_Food ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $Recipe_NAme TEXT, $Description TEXT, $Recipe_Items TEXT, $Calories TEXT, $Cook_time TEXT, $Image_parh INTEGER);"
        val CREATE_TABLE_FAVORITE = "CREATE TABLE $Table_Name_Favorite ($ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $Recipe_NAme TEXT, $Description TEXT, $Recipe_Items TEXT, $Calories TEXT, $Cook_time TEXT, $Image_parh INTEGER, $Login TEXT, FOREIGN KEY ($Login) REFERENCES $Table_Name ($Login));"
        val CREATE_TABLE_BASKET = "CREATE TABLE $Table_Name_Basket ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $Recipe_NAme TEXT, $Recipe_Dose TEXT);"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
        db.execSQL(CREATE_TABLE_FOOD)
        db.execSQL(CREATE_TABLE_FAVORITE)
        db.execSQL(CREATE_TABLE_BASKET)

        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Calories, $Cook_time, $Image_parh) VALUES('${Res2[0]}', '${Res2[1]}', '${Res2[2]}', ${Res2[3]}, ${Res2[4]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Calories, $Cook_time, $Image_parh) VALUES('${Res3[0]}', '${Res3[1]}', '${Res3[2]}', ${Res3[3]}, ${Res3[4]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Calories, $Cook_time, $Image_parh) VALUES('${Res4[0]}', '${Res4[1]}', '${Res4[2]}', ${Res4[3]}, ${Res4[4]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Calories, $Cook_time, $Image_parh) VALUES('${Res1[0]}', '${Res1[1]}', '${Res1[2]}', ${Res1[3]}, ${Res1[4]})")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $Table_Name")
        db.execSQL("DROP TABLE IF EXISTS $Table_Name_Food")
        db.execSQL("DROP TABLE IF EXISTS $Table_Name_Favorite")
        db.execSQL("DROP TABLE IF EXISTS $Table_Name_Basket")
        onCreate(db)
    }
}