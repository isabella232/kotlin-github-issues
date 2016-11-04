package com.ftinc.gitissues.ui.screens.home.recents

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.bindView
import com.ftinc.gitissues.R
import com.ftinc.gitissues.api.Issue
import com.ftinc.gitissues.di.components.HasComponent
import com.ftinc.gitissues.ui.BaseFragment
import com.ftinc.gitissues.ui.screens.home.HomeComponent
import com.ftinc.gitissues.ui.screens.home.recents.adapter.RecentsAdapter
import com.ftinc.kit.adapter.BetterRecyclerAdapter
import com.ftinc.kit.widget.EmptyView
import javax.inject.Inject

/**
 *
 * Project: kotlin-github-issues-client
 * Package: com.ftinc.gitissues.ui.screens.home.recents
 * Created by drew.heavner on 11/3/16.
 */

class RecentsFragment : BaseFragment(), RecentsView {

    /***********************************************************************************************
     *
     * Variables
     *
     */

    val recycler: RecyclerView by bindView(R.id.recycler)
    val emptyView: EmptyView by bindView(R.id.empty_view)

    lateinit var adapter: RecentsAdapter

    @Inject
    lateinit var presenter: RecentsPresenter

    /***********************************************************************************************
     *
     * Lifecycle Methods
     *
     */

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = RecentsAdapter(activity)
        adapter.setEmptyView(emptyView)
        adapter.setOnItemClickListener({ view, item, i ->
            showSnackBar("#${item.number} was clicked")
        })

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_item_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.loadLatestIssues()
    }

    /***********************************************************************************************
     *
     * Base Methods
     *
     */

    override fun setupComponent(){
        (activity as HasComponent<HomeComponent>).component.plus(RecentsModule(this))
            .inject(this)
    }

    /***********************************************************************************************
     *
     * View Methods
     *
     */

    override fun setRecentItems(items: List<Issue>) {
        adapter.clear()
        adapter.addAll(items)
        adapter.notifyDataSetChanged()
    }

    override fun setLoading(flag: Boolean) {
        when(flag){
            true -> emptyView.setLoading()
            false -> emptyView.setEmpty()
        }
    }

}
