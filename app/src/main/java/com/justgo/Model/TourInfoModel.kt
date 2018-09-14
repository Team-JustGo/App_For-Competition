package com.justgo.Model

class TourInfoModel {

    var name: String = ""
    var address: String = ""
    var info: String = ""
    var image: String = ""
    var theme: String = ""
    var nearSpot: ArrayList<NearSpot> = ArrayList()

    class NearSpot {
        var id = ""
        var title = ""
        var image = ""
        var rate = 0
        var address = ""
        var theme = ""
    }

    var nearRestaurant: ArrayList<NearRestaurant> = arrayListOf()

    class NearRestaurant {
        var name = ""
        var address = ""
        var image = ""
        var rate = 0
    }

    var comment: ArrayList<Comment> = arrayListOf()

    class Comment {
        var profileImage = ""
        var profileName = ""
        var rate = 0
        var content = ""
        var date = ""
    }


}