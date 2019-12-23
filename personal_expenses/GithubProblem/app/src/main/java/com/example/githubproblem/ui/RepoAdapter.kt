package com.example.githubproblem.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproblem.R
import com.example.githubproblem.data.GithubRepoDTO
import com.example.githubproblem.helper.AppUtils
import com.squareup.picasso.Picasso

class RepoAdapter(val dataSource: ArrayList<GithubRepoDTO>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_repo,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = (holder as RepoViewHolder)

        vh.binTo(dataSource[position])
    }

    override fun getItemViewType(position: Int): Int {
        //we have only one viewType
        return 0
    }

    class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val desc_text = view.findViewById<AppCompatTextView>(R.id.item_desc)
        val profile_img = view.findViewById<ImageView>(R.id.item_profile_img)
        val title_text = view.findViewById<AppCompatTextView>(R.id.item_title)
        val likes_text = view.findViewById<AppCompatTextView>(R.id.item_likes)
        val time_text = view.findViewById<AppCompatTextView>(R.id.item_time)
        val dot_img = view.findViewById<View>(R.id.item_img_language)

        fun binTo(repo: GithubRepoDTO) {
            desc_text.text = repo.description
            title_text.text = repo.fullName

            if (repo.language == null) {
                dot_img.visibility = View.GONE
            } else {
                dot_img.visibility = View.VISIBLE
            }

            repo.createdAt?.let { timeString ->
                time_text.text = AppUtils.getDate(timeString)
            }


            likes_text.text = repo.language

            Picasso.get().load(repo.owner?.avatarUrl)
                .into(profile_img)
        }
    }
}