package com.example.tour.src.my.signup

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivitySignUpChechContentBinding

class SignUpCheckContentActivity : BaseActivity<ActivitySignUpChechContentBinding>(ActivitySignUpChechContentBinding::inflate) {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.signUpCheckContent.text = "1. 수집 및 이용목적\n" +
                "·APP 회원가입, 서비스 이용시 본인의 확인\n" +
                "·각종고지 · 통지, 고충처리, 분쟁조정을 위한 기록 보존\n" +
                "2. 수집 및 이용하는 개인정보 항목\n" +
                "APP 회원가입\n" +
                "·이메일, 연락처\n" +
                "APP 서비스 관련\n" +
                "·보험계약정보\n" +
                "·APP 접속 정보 및 서비스 이용정보\n" +
                "3. 수집방법\n" +
                "·핸드폰에 직접 입력하는 방식\n" +
                "·APP에 마련된 회원가입란에 회원 본인이 직접 입력하는 방식\n" +
                "4. 보유 및 이용기간\n" +
                "이용자가 회원탈회를 요청하거나 개인정보의 수집·이용 등에 대한 동의를 철회할 때까지 보유·이용합니다. 다만, 다음의 정보에 대해서는 아래의 이유로 예외로 합니다.\n" +
                "·기타 법률에 의해 이용자의 개인정보를 보존해야 할 필요가 있는 경우에는 해당 법률의 규정에 따릅니다.\n" +
                "·정보통신망 이용촉진 및 정보보 등에 관한 법률에 따라 APP을 대통령령으로 정하는 기간동안 로그인하지 아니하는 이용자의 개인정보를 보호하기 위하여 개인정보 파기 등 필요한 조치를 취합니다. \n" +
                "·APP 회원은 1년간 미로그인시 이용정지처리가 되며 3년이 지날시 회원정보 삭제처리가 됩니다. 이용자의 요청에 따라 달리 정한 경우가 있을 경우, 달리 정한 기간을 적용할 수 있습니다."
    }
}