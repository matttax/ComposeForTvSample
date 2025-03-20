package com.matttax.composefortvsample.leanback

import android.os.Bundle
import android.view.View
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import com.matttax.composefortvsample.data.LocalDataProvider

class MainFragment : BrowseSupportFragment() {

    private var pendingSelectionCategoryId = -1L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onItemViewClickedListener = ItemViewClickedListener()
        loadRows()
        if (pendingSelectionCategoryId != -1L) {
            val categoryAdapterList = (adapter as ArrayObjectAdapter).unmodifiableList<ListRow>()
            val categoryIndex = categoryAdapterList.indexOfFirst { it.id == pendingSelectionCategoryId }
            setSelectedPosition(categoryIndex, false)
            pendingSelectionCategoryId = -1L
        }
    }

    private fun loadRows() {
        val restaurants = LocalDataProvider.restaurants
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()
        for (restaurant in restaurants) {
            val listRowAdapter = ArrayObjectAdapter(cardPresenter)
            for (element in restaurant.dishes) {
                listRowAdapter.add(element)
            }
            val header = HeaderItem(restaurant.id, restaurant.name)
            rowsAdapter.add(ListRow(header, listRowAdapter))
        }
        adapter = rowsAdapter
    }

    private inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder,
            item: Any,
            rowViewHolder: RowPresenter.ViewHolder,
            row: Row
        ) = Unit
    }
}
