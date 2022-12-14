package com.example.tour.src.home.detail

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivityMainDetailBinding
import com.example.tour.src.crew.crewAttend.CrewAttendActivity
import com.example.tour.src.crew.make.CrewMakeActivity
import com.example.tour.util.ImageURLClass
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.net.URL


class DetailActivity : BaseActivity<ActivityMainDetailBinding>(ActivityMainDetailBinding::inflate) {
    private var image_url: String? = null
    private var title: String? = null
    private var place: String? = null
    private var content: String? = null
    private var date: String? = null
    private var address: String? = null
    private var money: String? = null
    private var phoneNumber: String? = null
    private var homepageURL: String? = null
    private var facility: String? = null
    private var festival_id: Int = -1
    private var lat : Double? = null
    private var lng : Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent = intent
        var count = 0

        image_url = intent.getStringExtra("image_url")
        Log.d("test", image_url.toString())
        title = intent.getStringExtra("title")
        place = intent.getStringExtra("place")
        content = intent.getStringExtra("content")
        date = intent.getStringExtra("date")
        address = intent.getStringExtra("address")
        money = intent.getStringExtra("money")
        phoneNumber = intent.getStringExtra("phoneNumber")
        homepageURL = intent.getStringExtra("homepageURL")
        facility = intent.getStringExtra("facility")
        festival_id = intent.getIntExtra("festivalIdx", 0)
        content = intent.getStringExtra("content")
        lat = intent.getDoubleExtra("lat",0.0)
        lng = intent.getDoubleExtra("lng",0.0)

        var image_task: ImageURLClass = ImageURLClass()
        image_task = ImageURLClass().apply {
            url = URL(image_url)
        }
        var bitmap: Bitmap = image_task.execute().get()

        //Glide.with(this).load(image_url).into(binding.detailImageVaner)
        binding.detailImageVaner.setImageBitmap(bitmap)
        binding.detailTextTitle.text = title
        //binding.detailTextPlace.text = place
        binding.detailTextContent.text = content
        binding.detailTextDate.text = date
        binding.detailTextAddress.text = address
        binding.detailTextMoney.text = money
        binding.detailTextPhonenumber.text = phoneNumber
        binding.detailTextHomepageURL.text = homepageURL
        binding.detailTextFacility.text = facility

        // ?????? ?????????
        binding.imageFloatingCrewMake.setOnClickListener {
            val intent = Intent(this, CrewMakeActivity::class.java)
            intent.putExtra("title", title)
            startActivity(intent)
        }
        // ?????? ????????????
        binding.imageFloatingCrewAttend.setOnClickListener {
            // ????????? ?????? - ????????????
            val intent = Intent(this, CrewAttendActivity::class.java)
            intent.putExtra("image_url", image_url)
            intent.putExtra("title", title)
            intent.putExtra("place", place)
            intent.putExtra("festivalIdx", festival_id)
            startActivity(intent)
            finish()
            // ????????? URL??? Bitmap?????? ??????
            var image_task: ImageURLClass = ImageURLClass()
            image_task = ImageURLClass().apply {
                url = URL(image_url)
            }
            var bitmap: Bitmap = image_task.execute().get()
        }

        // ?????????
        binding.ibMoreSee.setOnClickListener {
            if(count%2 == 0) {
                binding.detailTextContent.visibility = View.VISIBLE
                binding.contentImage.visibility = View.VISIBLE
                binding.detailTextContent.text = content
            }
            else {
                binding.detailTextContent.visibility = View.GONE
                binding.contentImage.visibility = View.GONE
                binding.detailTextContent.text = content
            }
            count++
        }

        var mapView = MapView(this)

        /*?????? ?????????,??????(zoom) ??????*/
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat!!, lng!!), true)

        // ??? ?????? ??????
        mapView.setZoomLevel(1, true)

        // ??? ???
        mapView.zoomIn(true)

        // ??? ??????
        mapView.zoomOut(true)

        /*?????? ??????*/

        val MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(lat!!, lng!!)

        // ?????? ????????? ???????????? ??????
        val marker1 = MapPOIItem()

        // ?????? ?????? ??? ????????? ?????? ???
        marker1.itemName = title

        marker1.tag = 0

        // ????????? ???????????? ??? ????????? ??????
        marker1.mapPoint = MARKER_POINT1

        //  (?????? ???)???????????? ???????????? BluePin ?????? ????????? ???.
        marker1.markerType = MapPOIItem.MarkerType.BluePin

        // (?????? ???) ????????? ???????????????, ???????????? ???????????? RedPin ?????? ??????.
        marker1.selectedMarkerType = MapPOIItem.MarkerType.RedPin

        // ???????????? ?????? ???????????? ???????????? ???????????? ?????? ??????(????????? ??????)
        mapView.addPOIItem(marker1)

        binding.mapView.addView(mapView)
    }
}