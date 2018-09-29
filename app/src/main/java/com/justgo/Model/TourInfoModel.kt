package com.justgo.Model

class TourInfoModel {

    var result : String = ""
    var name: String = ""
    var address: String = ""
    var image: String = ""
    var theme: String = ""

    var nearSpot: ArrayList<NearSpot> = arrayListOf()

    var nearRestaurant: ArrayList<NearRestaurant> = arrayListOf()

    var comment: ArrayList<Comment> = arrayListOf()

    data class Comment(val rate :Double = 0.0, val content :String= "") {

    }

    class NearSpot {
        var title = ""
        var image = ""
        var address = ""
        var lat = 0.0
        var lng = 0.0
    }

    class NearRestaurant {
        var title = ""
        var address = ""
        var image = ""
        var lat = 0.0
        var lng = 0.0
    }

}