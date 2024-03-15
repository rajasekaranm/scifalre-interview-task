package  com.scifalre.chat.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.scifalre.chat.data.repository.AppRepository
import com.scifalre.chat.data.repository.AppRepositoryImpl

class AppViewModel(application: Application) : AndroidViewModel(application) {
    private val appRepository: AppRepository

    init {
        appRepository = AppRepositoryImpl(getApplication<Application>().applicationContext)
    }
    val messageLiveData = appRepository.messageLiveData

    fun addMessage(message :String){
       appRepository.addMessage(message)

    }
    fun getMessages(){
        appRepository.getMessage()
    }

}