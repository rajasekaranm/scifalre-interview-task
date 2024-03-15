package com.scifalre.chat.ui.receiver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.scifalre.chat.data.model.ChatMessage
import com.scifalre.chat.data.viewmodel.AppViewModel
import com.scifalre.chat.databinding.FragmentReceiverBinding
import com.scifalre.chat.ui.sender.adapter.AdapterMessages


class ReceiverFragment : Fragment() {
    private val appViewModel: AppViewModel by viewModels()

    private var binding: FragmentReceiverBinding? = null
    private val adapter = AdapterMessages()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReceiverBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.chatList?.adapter = adapter
        initObserver()
        appViewModel.getMessages()
    }

    private fun initObserver() {
        appViewModel.messageLiveData.observe(viewLifecycleOwner) {
            when (it.first) {
                "Message" -> {
                    adapter.addMessage(it.second as ChatMessage)
                }

                "MessageList" -> {
                    adapter.addMessages(it.second as List<ChatMessage>)
                }
            }
        }
    }

}

