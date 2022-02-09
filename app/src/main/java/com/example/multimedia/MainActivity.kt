package com.example.multimedia

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.multimedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.isEnabled = true
        binding.btnPause.isEnabled = true
        binding.btnResume.isEnabled = true
        binding.btnStop.isEnabled = true

        val mediaPlayer = MediaPlayer()
        val audioAttributes = AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
        mediaPlayer.setAudioAttributes(audioAttributes.build())

        val  uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.musikk)
        mediaPlayer.setDataSource(this, uri)

        binding.btnPlay.setOnClickListener {
            mediaPlayer.prepare()
            mediaPlayer.start()

            binding.btnPlay.isEnabled = false
            binding.btnPause.isEnabled = true
            binding.btnResume.isEnabled = false
            binding.btnStop.isEnabled = true
        }

        binding.btnPause.setOnClickListener {
            if (mediaPlayer.isPlaying) mediaPlayer.pause()
            binding.btnPlay.isEnabled = false
            binding.btnPause.isEnabled = false
            binding.btnResume.isEnabled = true
            binding.btnStop.isEnabled = false
        }

        binding.btnResume.setOnClickListener {
            mediaPlayer.start()
            binding.btnPlay.isEnabled = false
            binding.btnPause.isEnabled = true
            binding.btnResume.isEnabled = true
            binding.btnStop.isEnabled = true
        }

        binding.btnStop.setOnClickListener {
            if (mediaPlayer.isPlaying) mediaPlayer.stop()
            binding.btnPlay.isEnabled = true
            binding.btnPause.isEnabled = false
            binding.btnResume.isEnabled = false
            binding.btnStop.isEnabled = false
        }

        binding.btnVideo.setOnClickListener {
            val i = Intent(this, VideoActivity::class.java)
            startActivity(i)
        }

        binding.btnVideoStreaming.setOnClickListener {
            val i = Intent(this, VideoStreamingActivity::class.java)
            startActivity(i)
        }

    }
}