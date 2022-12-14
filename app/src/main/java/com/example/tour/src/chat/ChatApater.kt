package com.example.tour.src.chat


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import android.content.Intent
import com.bumptech.glide.Glide
import com.example.tour.databinding.ItemChatListBinding
import com.example.tour.src.chat.room.RoomActivity
import com.example.tour.src.home.MainActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//import com.example.tour.src.MyAdapter.HeaderViewHolder

private lateinit var binding: ItemChatListBinding

class ChatAdapter(
    private val dataSet: ArrayList<ChatList>,
    private val con: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemChatListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
//            is HeaderViewHolder -> holder.bind()
            is ViewHolder -> holder.bind(dataSet[position])
        }
//        holder.bind(dataSet[position])

    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(private val binding: ItemChatListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChatList) {
            val cal = Calendar.getInstance()
            cal.time = Date()
            val yearFormat: DateFormat = SimpleDateFormat("yyyy")
            val monFormat: DateFormat = SimpleDateFormat("M")
            val dateFormat: DateFormat = SimpleDateFormat("d")
            val hourFormat: DateFormat = SimpleDateFormat("H")
            val minFormat: DateFormat = SimpleDateFormat("m")
            var year = yearFormat.format(cal.time)
            var mon = monFormat.format(cal.time)
            var date = dateFormat.format(cal.time)
            var hour = hourFormat.format(cal.time)
            var min = minFormat.format(cal.time)



            Glide.with(con).load(data.festivalImageUrl).into(binding.crewImage)
            binding.crewName.text = data.crewName
            if(data.chatContent.toString().equals("null")){
                binding.lastChatContent.text = ""
            }else{
                binding.lastChatContent.text = data.chatContent
            }


            var now : String
            if(data.updatedAt.equals("")){
                now = ""
            }else{
                //???
                if(year.toInt() - data.updatedAt.substring(0,4).toInt() < 1){
                    System.out.println("??? : " + year.toInt())
                    //???
                    if(mon.toInt() - data.updatedAt.substring(5,7).toInt() < 1){
                        System.out.println("??? : " + mon.toInt())
                        //???
                        if(date.toInt() - data.updatedAt.substring(8,10).toInt() < 1){
                            System.out.println("??? : " + date.toInt())
                            //???
                            if(hour.toInt() - data.updatedAt.substring(11,13).toInt() < 1){
                                System.out.println("??? : " + hour.toInt())
                                //???
                                now = (min.toInt() - data.updatedAt.substring(14,16).toInt()).toString() + "??? ???"
                                System.out.println("??? : " + min.toInt())
                            }else{
                                now = (hour.toInt() - data.updatedAt.substring(11,13).toInt()).toString() + "?????? ???"
                            }
                        }else{
                            now = (date.toInt() - data.updatedAt.substring(8,10).toInt()).toString() + "??? ???"
                        }
                    }else{
                        now = (mon.toInt() - data.updatedAt.substring(5,7).toInt()).toString() + "??? ???"
                    }
                }else{
                    System.out.println("??? : " + year.toInt())
                    System.out.println("???(????????? ???) : " + data.updatedAt.substring(0,4).toInt())
                    now = (year.toInt() - data.updatedAt.substring(0,4).toInt()).toString()
                }
            }
            binding.lastChatTime.text = now

            // ???????????? ???
            binding.chattingRoomLlList.setOnClickListener {
                val intent = Intent(con, RoomActivity::class.java)
                intent.putExtra("roomIdx",data.roomIdx)
                con.startActivity(intent)

            }
        }
    }
}