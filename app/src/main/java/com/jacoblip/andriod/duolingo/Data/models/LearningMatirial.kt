package com.jacoblip.andriod.duolingo.Data.models

import com.jacoblip.andriod.duolingo.Data.models.*

class LearningMatirial {
    companion object{
        val learningMatirial = listOf(

                                Lesson(1,"beginners words",
                                        listOf(
                                                Question("","",5,false,QuestionType.TwinsQ,null, listOf("אבא","אמא","ילד","dad","mom","child")),
                                                Question("dad wants water","אבא רוצה מים",5,false,QuestionType.FreeStyleQ,null,null),
                                                Question("dad is coming","אבא בא",5,false, QuestionType.TranslationQ),
                                                Question("small dog","כלב קטן",5,false, QuestionType.TranslationQ),
                                                Question("mom wants water","אמא רוצה מים",5,false, QuestionType.TranslationQ),
                                                Question("dad is home","אבא בבית",5,false, QuestionType.TranslationQ),
                                                Question("","",5,false, QuestionType.PictureQ,"כלב",null),
                                                Question("mom likes water","אמא אוהבת מים",5,false,QuestionType.HearingQ))
                                        ,20,true,false,1),

                                Lesson(2,"in the home",
                                        listOf(
                                                Question("","",5,false,QuestionType.TwinsQ,null, listOf("שולחן","כיסא","דלת","table","chair","door")),
                                                Question("dad is in the bathroom","אבא בשירותים",5,false,QuestionType.FreeStyleQ,null,null),
                                                Question("sit at the table","תשב בשולחן",5,false, QuestionType.TranslationQ),
                                                Question("stand on the bed","תעמוד על המיטה",5,false, QuestionType.TranslationQ),
                                                Question("sit on the chair","שב על הכיסא",5,false, QuestionType.TranslationQ),
                                                Question("open the door","תפתח את הדלת",5,false, QuestionType.TranslationQ),
                                                Question("","",5,false, QuestionType.PictureQ,"שולחן",null),
                                                Question("dad likes the house","אבא אוהב את הבית",5,false,QuestionType.HearingQ))
                                        ,20,false,false,1),
                                Lesson(3,"sizes",
                                        listOf(
                                                Question("","",5,false,QuestionType.TwinsQ,null, listOf("גדול", "קטן", "בינוני","big","small","medium")),
                                                Question("the tower is big","המגדל גדול",5,false,QuestionType.FreeStyleQ,null,null),
                                                Question("the ball is small","הכדור קטן",5,false, QuestionType.TranslationQ),
                                                Question("the house is big","הבית גדול",5,false, QuestionType.TranslationQ),
                                                Question("the baby is small","התינוק הקטן",5,false, QuestionType.TranslationQ),
                                                Question("the dad is big","האבא גדול",5,false, QuestionType.TranslationQ),
                                                Question("","",5,false, QuestionType.PictureQ,"הר",null),
                                                Question("ants are very small","נמלים קטנות מאוד",5,false,QuestionType.HearingQ))
                                        ,20,false,false,1),
                                Lesson(4,"food",
                                        listOf(
                                                Question("","",5,false,QuestionType.TwinsQ,null, listOf("חלב", "בשר", "מיץ","milk","meat","juice")),
                                                Question("the pizza is round","הפיצה עגולה",5,false,QuestionType.FreeStyleQ,null,null),
                                                Question("the apple is green","התפוח ירוק",5,false, QuestionType.TranslationQ),
                                                Question("the banana is yellow","הבננה צהובה",5,false, QuestionType.TranslationQ),
                                                Question("the food is hot","האוכל חם",5,false, QuestionType.TranslationQ),
                                                Question("the ice is cold","הקרח קר",5,false, QuestionType.TranslationQ),
                                                Question("","",5,false, QuestionType.PictureQ,"חתול",null),
                                                Question("you like wine","אתה אוהב יין",5,false,QuestionType.HearingQ))
                                        ,20,false,false,1),
                                Lesson(5,"family",
                                        listOf(
                                                Question("","",5,false,QuestionType.TwinsQ,null, listOf("אח", "אחות", "תינוק","brother","sister","baby")),
                                                Question("my family is big","המשפחה שלי גדולה",5,false,QuestionType.FreeStyleQ,null,null),
                                                Question("I have a big brother","יש לי אח גדול",5,false, QuestionType.TranslationQ),
                                                Question("I have an uncle","יש לי דוד",5,false, QuestionType.TranslationQ),
                                                Question("my granddad is old","סבא שלי מבוגר",5,false, QuestionType.TranslationQ),
                                                Question("my sister is young","אחות שלי צעירה",5,false, QuestionType.TranslationQ),
                                                Question("","",5,false, QuestionType.PictureQ,"ילדה",null),
                                                Question("dad entered the house","אבא נכנס לבית",5,false,QuestionType.HearingQ))
                                        ,20,false,false,1),
                                Lesson(6,"mix 1",
                                        listOf(
                                                Question("","",5,false,QuestionType.TwinsQ,null, listOf("חלב", "בשר", "מיץ","milk","meat","juice")),
                                                Question("my family is big","המשפחה שלי גדולה",5,false,QuestionType.FreeStyleQ,null,null),
                                                Question("the baby is small","התינוק הקטן",5,false, QuestionType.TranslationQ),
                                                Question("my granddad is old","סבא שלי מבוגר",5,false, QuestionType.TranslationQ),
                                                Question("the food is hot","האוכל חם",5,false, QuestionType.TranslationQ),
                                                Question("open the door","תפתח את הדלת",5,false, QuestionType.TranslationQ),
                                                Question("","",5,false, QuestionType.PictureQ,"מכונית",null),
                                                Question("mom entered the house","אמא ניכנסה לבית",5,false,QuestionType.HearingQ))
                                        ,20,false,false,1),
                                Lesson(7,"mix 2",
                                        listOf(
                                                Question("","",5,false,QuestionType.TwinsQ,null, listOf("שולחן","כיסא","דלת","table","chair","door")),
                                                Question("dad wants water","אבא רוצה מים",5,false,QuestionType.FreeStyleQ,null,null),
                                                Question("dad is coming","אבא בא",5,false, QuestionType.TranslationQ),
                                                Question("I have an uncle","יש לי דוד",5,false, QuestionType.TranslationQ),
                                                Question("the ice is cold","הקרח קר",5,false, QuestionType.TranslationQ),
                                                Question("sit on the chair","שב על הכיסא",5,false, QuestionType.TranslationQ),
                                                Question("","",5,false, QuestionType.PictureQ,"ילד",null),
                                                Question("the ball is round","הכדור עגול",5,false,QuestionType.HearingQ))
                                        ,20,false,false,1),
                                Lesson(8,"mix 3",
                                        listOf(
                                                Question("","",5,false,QuestionType.TwinsQ,null, listOf("גדול", "קטן", "בינוני","big","small","medium")),
                                                Question("dad is in the bathroom","אבא בשירותים",5,false,QuestionType.FreeStyleQ,null,null),
                                                Question("the apple is green","התפוח ירוק",5,false, QuestionType.TranslationQ),
                                                Question("mom wants water","אמא רוצה מים",5,false, QuestionType.TranslationQ),
                                                Question("my granddad is old","סבא שלי מבוגר",5,false, QuestionType.TranslationQ),
                                                Question("the ice is cold","הקרח קר",5,false, QuestionType.TranslationQ),
                                                Question("","",5,false, QuestionType.PictureQ,"שלג",null),
                                                Question("you like wine","אתה אוהב יין",5,false,QuestionType.HearingQ))
                                        ,20,false,false,1),


                                        Lesson(9,"sizes",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("רחב", "צר", "ארוך","wide","narrow","long")),
                                                        Question("the sea is deep","הים עמוק",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("the mountain is huge","ההר ענק",5,false, QuestionType.TranslationQ),
                                                        Question("i have a tiny bug","יש לי חרק זעיר",5,false, QuestionType.TranslationQ),
                                                        Question("I have a medium shirt size","יש לי מידת חולצה בינונית",5,false, QuestionType.TranslationQ),
                                                        Question("I am the smallest size","אני המידה הקטנה ביותר",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"מים",null),
                                                        Question("this wall is too high","הקיר הזה גבוה מדי",5,false,QuestionType.HearingQ))
                                                ,30,false,false,2),

                                        Lesson(10,"sports",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("ריצה", "שחייה", "הליכה","running","swimming","walking")),
                                                        Question("I love Basketball","אני אוהב כדורסל",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("run after the ball","רוץ אחרי הכדור",5,false, QuestionType.TranslationQ),
                                                        Question("swim fast in the water","לשחות מהר במים",5,false, QuestionType.TranslationQ),
                                                        Question("score a goal","להבקיע גול",5,false, QuestionType.TranslationQ),
                                                        Question("fight in the ring","להילחם בזירה",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"יין",null),
                                                        Question("I want to go to swimming","אני רוצה ללכת לשחות",5,false,QuestionType.HearingQ))
                                                ,30,false,false,2),
                                        Lesson(11,"food",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("עגבנייה", "תפוח", "תפוז","tomato","apple","orange")),
                                                        Question("I am making a salad","אני מכין סלט",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("the hotdog is tasty","הנקניקייה טעימה",5,false, QuestionType.TranslationQ),
                                                        Question("the soup is ready to eat","המרק מוכן לאכילה",5,false, QuestionType.TranslationQ),
                                                        Question("the hot pan is outside cooling","המחבת החמה מתקררת בחוץ",5,false, QuestionType.TranslationQ),
                                                        Question("the cookies are filled with chocolate","העוגיות מלאות בשוקולד",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"ספר",null),
                                                        Question("I need to go to eat","אני צריך ללכת לאכול",5,false,QuestionType.HearingQ))
                                                ,30,false,false,2),
                                        Lesson(12,"greetings",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("שלום", "להיתראות", "ברכות","hello","bye","blessings")),
                                                        Question("happy holiday","חג שמח",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("hello, how are you","שלום מה שלומך",5,false, QuestionType.TranslationQ),
                                                        Question("it is great to see you","זה נהדר לראות אותך",5,false, QuestionType.TranslationQ),
                                                        Question("bye for now, keep in touch","להתראות בינתיים, תשמור על קשר",5,false, QuestionType.TranslationQ),
                                                        Question("I had a great time with you","היה לי זמן נהדר איתך",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"מטרייה",null),
                                                        Question("thank you for coming","תודה שבאת",5,false,QuestionType.HearingQ))
                                                ,30,false,false,2),
                                        Lesson(13,"work",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("מחשב", "ספר", "עט","computer","book","pen")),
                                                        Question("I go to work early","אני הולך לעבודה מוקדם",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("I am working really hard","אני עובד ממש קשה",5,false, QuestionType.TranslationQ),
                                                        Question("this work is really easy","העבודה הזו ממש קלה",5,false, QuestionType.TranslationQ),
                                                        Question("I am finished with my work","סיימתי עם עבודתי",5,false, QuestionType.TranslationQ),
                                                        Question("I finished my work for today","סיימתי את עבודתי להיום",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"בית",null),
                                                        Question("dad goes to work every day","אבא הולך לעבודה כל יום",5,false,QuestionType.HearingQ))
                                                ,30,false,false,2),
                                        Lesson(14,"mix 1",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("עגבנייה", "תפוח", "תפוז","tomato","apple","orange")),
                                                        Question("I go to work early","אני הולך לעבודה מוקדם",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("I am finished with my work","סיימתי עם עבודתי",5,false, QuestionType.TranslationQ),
                                                        Question("the soup is ready to eat","המרק מוכן לאכילה",5,false, QuestionType.TranslationQ),
                                                        Question("the food is hot","האוכל חם",5,false, QuestionType.TranslationQ),
                                                        Question("my granddad is old","סבא שלי מבוגר",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"מחשב",null),
                                                        Question("I feel very happy","אני מרגיש מאוד שמח",5,false,QuestionType.HearingQ))
                                                ,30,false,false,2),
                                        Lesson(15,"mix 2",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("שלום", "להיתראות", "ברכות","hello","bye","blessings")),
                                                        Question("happy holiday","חג שמח",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("swim fast in the water","לשחות מהר במים",5,false, QuestionType.TranslationQ),
                                                        Question("I have an uncle","יש לי דוד",5,false, QuestionType.TranslationQ),
                                                        Question("I have a medium shirt size","יש לי מידת חולצה בינונית",5,false, QuestionType.TranslationQ),
                                                        Question("the cookies are filled with chocolate","העוגיות מלאות בשוקולד",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"לחם",null),
                                                        Question("the girl is going to school","הילדה הולכת לבית הספר",5,false,QuestionType.HearingQ))
                                                ,30,false,false,2),
                                        Lesson(16,"mix 3",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("רחב", "צר", "ארוך","wide","narrow","long")),
                                                        Question("I love Basketball","אני אוהב כדורסל",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("the mountain is huge","ההר ענק",5,false, QuestionType.TranslationQ),
                                                        Question("mom wants water","אמא רוצה מים",5,false, QuestionType.TranslationQ),
                                                        Question("fight in the ring","להילחם בזירה",5,false, QuestionType.TranslationQ),
                                                        Question("I had a great time with you","היה לי זמן נהדר איתך",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"פרח",null),
                                                        Question("the teacher is teaching","המורה מלמד",5,false,QuestionType.HearingQ))
                                                ,30,false,false,2),

                                        Lesson(17,"colors",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("אדום", "צהוב", "ירוק","red","yellow","green")),
                                                        Question("I love many colors","אני אוהב צבעים רבים",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("the sky is blue","השמיים כחולים",5,false, QuestionType.TranslationQ),
                                                        Question("the sunset is full of colors","השקיעה מלאה בצבעים",5,false, QuestionType.TranslationQ),
                                                        Question("my favorite color is purple","הצבע האהוב עליי הוא סגול",5,false, QuestionType.TranslationQ),
                                                        Question("yellow is a very bright color","צהוב הוא צבע בהיר מאוד",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"עץ",null),
                                                        Question("the rainbow has many colors","לקשת בענן יש צבעים רבים",5,false,QuestionType.HearingQ))
                                                ,40,false,false,3),

                                        Lesson(18,"money",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("מטבע", "שטר", "הלוואה","coin","bill","loan")),
                                                        Question("all my money is in the bank","כל הכסף שלי נמצא בבנק",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("I am very rich","אני עשיר מאוד",5,false, QuestionType.TranslationQ),
                                                        Question("the gum is not worth so much","המסטיק לא שווה כל כך הרבה",5,false, QuestionType.TranslationQ),
                                                        Question("you are are able to buy alot","אתה מסוגל לקנות הרבה דברים",5,false, QuestionType.TranslationQ),
                                                        Question("I owe you money","אני חייב לך כסף",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"חשמל",null),
                                                        Question("A car is very expensive","מכונית יקרה מאוד",5,false,QuestionType.HearingQ))
                                                ,40,false,false,3),
                                        Lesson(19,"fashion",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("יפה", "נאה", "שמן","beautiful","handsome","fat")),
                                                        Question("the woman is beautiful","האישה יפה",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("this shirt looks good","החולצה הזו נראית טוב",5,false, QuestionType.TranslationQ),
                                                        Question("the dress is to long on me","השמלה ארוכה עליי",5,false, QuestionType.TranslationQ),
                                                        Question("the shoe is to small for me","הנעל קטנה בשבילי",5,false, QuestionType.TranslationQ),
                                                        Question("A hat helps you in the summer","כובע עוזר לך בקיץ",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"אש",null),
                                                        Question("these pants look very good","המכנסיים האלה נראים טוב מאוד",5,false,QuestionType.HearingQ))
                                                ,40,false,false,3),
                                        Lesson(20,"weather",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("גשם", "שמש", "ענן","rain","sun","cloud")),
                                                        Question("the weather is perfect outside","מזג האוויר מושלם בחוץ",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("it is a sunny day today","היום זה יום שטוף שמש",5,false, QuestionType.TranslationQ),
                                                        Question("it is raining very hard","יורד גשם חזק מאוד",5,false, QuestionType.TranslationQ),
                                                        Question("they say that it will snow","הם אומרים שירד שלג",5,false, QuestionType.TranslationQ),
                                                        Question("tomorrow will be very hot","מחר יהיה חם מאוד",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"טלפון",null),
                                                        Question("today it is cold and rainy outside","היום קר וגשום בחוץ",5,false,QuestionType.HearingQ))
                                                ,40,false,false,3),
                                        Lesson(21,"learning",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("שיעור", "שאלה", "מורה","lesson","question","teacher")),
                                                        Question("there is a lot of learning to do","יש הרבה למידה לעשות",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("the sentence is very long","המשפט ארוך מאוד",5,false, QuestionType.TranslationQ),
                                                        Question("math is very hard","מתמטיקה קשה מאוד",5,false, QuestionType.TranslationQ),
                                                        Question("my school is very good","בית הספר שלי טוב מאוד",5,false, QuestionType.TranslationQ),
                                                        Question("I like my teacher very much","אני אוהב את המורה שלי מאוד",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"כוס",null),
                                                        Question("the teacher is teaching very fast","המורה מלמד מהר מאוד",5,false,QuestionType.HearingQ))
                                                ,40,false,false,3),
                                        Lesson(6,"mix 1",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("מטבע", "שטר", "הלוואה","coin","bill","loan")),
                                                        Question("I love many colors","אני אוהב צבעים רבים",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("the shoe is to small for me","הנעל קטנה בשבילי",5,false, QuestionType.TranslationQ),
                                                        Question("you are able to buy alot","אתה מסוגל לקנות הרבה דברים",5,false, QuestionType.TranslationQ),
                                                        Question("yellow is a very bright color","צהוב הוא צבע בהיר מאוד",5,false, QuestionType.TranslationQ),
                                                        Question("tomorrow will be very hot","מחר יהיה חם מאוד",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"כיסא",null),
                                                        Question("mom likes bread and wine","אמא אוהבת לחם ויין",5,false,QuestionType.HearingQ))
                                                ,40,false,false,3),
                                        Lesson(23,"mix 2",
                                                listOf(
                                                        Question("","",5,false,QuestionType.TwinsQ,null, listOf("גשם", "שמש", "ענן","rain","sun","cloud")),
                                                        Question("the woman is beautiful","האישה יפה",5,false,QuestionType.FreeStyleQ,null,null),
                                                        Question("the sentence is very long","המשפט ארוך מאוד",5,false, QuestionType.TranslationQ),
                                                        Question("the gum is not worth so much","המסטיק לא שווה כל כך הרבה",5,false, QuestionType.TranslationQ),
                                                        Question("my favorite color is purple","הצבע האהוב עליי הוא סגול",5,false, QuestionType.TranslationQ),
                                                        Question("I owe you money","אני חייב לך כסף",5,false, QuestionType.TranslationQ),
                                                        Question("","",5,false, QuestionType.PictureQ,"מקרר",null),
                                                        Question("dad likes wine and bread","אבא אוהב יין ולחם",5,false,QuestionType.HearingQ))
                                                ,40,false,false,3)
        )


    }
}