package com.justgo.Model

class DirectionModel {
    val points = arrayListOf<Point>()

    class Point {
        var instruction = ""
        var lat = 0.0
        var lng = 0.0
    }
}