package com.example.androidtraining_mvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.contributor_row.view.*

class ContributorAdapter(
    val context: Context,
    val contributors: List<Contributor>
): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, container: ViewGroup?): View {
        var rowView = LayoutInflater.from(context)
            .inflate(
                R.layout.contributor_row,
                container,
                false)
        val contributor = contributors[position]
        rowView.tv_row_id.text = contributor.id.toString()
        rowView.tv_row_name.text = contributor.name
        Picasso.get().load(contributor.avatarUrl).into(rowView.row_img_avartar)
        return rowView
    }

    override fun getItem(position: Int): Any {
        return contributors[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return contributors.size
    }

}