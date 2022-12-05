package ru.karpenko.dzen.presentation.categories

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.karpenko.dzen.R
import ru.karpenko.dzen.databinding.CardCategoryBinding
import ru.karpenko.dzen.domain.model.Category

class CategoriesAdapter(private val onClickListener: (category: Category) -> Unit) :
    ListAdapter<Category, CategoriesAdapter.CategoriesViewHolder>(DiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding =
            CardCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CategoriesViewHolder(
        private val binding: CardCategoryBinding,
        private val onClickListener: (category: Category) -> Unit,
    ) :
        ViewHolder(binding.root) {
        fun bind(category: Category) {
            with(binding) {
                cardCategoryMain.nameTextView.text = category.name
                cardCategoryMain.iconImageView.setImageResource(category.iconId)
                cardCategoryInfo.infoTextView.text = category.info
                cardCategoryMain.infoImageView.setOnClickListener {
                    flipCard(binding.root.context,
                        cardCategoryInfoFrameLayout,
                        cardCategoryMainFrameLayout) {

                        cardCategoryInfo.infoTextView.setOnClickListener {
                            flipCard(binding.root.context,
                                cardCategoryMainFrameLayout,
                                cardCategoryInfoFrameLayout) {
                                cardCategoryMain.infoImageView.setOnClickListener(null)
                            }
                        }
                    }
                }

                root.setOnClickListener {
                    onClickListener(category)
                }
            }
        }

        private fun flipCard(
            context: Context,
            visibleView: View,
            inVisibleView: View,
            action: () -> Unit,
        ) {
            try {
                visibleView.isVisible = true
                val flipOutAnimatorSet =
                    AnimatorInflater.loadAnimator(
                        context,
                        R.animator.flip_out
                    ) as AnimatorSet
                flipOutAnimatorSet.setTarget(inVisibleView)
                val flipInAnimationSet =
                    AnimatorInflater.loadAnimator(
                        context,
                        R.animator.flip_in
                    ) as AnimatorSet
                flipInAnimationSet.setTarget(visibleView)
                flipOutAnimatorSet.start()
                flipInAnimationSet.start()
                flipInAnimationSet.doOnEnd {
                    inVisibleView.isInvisible = true
                    action()
                }
            } catch (e: Exception) {
                Log.i("ERROR", e.localizedMessage)
                //logHandledException(e)
            }
        }

    }

    class DiffUtilItemCallback : ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
            oldItem == newItem

    }
}
