package com.justgo.Model

class TourListModel {
    var list: ArrayList<TourListItem> = arrayListOf()

    class TourListItem {
        var placeId = ""
        var lat = 0.0
        var lng = 0.0
        var theme = ""
        var distance = 0.0
    }
}