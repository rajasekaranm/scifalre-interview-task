package com.scifalre.chat.ui.sender.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.scifalre.chat.databinding.ItemMessageListBinding
import com.scifalre.chat.data.model.ChatMessage

class ViewHolderMessage(private val binding: ItemMessageListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(message: ChatMessage) {
        binding.message.text = message.message
    }

}