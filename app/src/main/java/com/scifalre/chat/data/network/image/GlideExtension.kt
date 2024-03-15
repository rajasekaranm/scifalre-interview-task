package  com.scifalre.chat.data.network.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String?) {
    url?.let {
        Glide.with(context).load(url).apply(RequestOptions().override(600, 200))
            .into(this)
    }
}