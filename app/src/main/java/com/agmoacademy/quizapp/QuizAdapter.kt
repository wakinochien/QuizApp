package com.agmoacademy.quizapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agmoacademy.quizapp.model.Question


/**
 * Adapter for the [RecyclerView] in [MainActivity]. Displays [Question] data object.
 */
class QuizAdapter(
    context: Context,
    private val dataset: MutableList<Question>,
) : RecyclerView.Adapter<QuizAdapter.ItemViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null


    fun isAnsweredCount(): Int = dataset.filter { it.isAnswered }.size
    fun isCorrectCount(): Int = dataset.filter { it.isCorrect }.size

    init {
        if (context is OnItemClickListener) onItemClickListener = context
    }

    interface OnItemClickListener {
        fun onItemClick()
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Quiz object.
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val radioButton1: RadioButton = view.findViewById(R.id.selection_1)
        val radioButton2: RadioButton = view.findViewById(R.id.selection_2)
        val radioButton3: RadioButton = view.findViewById(R.id.selection_3)
        val radioButton4: RadioButton = view.findViewById(R.id.selection_4)
    }

    /**
     * Create new views (invoked by the layout manager)
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount(): Int {
        return dataset.size
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = "${position + 1}. ${item.question}"

        val questionList = item.incorrectAnswers.toMutableList()
        questionList.add(item.correctAnswer)
        questionList.shuffle()

        holder.radioButton1.text = questionList.get(0)
        holder.radioButton2.text = questionList.get(1)
        holder.radioButton3.text = questionList.get(2)
        holder.radioButton4.text = questionList.get(3)
        holder.radioButton1.setOnClickListener {
            item.isAnswered = true
            item.isCorrect = holder.radioButton1.text == item.correctAnswer
            onItemClickListener?.onItemClick()
        }

        holder.radioButton2.setOnClickListener {
            item.isAnswered = true
            item.isCorrect = holder.radioButton2.text == item.correctAnswer
            onItemClickListener?.onItemClick()
        }

        holder.radioButton3.setOnClickListener {
            item.isAnswered = true
            item.isCorrect = holder.radioButton3.text == item.correctAnswer
            onItemClickListener?.onItemClick()
        }

        holder.radioButton4.setOnClickListener {
            item.isAnswered = true
            item.isCorrect = holder.radioButton4.text == item.correctAnswer
            onItemClickListener?.onItemClick()
        }
    }

    fun clear() {
        dataset.clear()
    }

    fun addAll(list: List<Question>) {
        dataset.addAll(list)
        notifyDataSetChanged()
    }
}
