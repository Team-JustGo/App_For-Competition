package com.justgo.Model

class TourListModel {
    var list: ArrayList<TourListItem> = arrayListOf()

    class TourListItem {
        var lat = 0F
        var lng = 0F
        var reqtime = 0F
        var rate = 0
        var theme = ""
        var comment = ""
    }
}