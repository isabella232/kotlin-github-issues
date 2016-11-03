package com.ftinc.gitissues.api

/**
 * User Model
 */
data class User(val id: Long,
                val login: String,
                val avatar_url: String,
                val type: String,
                val name: String?,
                val company: String?,
                val location: String?,
                val email: String?,
                val bio: String?,
                val public_repos: Int?,
                val followers: Int?,
                val following: Int?,
                val contributions: Int?)

/**
 * Repository Model
 */
data class Repository(val id: Long,
                      val owner: User,
                      val name: String,
                      val full_name: String,
                      val description: String,
                      val private: Boolean,
                      val fork: Boolean,
                      val forks_count: Int,
                      val stargazers_count: Int,
                      val watchers_count: Int,
                      val size: Int,
                      val default_branch: String,
                      val open_issues_count: Int,
                      val has_issues: Boolean,
                      val pushed_at: String,
                      val created_at: String,
                      val updated_at: String)

/**
 * Issue Model
 */
data class Issue(val id: Long,
                 val number: Int,
                 val title: String,
                 val body: String,
                 val state: String,
                 val user: User,
                 val labels: List<Label>,
                 val assignee: User?,
                 val milestone: Milestone?,
                 val locked: Boolean,
                 val comments: Int,
                 val closed_at: String?,
                 val created_at: String,
                 val updated_at: String,
                 val closed_by: User?)

/**
 * Label Model
 */
data class Label(val id: Long,
                 val url: String,
                 val name: String,
                 val color: String,
                 val default: Boolean)

/**
 * Milestone Model
 */
data class Milestone(val id: Long,
                     val number: Int,
                     val title: String,
                     val state: String,
                     val description: String,
                     val creator: User,
                     val open_issues: Int,
                     val closed_issues: Int,
                     val created_at: String,
                     val updated_at: String,
                     val closed_at: String,
                     val due_on: String)

/**
 * Issue Comment Model
 */
data class Comment(val id: Long,
                   val body: String,
                   val user: User,
                   val created_at: String,
                   val updated_at: String)

/**
 * Event Constants
 */
enum class Events(val event: String){
    CLOSED("closed"),
    REOPENED("reopened"),
    SUBSCRIBED("subscribed"),
    MERGED("merged"),
    REFERENCED("referenced"),
    MENTIONED("mentioned"),
    ASSIGNED("assigned"),
    UNASSIGNED("unassigned"),
    LABELED("labeled"),
    UNLABELED("unlabeled"),
    MILESTONED("milestoned"),
    DEMILESTONED("demilestoned"),
    RENAMED("renamed"),
    LOCKED("locked"),
    UNLOCKED("unlocked"),
    HEAD_REF_DELETED("head_ref_deleted"),
    HEAD_REF_RESTORED("head_ref_restored");

    companion object {
        fun find(tag: String): Events = Events.values().first { it.event == tag }
    }
}

/**
 * Issue Event Model
 */
data class Event(val id: Long,
                 val actor: User,
                 val commit_id: String,
                 val event: String,
                 val created_at: String,
                 val label: Label?,
                 val assignee: User?,
                 val assigner: User?,
                 val milestone: Milestone?)