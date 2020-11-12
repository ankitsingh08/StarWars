package ankit.com.starwarepisodes.view.episodes

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ankit.com.starwarepisodes.util.UIResponseState
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Created by AnkitSingh on 10/29/20.
 */

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }

    @BindingAdapter("hideOnLoading")
    fun ViewGroup.hideOnLoading(responseState: UIResponseState<Nothing>) {
        visibility = if (responseState is UIResponseState.Loading)
            View.GONE
        else
            View.VISIBLE
    }

    @BindingAdapter("showOnLoading")
    fun ProgressBar.showOnLoading(responseState: UIResponseState<Nothing>) {
        visibility = if (responseState is UIResponseState.Loading)
            View.VISIBLE
        else
            View.GONE
    }

    @BindingAdapter("showOnError")
    fun TextView.showError(responseState: UIResponseState<Nothing>) {
        visibility = if (responseState is UIResponseState.Error)
            View.VISIBLE
        else
            View.GONE
        text = (responseState as UIResponseState.Error).exception.message
    }
}