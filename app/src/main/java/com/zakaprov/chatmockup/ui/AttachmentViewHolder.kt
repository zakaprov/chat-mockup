package com.zakaprov.chatmockup.ui

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.zakaprov.chatmockup.extensions.getParentMessage
import com.zakaprov.chatmockup.model.Attachment
import com.zakaprov.chatmockup.model.User
import kotlinx.android.synthetic.main.item_attachment.view.*

class AttachmentViewHolder(
    private val view: View,
    private val glideManager: RequestManager
) : RecyclerView.ViewHolder(view) {

    fun bind(attachment: Attachment, longClickListener: (String) -> Unit) = with(view) {
        if (attachment.getParentMessage()?.userId == User.SESSION_USER_ID) {
            item_attachment_root.gravity = Gravity.END
        } else {
            item_attachment_root.gravity = Gravity.START
        }

        glideManager.clear(item_attachment_image)
        glideManager
            .load(attachment.url)
            .apply(RequestOptions.fitCenterTransform())
            .into(item_attachment_image)

        item_attachment_title.text = attachment.title

        view.setOnLongClickListener {
            longClickListener.invoke(attachment.id)
            true
        }
    }
}
