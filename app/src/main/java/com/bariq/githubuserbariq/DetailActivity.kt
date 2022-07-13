package com.bariq.githubuserbariq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bariq.githubuserbariq.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        binding.cvProfile.setImageResource(user.avatar)
        binding.apply {
            tvName.text = user.name
            tvUsername.text = user.username
            tvFollowing.text = "Following \n${user.following}"
            tvFollowers.text = "Followers \n${user.followers}"
            tvRepo.text = "Repository \n${user.repository}"
            tvCompany.text = "Company : ${user.company}"
            tvLocation.text = user.location
        }

        binding.btnShare.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val share: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, binding.tvUsername.text.toString())
            type = "text/plain"
        }
        startActivity(share)
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}