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

        val Res1 = listOf("Белковые Кексы", "Мягкая и влажная выпечка", "20 г мука ржаная цельнозерновая\n" +
                "20 г сывороточный протеин двойной шоколад\n" +
                "50 г хлопья рисовые\n" +
                "50 г чёрная смородина\n" +
                "60 г белок яичный", "Смешать все ингредиенты, добавляя смородину в последнюю очередь.\n" +
                "Вылить в слегка смазанные формы для маффинов.\n" +
                "Выпекать в предварительно разогретой до 180°С духовке 25 минут.", 60, 50 )
        val Res2 = listOf("Окрошка", "Холодный суп, от которого вы почувствуете себя сытым.\n", "200 г майонез\n" +
                "350 г картошка вареная\n" +
                "405 г колбаса докторская\n" +
                "413 мл сметана 10%\n" +
                "1,385 литры вода газированная\n" +
                "460 г вареное яйцо\n" +
                "313 г укроп\n" +
                "425 г огурец (с кожурой)\n" +
                "458 г редис", "Нарежьте яйца, колбасу и овощи.\n" +
                "Смешайте воду, сметану и майонез.\n" +
                "Добавьте нарезанные ингредиенты и перемешайте.", 370, 30)
        val Res3 = listOf("Яичная Лапша С Креветками\n", "Просто и легко готовить.\n", "3 г растительное масло\n" +
                "10 мл соевый соус\n" +
                "40 г соус терияки\n" +
                "75 г лапша яичная\n" +
                "90 г креветки (вареные или на пару)\n" +
                "25 г морковь\n" +
                "30 г цукини\n" +
                "77 г сладкий красный перец", "Отварить лапшу.\n" +
                "Нарезать овощи соломкой.\n" +
                "Обжарить овощи на сковороде с маслом.\n" +
                "Добавить лапшу и креветки к овощам.\n" +
                "Добавить соус и варить на медленном огне еще 5 минут.", 172, 45)
        val Res4 = listOf("Картофельная Запеканка С Грибами И Фаршем\n", "Вкусный и очень сытный.\n", "80 г масло сливочное\n" +
                "200 мл молоко 2,5%\n" +
                "500 г куриный фарш\n" +
                "1 кг картофель\n" +
                "500 г шампиньоны\n" +
                "200 г сыр голландский\n" +
                "150 г лук", "Отварить картофель и сделать пюре. Добавьте молоко и масло.\n" +
                "Отдельно обжарить лук с грибами и отдельно обжарить фарш. Приправить по вкусу.\n" +
                "В форму выкладываем слоями пюре, фарш, грибы с луком. Сверху посыпьте сыром.\n" +
                "Выпекать при 180°С 15 минут.", 476, 60)
        val Res5 = listOf("Шоколадные Венские Вафли\n", "Простой, вкусный и легкий рецепт завтрака.\n", "7 г разрыхлитель теста\n" +
                "14 г масло подсолнечное с добавлением оливкового масла и масла зародышей пшеницы\n" +
                "100 мл молоко 3,2%\n" +
                "150 г рисовая мука\n" +
                "25 г какао\n" +
                "1 большой яйцо (целое)\n" +
                "100 г сахарный песок", "Смешайте все сухие ингредиенты.\n" +
                "Добавьте яйцо и молоко. Взбейте миксером сначала на низкой скорости, затем на средней скорости. Тесто должно быть консистенции 20% сметаны.\n" +
                "Добавьте масло и перемешайте.\n" +
                "В разогретую вафельницу выливаем 1,5 столовые ложки теста. Варить 5-7 минут.", 104, 55)
        val Res6 = listOf("Сырная Лепёшка\n", "Легкая, вкусная и низкоуглеводная закуска.\n", "15 г топлёное масло\n" +
                "1 средний яйцо\n" +
                "180 г сыр сулугуни\n" +
                "180 г творог 9%", "Натереть сыр.\n" +
                "Смешать все ингредиенты вместе, кроме сливочного масла.\n" +
                "Выложить массу в виде круглой лепешки и смазать верх растопленным сливочным маслом.\n" +
                "Выпекать в духовке при температуре 180°C в течение 20 минут или до золотистого цвета.\n" +
                "Разрезать торт на 6 частей.", 163, 30)
        val Res7 = listOf("Гречка Тушеная С Овощами\n", "Простое, но вкусное блюдо.\n", "200 г крупа гречневая ядрица\n" +
                "314 г консервы свинина тушеная\n" +
                "30 г морковь\n" +
                "113 г лук\n" +
                "383 г кабачок", "Лук и морковь обжарить на слегка смазанной маслом сковороде 3-5 минут.\n" +
                "Добавьте кабачки и перемешайте.\n" +
                "Добавьте гречку, свинина тушеная и немного воды.\n" +
                "Варить 35 минут.", 154, 70)
        val Res8 = listOf("Удон С Свиной Вырезкой И Овощами По Конадски\n", "Вкусный и простой рецепт обеда.\n", "1 порция рубленый чеснок\n" +
                "10 мл соевый соус\n" +
                "250 г канадская смесь\n" +
                "100 г вырезка свиная\n" +
                "100 г лапша удон (вареная)", "На сковороде обжарить овощи со свиной вырезкой без добавления масла.\n" +
                "Добавьте лапшу и обжарьте. В конце добавить соевый соус и чеснок.\n" +
                "По желанию можно украсить сверху свежим огурцом при подаче на стол.", 389, 30)
        val Res9 = listOf("Салат Греческий\n", "Идеально подходит для обеда или ужина.\n", "13 мл оливковое масло\n" +
                "28 г оливки черные без косточек\n" +
                "66 г фета\n" +
                "100 г помидоры\n" +
                "150 г огурец (с кожурой)\n" +
                "124 г болгарский перец", "Нарезать фету и овощи.\n" +
                "Смешайте все в миске.\n" +
                "Приправить по вкусу.", 332, 15)
        val Res10 = listOf("Блины Из Рисовой Муки\n", "Быстрый, вкусный и безглютеновый рецепт.\n", "6 г крахмал картофельный\n" +
                "71 г рисовая мука\n" +
                "100 мл молоко 2,5%\n" +
                "108 г яйцо", "Смешайте все ингредиенты вместе.\n" +
                "Подготовить разогретую сковороду.\n" +
                "Вылить достаточное количество смеси и жарить до золотистого цвета с обеих сторон.", 96, 30)
        val Res11 = listOf("Бананово-Овсяные Оладьи\n", "Натурально подслащенный, вкусный и простой в приготовлении.\n", "20 г мука рисовая\n" +
                "30 мл молоко 2,5%\n" +
                "150 г банан\n" +
                "45 г хлопья овсяные геркулес\n" +
                "1 средний яйцо", "Размять банан.\n" +
                "Добавить взбитое яйцо и остальные ингредиенты.\n" +
                "Выпекать на сковороде с антипригарным покрытием с обеих сторон до золотистого цвета.", 433, 30)
        val Res12 = listOf("Котлеты Куриные\n", "Легкий, вкусный и простой рецепт.\n", "1 ст л растительное масло\n" +
                "53 г яйцо\n" +
                "500 г куриная грудка\n" +
                "15 г укроп\n" +
                "85 г лук\n" +
                "26 г петрушка\n" +
                "180 г кабачок", "Все ингредиенты, кроме яиц, измельчить на мясорубке.\n" +
                "Выложите его в миску, добавьте яйцо и желаемые специи по вкусу.\n" +
                "Сформировать котлеты и выложить их на смазанный маслом противень. Выпекать при 180°С 25 минут.", 140, 40)
        val Res13 = listOf("Треугольники Лаваша С Творогом\n", "Отличный рецепт для перекуса.\n", "50 г яйцо\n" +
                "65 г сыр российский 50%\n" +
                "1 небольшая щепотка соль\n" +
                "10 г укроп\n" +
                "200 г лаваш тонкий\n" +
                "253 г творог 9%\n" +
                "1 долька чеснок", "Лаваш нарезать полосками около 10 см на 8 частей.\n" +
                "Приготовить начинку: смешать творог, яйцо, соль, мелко нарезанный укроп, чеснок и тертый сыр и все хорошо перемешать в однородную массу.\n" +
                "Выкладываем на полоску столовую ложку начинки и складываем треугольниками.\n" +
                "Обжарить на сухой сковороде, гриле или обычном электрогриле до румяной корочки.", 296, 20)
        val Res14 = listOf("Сырники", "Кремовый, но не такой сладкий.\n", "2 г ванилин\n" +
                "5 г заменитель сахара\n" +
                "10 г разрыхлитель теста\n" +
                "60 г рисовая мука\n" +
                "1 средний яйцо\n" +
                "10 г кокосовое масло\n" +
                "600 г творог 9%", "Яйцо взбить с творогом и сахарозаменителем.\n" +
                "Добавить муку, разрыхлитель и ванилин.\n" +
                "Обжарить на сковороде с кокосовым маслом под крышкой до золотистого цвета.", 334, 30)
        val Res15 = listOf("Куриный Салат\n", "Здоровое блюдо на обед.\n", "10 г горчица русская\n" +
                "100 г авокадо\n" +
                "120 г йогурт греческий 2,5%\n" +
                "2 большой сваренное вкрутую яйца\n" +
                "1 небольшая щепотка соль\n" +
                "180 г куриная грудка отварная\n" +
                "200 г огурец (с кожурой)\n" +
                "50 г зеленый листовой салат", "Нарежьте курицу, яйца и овощи.\n" +
                "Смешать все ингридиенты в одной миске.\n" +
                "Приправить по вкусу.", 112, 20)
        val Res16 = listOf("Чизкейк Из Рисовой Муки\n", "Рецепт вкусного и безглютенового завтрака.\n", "1 г ванилин\n" +
                "3 г заменитель сахара\n" +
                "25 г мука рисовая\n" +
                "1 средний яйцо\n" +
                "250 г творог 5%", "Смешать все ингредиенты.\n" +
                "Сформировать сырники.\n" +
                "Обжарить с двух сторон на антипригарной сковороде с закрытой крышкой до готовности.", 229, 25)
        val Res17 = listOf("Салат С Креветками\n", "Низкокалорийное блюдо.\n", "2 ст л растительное масло\n" +
                "50 г креветки\n" +
                "70 г салат месклам\n" +
                "1 небольшая щепотка соль\n" +
                "200 г огурец (с кожурой)\n" +
                "150 г помидоры черри", "Бланшируйте креветки в кипящей воде в течение 5 минут.\n" +
                "Нарезать помидоры и огурцы.\n" +
                "Смешайте все в миске.", 88, 20)
        val Res18 = listOf("Булгурский Плов С Курицей\n", "Сытное блюдо на обед или ужин.\n", "1 ч.л. подсолнечное масло\n" +
                "512 г филе бедра куриное\n" +
                "111 г морковь\n" +
                "125 г лук\n" +
                "400 г булгур", "Нарезанные лук и морковь обжарить на сковороде с маслом 3-5 минут.\n" +
                "Добавьте курицу, приправьте и готовьте 5 минут.\n" +
                "Положите в мультиварку булгур, курицу и овощи, затем влейте 500 мл воды.\n" +
                "Варить до готовности и мягкости.", 406, 60)
        val Res19 = listOf("Печенье Творожное\n", "Легкий, простой и вкусный рецепт.\n", "2 средний яйца\n" +
                "100 г сахар\n" +
                "300 г пшеничная мука\n" +
                "1 небольшая щепотка соль\n" +
                "5 г пищевая сода\n" +
                "200 г творог 9%", "Смешать 1 яйцо, сахар, творог и соль и взбить миксером.\n" +
                "Добавить муку и соду и взбить.\n" +
                "Раскатать тесто до 1 см и разрезать на 8 частей.\n" +
                "Разложите их на противне и смажьте яйцом. Выпекать при 180°С 25 минут.", 233, 40)
        val Res20 = listOf("Овощное Рагу\n", "Здоровое блюдо без использования масла.\n", "124 г болгарский перец\n" +
                "143 г помидоры\n" +
                "186 г баклажан\n" +
                "163 г картофель\n" +
                "14 г укроп\n" +
                "38 г лук\n" +
                "30 г зеленый лук\n" +
                "362 г кабачок", "Нарезать все ингредиенты.\n" +
                "Тушить в толстостенной кастрюле или сковороде.\n" +
                "Приправить по вкусу. Варить до мягкости, но не переваривать.", 74, 50)
        val Res21 = listOf("")
        val Res22 = listOf("")
        val Res23 = listOf("")
        val Res24 = listOf("")
        val Res25 = listOf("")
        val Res26 = listOf("")
        val Res27 = listOf("")
        val Res28 = listOf("")
        val Res29 = listOf("")
        val Res30 = listOf("")


         val ID = "_id"
         val Recipe_NAme = "recipe_name"
         val Description = "description"
         val Preparation= "preparation"
         val Recipe_Items = "items"
         val Calories = "calories"
         val Cook_time = "cook_time"
         val Image_parh = "image_path"


         val Item_name = "item_name"
         val Item_Dose = "item_dose"


         val DB_NAME = "FoodIepal.db"
         val DB_VERSION = 29

        val CREATE_TABLE = "CREATE TABLE $Table_Name ($_ID INTEGER PRIMARY KEY AUTOINCREMENT, $Login TEXT, $Password TEXT);"
        val CREATE_TABLE_FOOD = "CREATE TABLE $Table_Name_Food ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $Recipe_NAme TEXT, $Description TEXT, $Preparation TEXT, $Recipe_Items TEXT, $Calories INTEGET, $Cook_time INTEGER, $Image_parh INTEGER);"
        val CREATE_TABLE_FAVORITE = "CREATE TABLE $Table_Name_Favorite ($ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $Recipe_NAme TEXT, $Description TEXT, $Preparation TEXT, $Recipe_Items TEXT, $Calories TEXT, $Cook_time TEXT, $Image_parh INTEGER, $Login TEXT, FOREIGN KEY ($Login) REFERENCES $Table_Name ($Login));"
        val CREATE_TABLE_BASKET = "CREATE TABLE $Table_Name_Basket ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $Item_name TEXT, $Item_Dose TEXT, $Login TEXT, FOREIGN KEY($Login) REFERENCES $Table_Name ($Login));"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
        db.execSQL(CREATE_TABLE_FOOD)
        db.execSQL(CREATE_TABLE_FAVORITE)
        db.execSQL(CREATE_TABLE_BASKET)

        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res1[0]}', '${Res1[1]}', '${Res1[2]}', '${Res1[3]}', ${Res1[4]}, ${Res1[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res2[0]}', '${Res2[1]}', '${Res2[2]}', '${Res2[3]}', ${Res2[4]}, ${Res2[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res3[0]}', '${Res3[1]}', '${Res3[2]}', '${Res3[3]}', ${Res3[4]}, ${Res3[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res4[0]}', '${Res4[1]}', '${Res4[2]}', '${Res4[3]}', ${Res4[4]}, ${Res4[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res5[0]}', '${Res5[1]}', '${Res5[2]}', '${Res5[3]}', ${Res5[4]}, ${Res5[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res6[0]}', '${Res6[1]}', '${Res6[2]}', '${Res6[3]}', ${Res6[4]}, ${Res6[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res7[0]}', '${Res7[1]}', '${Res7[2]}', '${Res7[3]}', ${Res7[4]}, ${Res7[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res8[0]}', '${Res8[1]}', '${Res8[2]}', '${Res8[3]}', ${Res8[4]}, ${Res8[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res9[0]}', '${Res9[1]}', '${Res9[2]}', '${Res9[3]}', ${Res9[4]}, ${Res9[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res10[0]}', '${Res10[1]}', '${Res10[2]}', '${Res10[3]}', ${Res10[4]}, ${Res10[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res11[0]}', '${Res11[1]}', '${Res11[2]}', '${Res11[3]}', ${Res11[4]}, ${Res11[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res12[0]}', '${Res12[1]}', '${Res12[2]}', '${Res12[3]}', ${Res12[4]}, ${Res12[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res13[0]}', '${Res13[1]}', '${Res13[2]}', '${Res13[3]}', ${Res13[4]}, ${Res13[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res14[0]}', '${Res14[1]}', '${Res14[2]}', '${Res14[3]}', ${Res14[4]}, ${Res14[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res15[0]}', '${Res15[1]}', '${Res15[2]}', '${Res15[3]}', ${Res15[4]}, ${Res15[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res16[0]}', '${Res16[1]}', '${Res16[2]}', '${Res16[3]}', ${Res16[4]}, ${Res16[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res17[0]}', '${Res17[1]}', '${Res17[2]}', '${Res17[3]}', ${Res17[4]}, ${Res17[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res18[0]}', '${Res18[1]}', '${Res18[2]}', '${Res18[3]}', ${Res18[4]}, ${Res18[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res19[0]}', '${Res19[1]}', '${Res19[2]}', '${Res19[3]}', ${Res19[4]}, ${Res19[5]})")
        db.execSQL("INSERT INTO $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Preparation, $Calories, $Cook_time) VALUES('${Res20[0]}', '${Res20[1]}', '${Res20[2]}', '${Res20[3]}', ${Res20[4]}, ${Res20[5]})")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $Table_Name")
        db.execSQL("DROP TABLE IF EXISTS $Table_Name_Food")
        db.execSQL("DROP TABLE IF EXISTS $Table_Name_Favorite")
        db.execSQL("DROP TABLE IF EXISTS $Table_Name_Basket")
        onCreate(db)
    }
}