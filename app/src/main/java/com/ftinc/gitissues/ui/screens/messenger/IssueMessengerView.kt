package com.ftinc.gitissues.ui.screens.messenger

import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import com.ftinc.gitissues.api.Label
import com.ftinc.gitissues.ui.BaseView
import com.ftinc.gitissues.ui.adapter.delegate.BaseIssueMessage
import com.ftinc.gitissues.ui.adapter.delegate.CommentIssueMessage

/**
 * Created by r0adkll on 11/6/16.
 */

interface IssueMessengerView: BaseView{

    fun setStatus(status: String, @ColorRes color: Int)
    fun setNumber(number: String)
    fun setIssueTitle(title: String)
    fun setOwnerAvatar(url: String)
    fun setOwnerName(name: String)
    fun setOpenDate(date: String)

    fun setLabels(labels: List<Label>)
    fun setMessengerItems(items: List<BaseIssueMessage>)

    fun hideLoading()
    fun appendComment(comment: CommentIssueMessage?)
    fun hideInput()

}