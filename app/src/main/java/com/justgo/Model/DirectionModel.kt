package com.justgo.Model

class DirectionModel {
    val points = arrayListOf<Point>()
    var polyline = ""
    data class Point(
            var instruction: String = "",
            var lat: Double = 0.0,
            var lng: Double = 0.0
    )
}