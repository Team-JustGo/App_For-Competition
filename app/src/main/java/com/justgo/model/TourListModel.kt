package com.justgo.model

class TourListModel {
    var list: ArrayList<TourListItem> = arrayListOf()

    class TourListItem {
        var placeid = ""
        var lat = 0.0
        var lng = 0.0
        var theme = ""
        var distance = 0.0
    }
}