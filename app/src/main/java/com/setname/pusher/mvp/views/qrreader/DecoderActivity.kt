package com.setname.pusher.mvp.views.qrreader

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PointF
import android.os.Bundle
import android.widget.Toast
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import com.setname.pusher.R
import kotlinx.android.synthetic.main.activity_decoder.*

class DecoderActivity : Activity(), QRCodeReaderView.OnQRCodeReadListener {

    private lateinit var text: String

    private var CAMERA_REQUEST_CODE = 0

    override fun onQRCodeRead(text: String?, points: Array<out PointF>?) {

        val intent = Intent()
        intent.putExtra("text", text)
        setResult(RESULT_OK, intent)
        finish()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decoder)

        CAMERA_REQUEST_CODE = resources.getInteger(R.integer.CAMERA_REQUEST_CODE)

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            requestCustomCameraPermission()
            qr_decoder_view.setOnClickListener { requestCustomCameraPermission() }

        } else {

            initQrCodeReaderView()

        }

    }

    fun initQrCodeReaderView(){

        qr_decoder_view.apply {

            setOnQRCodeReadListener(this@DecoderActivity)
            setBackCamera()
            forceAutoFocus()

        }

    }


    fun requestCustomCameraPermission() {

        requestPermissions(
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                activity_decoder_constraint_layout.removeView(qr_decoder_view)
                activity_decoder_constraint_layout.addView(qr_decoder_view)

                initQrCodeReaderView()

                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show()

            } else {

                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()

            }

        }
    }

    override fun onResume() {
        super.onResume()
        qr_decoder_view.startCamera()
    }

    override fun onPause() {
        super.onPause()
        qr_decoder_view.stopCamera()
    }

}