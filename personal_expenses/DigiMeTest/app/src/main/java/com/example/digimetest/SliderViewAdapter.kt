package com.example.digimetest

import android.content.Context
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.digimetest.SuccessStates.*
import com.squareup.picasso.Picasso


class SliderViewAdapter(
    var context: Context,
    var dataSource: Array<Int>,
    var listener: OnItemClickListener
) : PagerAdapter() {

    private var inflater: LayoutInflater? = null
    private var success: SuccessStates? = null

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as View
//        return true
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun updateScreenStatus(success: SuccessStates) {
        this.success = success

        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = inflater!!.inflate(R.layout.slide_item, container, false)

        val viewImage = view?.findViewById<ImageView>(R.id.imageView)

        if (position == 12 && success != null) {
            when (success) {
                EMPTY -> Picasso.get().load(R.drawable.no_data).into(viewImage)
                NODATA -> Picasso.get().load(R.mipmap.fail).into(viewImage)
                DATA -> Picasso.get().load(R.mipmap.suc).into(viewImage)
                null -> Picasso.get().load(R.mipmap.fail).into(viewImage)
            }
        } else {
            Picasso.get().load(dataSource[position]).into(viewImage)
        }


        if (position == 12) {
            view.setOnClickListener {
                if (success == null)
                    listener.onItemClick(position)
            }
        }

        container.addView(view, 0)
        return view
    }

    override fun getCount() = dataSource.size
}