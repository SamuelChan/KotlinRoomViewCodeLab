package com.samuelcychan.codelab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter
    : ListAdapter<Word, WordListAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder =
        WordViewHolder.create(parent)

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) =
        holder.bind(getItem(position)?.word)

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(wordText: String?) { textItemView.text = wordText }

        companion object {
            fun create(container: ViewGroup): WordViewHolder {
                return LayoutInflater.from(container.context)
                    .inflate(R.layout.recyclerview_item, container, false)
                    .let{ WordViewHolder(it) }
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean =
            oldItem.word == newItem.word
    }
}