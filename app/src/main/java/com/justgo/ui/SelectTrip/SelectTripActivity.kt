package com.justgo.ui.SelectTrip

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.justgo.Adapter.SelectTripListAdapter
import com.justgo.Connecter.getTourList
import com.justgo.Model.MyTripItem
import com.justgo.R
import com.justgo.ui.SelectDone.SelectDoneActivity
import kotlinx.android.synthetic.main.activity_select_trip.*
import org.jetbrains.anko.toast
import kotlin.math.roundToInt

class SelectTripActivity : AppCompatActivity(), ItemClickMethod {
    val doneIntent by lazy { Intent(baseContext, SelectDoneActivity::class.java) }
    override fun onClick(placeId: String, lat: Double, lng: Double) {
        /*    if (!SelectTripListAdapter.select_item_flag) {
                select_trip_btn.visibility = View.VISIBLE
            } else {
                select_trip_btn.visibility = View.GONE
            }*/
        Log.d("SelectTripActivity", "placeid: ${placeId}")
        doneIntent.putExtra("placeid", placeId)
        doneIntent.putExtra("desLat", lat)
        doneIntent.putExtra("desLng", lng)
        startActivity(doneIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_trip)

        setSupportActionBar(select_trip_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        val intent = intent
        val lat = intent.getDoubleExtra("lat", 0.0)
        val lng = intent.getDoubleExtra("lng", 0.0)
        val theme = intent.getStringExtra("theme")
        val minDistance = intent.getIntExtra("minDistance", 0)
        val maxDistance = intent.getIntExtra("maxDistance", 0)
        val transType = intent.getIntExtra("transType", 0)

        with(doneIntent) {
            putExtra("lat", lat)
            putExtra("lng", lng)
            putExtra("theme", theme)
            putExtra("transType", transType)
        }

        val data = arrayListOf<MyTripItem>()
        getTourList(lat, lng, theme, minDistance, maxDistance) {
            onSuccess = {
                var index = 1
                body()!!.list.forEach {
                    Log.d("SelectTripActivity", "placeid: ${it.placeid}")
                    val item = arrayListOf<String>()
                    it.theme.split(",").forEach {
                        item.add(map[it]!!)
                    }
                    data.add(MyTripItem("Select ${(64 + index).toChar()}", "${it.distance.roundToInt().toDouble() / 1000} Km", item, it.placeid, it.lat, it.lng))
                    index++
                }
                val adapter_list = SelectTripListAdapter(data, this@SelectTripActivity, this@SelectTripActivity)
                adapter_list.transType = transType
                select_trip_recycler.adapter = adapter_list
            }
            onFailure = {
                toast("이건아닌데;")
            }
        }
        /*val sampledata_tag = ArrayList<String>().apply {
            add("Food")
            add("Instagram")
            add("Rainbow")
            add("Six Siege")
        }

        val sampledata_list = ArrayList<MyTripItem>().apply {
            for (i in 1..10)
                add(MyTripItem("Select " + (64 + i).toChar(), "44km, 30min", sampledata_tag))
        }*/

    }

    val map = hashMapOf(
            "accounting" to "회계",
            "airport" to "항공",
            "amusement_park" to "놀이공원 및 오락시설",
            "aquarium" to "수족관",
            "art_gallery" to "아트 갤러리",
            "atm" to "ATM",
            "bakery" to "제과점",
            "bank" to "은행",
            "bar" to "주점",
            "beauty_salon" to "미용",
            "bicycle_store" to "자전거 가게",
            "book_store" to "책방",
            "bowling_alley" to "볼링장",
            "bus_station" to "정류장",
            "cafe" to "카페",
            "campground" to "야영지",
            "car_dealer" to "자동차 영업소",
            "car_rental" to "자동차 렌탈",
            "car_repair" to "자동차 수리점",
            "car_wash" to "세차장",
            "casino" to "카지노",
            "cemetery" to "묘지",
            "church" to "교회",
            "city_hall" to "공공기관",
            "clothing_store" to "옷가게",
            "convenience_store" to "편의점",
            "courthouse" to "법원",
            "dentist" to "치과",
            "department_store" to "백화점",
            "doctor" to "병원",
            "electrician" to "전기공",
            "electronics_store" to "전자상가",
            "embassy" to "대사관",
            "fire_station" to "소방서",
            "florist" to "화원",
            "funeral_home" to "장례식장",
            "furniture_store" to "가구점",
            "gas_station" to "주유소",
            "gym" to "체육관",
            "hair_care" to "헤어 케어",
            "hardware_store" to "철물점",
            "hindu_temple" to "힌두 사원",
            "home_goods_store" to "생활용품 가게",
            "hospital" to "병원",
            "insurance_agency" to "보험사",
            "jewelry_store" to "보석상",
            "laundry" to "빨래방",
            "lawyer" to "변호사",
            "library" to "도서관",
            "liquor_store" to "주류 판매점",
            "local_government_office" to "지방기관",
            "locksmith" to "자물쇠 수리공",
            "lodging" to "하숙집",
            "meal_delivery" to "배달가능",
            "meal_takeaway" to "테이크아웃",
            "mosque" to "모스크",
            "movie_rental" to "영화방",
            "movie_theater" to "극장",
            "moving_company" to "이삿짐센터",
            "museum" to "박물관",
            "night_club" to "나이트클럽",
            "painter" to "도장공",
            "park" to "공원",
            "parking" to "주차장",
            "pet_store" to "애완동물 가게",
            "pharmacy" to "약국",
            "physiotherapist" to "물리치료",
            "plumber" to "배관공",
            "police" to "경찰",
            "post_office" to "우체국",
            "real_estate_agency" to "부동산",
            "restaurant" to "식당",
            "roofing_contractor" to "지붕 공사 업체",
            "rv_park" to "오토캠핑장",
            "school" to "학교",
            "shoe_store" to "신발가게",
            "shopping_mall" to "쇼핑몰",
            "spa" to "사우나",
            "stadium" to "경기장",
            "storage" to "창고",
            "store" to "가게",
            "subway_station" to "지하철역",
            "supermarket" to "슈퍼마켓",
            "synagogue" to "시나고그",
            "taxi_stand" to "택시 승강장",
            "train_station" to "기차역",
            "transit_station" to "대중교통 정류장",
            "travel_agency" to "여행사",
            "veterinary_care" to "동물병원",
            "zoo" to "동물원",
            "administrative_area_level_1" to "행정 구역",
            "administrative_area_level_2" to "행정 구역",
            "administrative_area_level_3" to "행정 구역",
            "administrative_area_level_4" to "행정 구역",
            "administrative_area_level_5" to "행정 구역",
            "colloquial_area" to "",
            "country" to "나라",
            "establishment" to "기관",
            "finance" to "",
            "floor" to "",
            "food" to "음식",
            "general_contractor" to "건설업자",
            "geocode" to "지역 코드",
            "health" to "건강",
            "intersection" to "교차로",
            "locality" to "지역",
            "natural_feature" to "자연",
            "neighborhood" to "이웃",
            "place_of_worship" to "종교시설",
            "political" to "정치적 시설",
            "point_of_interest" to "흥미로운 곳",
            "post_box" to "우체통",
            "postal_code" to "우편번호",
            "postal_code_prefix" to "우편번호",
            "postal_code_suffix" to "우편번호",
            "postal_town" to "우체국 마을",
            "premise" to "부지",
            "room" to "방",
            "route" to "경로",
            "street_address" to "도로의 주소",
            "street_number" to "도로 번호",
            "sublocality" to "하위 지역",
            "sublocality_level_4" to "하위지역",
            "sublocality_level_5" to "하위지역",
            "sublocality_level_3" to "하위지역",
            "sublocality_level_2" to "하위지역",
            "sublocality_level_1" to "하위지역",
            "subpremise" to "하위 부지")
}