package com.justgo.ui.Arrive

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.justgo.Adapter.ArriveInfoReviewAdapter
import com.justgo.Adapter.MyTripTagAdapter
import com.justgo.Connecter.getTourInfo
import com.justgo.Model.ReviewItem
import com.justgo.Model.ArriveItem
import com.justgo.Model.MyTripTagItem
import com.justgo.R

class ArriveInfoFragment() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_arrive_info, container, false)

        var image = rootview.findViewById<ImageView>(R.id.arrive_info_picture)
        var theme: ArrayList<String> = arrayListOf()
        val comments: ArrayList<ReviewItem> = arrayListOf()


        getTourInfo("ChIJ45fXsE1IZTURoogpKhRsxuY") {
            onSuccess = {
                Glide.with(activity!!).load(body()!!.image).into(image)

                body()!!.theme.split(',').forEach {
                    theme.add(it)
                }

                body()!!.comment.forEach {
                    comments.add(ReviewItem(it.rate.toString(), it.content))
                }
            }

            onFailure = {

            }
        }


        val adapter = MyTripTagAdapter(theme)

        val adapter2 = ArriveInfoReviewAdapter(comments)

        val taglist = rootview.findViewById<RecyclerView>(R.id.arrive_info_taglist)

        val reviewlist = rootview.findViewById<RecyclerView>(R.id.arrive_info_reviewlist)

        reviewlist.adapter = adapter2

        reviewlist.layoutManager = object : LinearLayoutManager(activity) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        with(taglist) {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        return rootview
    }
}