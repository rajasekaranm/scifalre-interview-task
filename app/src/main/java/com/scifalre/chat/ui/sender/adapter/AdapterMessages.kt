package com.scifalre.chat.ui.sender.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scifalre.chat.data.model.ChatMessage
import com.scifalre.chat.databinding.ItemMessageListBinding
import com.scifalre.chat.ui.sender.adapter.viewholder.ViewHolderMessage

class AdapterMessages : RecyclerView.Adapter<ViewHolderMessage>() {
    private val messages = mutableListOf<ChatMessage>()

    fun addMessage(message: ChatMessage) {
        messages.add(message)
        notifyDataSetChanged()
    }
    fun addMessages(messageList: List<ChatMessage>) {
        messages.clear()
        messages.addAll(messageList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMessage =
        ViewHolderMessage(
            ItemMessageListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolderMessage, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size

}