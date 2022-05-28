package com.bignerdranch.android.nerdlauncher

import android.content.Intent
import android.content.pm.ResolveInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActivityAdapter(val activities: List<ResolveInfo>) :
        RecyclerView.Adapter<ActivityAdapter.ActivityHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int):
            ActivityAdapter.ActivityHolder {
        val layoutInflater = LayoutInflater.from(container.context)
        val view = layoutInflater
                .inflate(android.R.layout.simple_list_item_1, container, false)
        return ActivityHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityAdapter.ActivityHolder, position: Int) {
        val resolveInfo = activities[position]
        holder.bindActivity(resolveInfo)
    }

    override fun getItemCount(): Int {
        return activities.size
    }

    inner class ActivityHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView),
            View.OnClickListener {

        private val nameTextView = itemView as TextView
        private lateinit var resolveInfo: ResolveInfo

        init {
            nameTextView.setOnClickListener(this)
        }

        fun bindActivity(resolveInfo: ResolveInfo) {
            this.resolveInfo = resolveInfo
            val packageManager = itemView.context.packageManager
            val appName = resolveInfo.loadLabel(packageManager).toString()
            nameTextView.text = appName
        }

        override fun onClick(view: View) {
            val activityInfo = resolveInfo.activityInfo

            val intent = Intent(Intent.ACTION_MAIN).apply {
                setClassName(activityInfo.applicationInfo.packageName,
                        activityInfo.name)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            val context = view.context
            context.startActivity(intent)
        }
    }
}