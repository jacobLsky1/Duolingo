package com.jacoblip.andriod.duolingo.utilities

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.jacoblip.andriod.duolingo.Data.models.Picture

class Util {
    companion object{
        var hasInternet : MutableLiveData<Boolean> = MutableLiveData()
        var useAppWithInternet = true
        var UserID = ""

        var spareWords:Array<String> = arrayOf("שלום","למה","אהבה","חתול","כלב","רוצה","מאה","יכול","צריך",
                "צורה","שער","בובה","ממתק","אש","ריצה","הביתה","דלת","שומר","זורח","פרה","ברווז","כוכב","שמש","מיטה",
                "עיגול","מורה","תפוז")

        var imgUrls = listOf<Picture>(
                Picture("dog", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=1.00xw:0.669xh;0,0.190xh&resize=1200:*"),
                Picture("table", "https://cdn.shopify.com/s/files/1/2660/5202/products/CHE3WDTL_5FAC_1400x.jpg?v=1598897552"),
                Picture("mountain", "https://cosmosmagazine.com/wp-content/uploads/2020/02/190218-mount-full-1440x810.jpg"),
                Picture("cat", "https://i.natgeofe.com/n/3861de2a-04e6-45fd-aec8-02e7809f9d4e/02-cat-training-NationalGeographic_1484324.jpg"),
                Picture("girl", "https://www.girlguides.ca/web/images/ggc-national/homepage/GuidingAtHomeMobile.jpg"),
                Picture("car", "https://www.extremetech.com/wp-content/uploads/2019/12/SONATA-hero-option1-764A5360-edit.jpg"),
                Picture("boy", "https://5.imimg.com/data5/EA/TJ/MY-6739764/boys-school-uniform-500x500.jpg"),
                Picture("snow", "https://images2.minutemediacdn.com/image/upload/c_fill,g_auto,h_1248,w_2220/v1555927976/shape/mentalfloss/111798556-565x376_1.jpg?itok=UEG-2KGY"),
                Picture("water", "https://www.europenowjournal.org/wp-content/uploads/2018/12/shutterstock_571761970.jpg"),
                Picture("wine", "https://www.popsci.com/app/uploads/2019/03/18/2ETEXZTIT2N3EUZLYGVXFOAI7A.jpg?width=1440"),
                Picture("book", "https://www.collinsdictionary.com/images/full/book_181404689_1000.jpg"),
                Picture("umbrella", "https://www.susino.co.uk/UserFiles/images/products/1200x1200/kids_rainbow_umbrella_3497.jpg"),
                Picture("house", "https://media-cdn.tripadvisor.com/media/photo-s/12/99/82/95/bellaire-house.jpg"),
                Picture("computer", "https://www.collinsdictionary.com/images/full/computer_49399603.jpg"),
                Picture("bread", "https://assets.bonappetit.com/photos/5f84743360f032defe1f5376/16:9/w_2560%2Cc_limit/Pullman-Loaf-Lede-new.jpg"),
                Picture("flower", "https://www.ikea.com/mx/en/images/products/smycka-artificial-flower-rose-red__0903311_pe596728_s5.jpg"),
                Picture("tree", "https://www.crozetgazette.com/wp-content/uploads/2020/01/iStock-1147108546.jpg"),
                Picture("electricity","https://teufa.ort.org.il/wp-content/uploads/2019/07/%D7%97%D7%A9%D7%9E%D7%9C.jpg"),
                Picture("fire", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/1-1581612592.jpg?crop=1.00xw:0.751xh;0,0.119xh&resize=480:*"),
                Picture("phone", "https://aatik.co.il/wp-content/uploads/2020/09/%D7%98%D7%9C%D7%A4%D7%95%D7%9F-%D7%9B%D7%A9%D7%A8-MTK1.jpg"),
                Picture("cup", "https://www.harfieldtableware.co.uk/wp-content/uploads/2019/09/001MBL-8oz-Cup-Med-Blue-scaled.jpg"),
                Picture("chair", "https://www.oakworld.co.uk/wp-content/uploads/2018/09/Alston-Oak-Dining-Chair-64334-64283.jpg"),
                Picture("refrigerator", "https://kitchenaid-h.assetsadobe.com/is/image/content/dam/business-unit/whirlpool/en-us/marketing-content/site-assets/page-content/refrigerator-sclp/Images/masthead/WHP_ReferSCLP_Mobile_IMG3x-updated.jpg?fit=constrain&fmt=jpg&utc=2018-09-19T19:00:56Z&wid=1245")
        )

        fun logSentence(words:ArrayList<String>){
            var str = ""
            for(word in words)
                str+= "$word "

            str.trim()

            Log.i("answer",str)
        }
    }
}