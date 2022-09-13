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

        // 크루 만들기
        binding.imageFloatingCrewMake.setOnClickListener {
            val intent = Intent(this, CrewMakeActivity::class.java)
            intent.putExtra("title", title)
            startActivity(intent)
        }
        // 크루 들어가기
        binding.imageFloatingCrewAttend.setOnClickListener {
            // 데이터 전달 - 액티비티
            val intent = Intent(this, CrewAttendActivity::class.java)
            intent.putExtra("image_url", image_url)
            intent.putExtra("title", title)
            intent.putExtra("place", place)
            intent.putExtra("festivalIdx", festival_id)
            startActivity(intent)
            finish()
            // 이미지 URL을 Bitmap으로 변경
            var image_task: ImageURLClass = ImageURLClass()
            image_task = ImageURLClass().apply {
                url = URL(image_url)
            }
            var bitmap: Bitmap = image_task.execute().get()
        }

        // 더보기
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

        /*지도 중심점,레벨(zoom) 변경*/
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat!!, lng!!), true)

        // 줌 레벨 변경
        mapView.setZoomLevel(1, true)

        // 줌 인
        mapView.zoomIn(true)

        // 줌 아웃
        mapView.zoomOut(true)

        /*마커 추가*/

        val MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(lat!!, lng!!)

        // 마커 아이콘 추가하는 함수
        val marker1 = MapPOIItem()

        // 클릭 했을 때 나오는 호출 값
        marker1.itemName = title

        marker1.tag = 0

        // 좌표를 입력받아 현 위치로 출력
        marker1.mapPoint = MARKER_POINT1

        //  (클릭 전)기본으로 제공하는 BluePin 마커 모양의 색.
        marker1.markerType = MapPOIItem.MarkerType.BluePin

        // (클릭 후) 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        marker1.selectedMarkerType = MapPOIItem.MarkerType.RedPin

        // 지도화면 위에 추가되는 아이콘을 추가하기 위한 호출(말풍선 모양)
        mapView.addPOIItem(marker1)

        binding.mapView.addView(mapView)
    }
}