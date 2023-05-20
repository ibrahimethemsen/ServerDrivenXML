package com.ibrahimethemsen.serverdrivenxml.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ibrahimethemsen.serverdrivenxml.DEF_STRING
import com.ibrahimethemsen.serverdrivenxml.R
import com.ibrahimethemsen.serverdrivenxml.databinding.ContainerActivityBinding
import com.ibrahimethemsen.serverdrivenxml.databinding.ContainerAppbarBinding
import com.ibrahimethemsen.serverdrivenxml.databinding.ContainerMockBinding
import com.ibrahimethemsen.serverdrivenxml.model.ContainerActivity
import com.ibrahimethemsen.serverdrivenxml.model.ContainerAppbar
import com.ibrahimethemsen.serverdrivenxml.model.ContainerMock
import com.ibrahimethemsen.serverdrivenxml.model.ItemActivity
import com.ibrahimethemsen.serverdrivenxml.model.ViewTypeModel

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val viewTypeList = mutableListOf<ViewTypeModel>()

    fun setViewTypeList(newViewTypeList: List<ViewTypeModel>) {
        viewTypeList.apply {
            clear()
            addAll(newViewTypeList)
        }
        notifyDataSetChanged()
    }

    private val userListActivity = mutableListOf<ItemActivity>()

    fun setUserListActivity(newUserListActivity: List<ItemActivity>) {
        userListActivity.apply {
            clear()
            addAll(newUserListActivity)
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VT_APP_BAR -> {
                AppbarViewHolder(
                    ContainerAppbarBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            VT_ACTIVITY -> {
                ActivityViewHolder(
                    ContainerActivityBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            VT_MOCK -> {
                MockViewHolder(
                    ContainerMockBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                MockViewHolder(
                    ContainerMockBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = viewTypeList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        viewTypeList[position].apply {
            when (viewType) {
                ID_APP_BAR -> {
                    (holder as AppbarViewHolder).bind(containerAppbar)
                }

                ID_ACTIVITY -> {
                    (holder as ActivityViewHolder).bind(containerActivity, userListActivity)
                }

                ID_MOCK -> {
                    (holder as MockViewHolder).bind(containerMock)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (viewTypeList[position].viewType) {
            ID_APP_BAR -> VT_APP_BAR
            ID_ACTIVITY -> VT_ACTIVITY
            ID_MOCK -> VT_MOCK
            else -> {
                VT_MOCK
            }
        }
    }

    companion object {
        const val VT_APP_BAR = 1
        const val VT_MOCK = 2
        const val VT_ACTIVITY = 3
        const val ID_APP_BAR = "container_appbar"
        const val ID_ACTIVITY = "container_activity"
        const val ID_MOCK = "container_mock"
    }
}

class AppbarViewHolder(private val containerAppbarBinding: ContainerAppbarBinding) :
    RecyclerView.ViewHolder(containerAppbarBinding.root) {
    fun bind(containerAppbar: ContainerAppbar) {
        containerAppbarBinding.apply {

            if (containerAppbar.startIconDrawable != String.DEF_STRING){
                containerAppbarSearchTil.startIconDrawable = ContextCompat.getDrawable(containerAppbarBinding.root.context, R.drawable.ic_search)
            }
            containerAppbarSearchTil.hint = containerAppbar.searchHint

            when(containerAppbar.endDrawable){
                "search" -> containerAppbarEndIb.setImageDrawable(ContextCompat.getDrawable(containerAppbarBinding.root.context, R.drawable.ic_search))
                "favorite" -> containerAppbarEndIb.setImageDrawable(ContextCompat.getDrawable(containerAppbarBinding.root.context, R.drawable.ic_favorite))
                "notification" -> containerAppbarEndIb.setImageDrawable(ContextCompat.getDrawable(containerAppbarBinding.root.context, R.drawable.ic_notification))
                "person" -> containerAppbarEndIb.setImageDrawable(ContextCompat.getDrawable(containerAppbarBinding.root.context, R.drawable.ic_person))
            }

        }
    }
}

class MockViewHolder(private val mockBinding: ContainerMockBinding) :
    RecyclerView.ViewHolder(mockBinding.root) {
    fun bind(containerMock: ContainerMock) {}
}

class ActivityViewHolder(private val activityBinding: ContainerActivityBinding) :
    RecyclerView.ViewHolder(activityBinding.root) {
    fun bind(
        containerActivity: ContainerActivity,
        userListActivity: List<ItemActivity>
    ) {
        activityBinding.apply {
            containerActivityTitle.text = containerActivity.title
            containerActivityTitle.textSize = containerActivity.titleSize
            containerActivityTitle.setUiTextColor(containerActivity.titleColor)
            if (containerActivity.endTitle == String.DEF_STRING) {
                containerActivityEndBtn.visibility = View.GONE
            } else {
                containerActivityEndBtn.visibility = View.VISIBLE
                containerActivityEndBtn.text = containerActivity.endTitle
            }
            containerActivityProductRv.adapter = ActivityAdapter().apply {
                setActivityUi(containerActivity.adapterActivity)
                setActivityList(userListActivity)
            }
        }
    }
}