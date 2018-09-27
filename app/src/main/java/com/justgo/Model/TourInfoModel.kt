package com.justgo.Model

class TourInfoModel {

    var result : String = ""
    var name: String = ""
    var address: String = ""
    var image: String = ""
    var theme: String = ""
    var nearSpot: ArrayList<NearSpot> = ArrayList()

    class NearSpot {
        var title = ""
        var image = ""
        var address = ""
        var lat = 0.0
        var lng = 0.0
    }

    var nearRestaurant: ArrayList<NearRestaurant> = arrayListOf()

    class NearRestaurant {
        var name = ""
        var address = ""
        var image = ""
        var lat = 0.0
        var lng = 0.0
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