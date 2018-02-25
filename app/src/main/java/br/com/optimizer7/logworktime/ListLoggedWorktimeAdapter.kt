package br.com.optimizer7.logworktime

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.optimizer7.logworktime.Model.Worktime
import kotlinx.android.synthetic.main.logged_worktime_cell.view.*

class ListLoggedWorktimeAdapter(private val loggedWorktimes: ArrayList<Worktime>) : RecyclerView.Adapter<ListLoggedWorktimeAdapter.PlaceHolder>(){
    val mDataSet: MutableList<Worktime> = mutableListOf()

    override fun onBindViewHolder(holder: ListLoggedWorktimeAdapter.PlaceHolder, position: Int) {
        if(position == 0){
            val itemWorktime = null
            holder.bindWorktime(itemWorktime, position - 1)
        }else{
            if(itemCount >= 1){
                val itemWorktime = loggedWorktimes[position - 1]
                holder.bindWorktime(itemWorktime, position - 1)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListLoggedWorktimeAdapter.PlaceHolder{

        val inflater = LayoutInflater.from(parent.context)
        val inflatedView = inflater.inflate(R.layout.logged_worktime_cell, parent, false)
        return PlaceHolder(inflatedView, viewType)

    }

    override fun getItemCount(): Int {
        return loggedWorktimes.size + 1
    }

    class PlaceHolder(v: View, viewType: Int) : RecyclerView.ViewHolder(v), View.OnClickListener{

        var view: View = v
        var worktime: Worktime? = null
        var viewTypeId = viewType

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = itemView.context
        }

        fun bindWorktime(worktime: Worktime?, position: Int){
            if( position == -1){
                view.startWorktime.text = view.context.getString(R.string.start_worktime_label)
                view.startLunch.text = view.context.getString(R.string.lunch_worktime_label)
                view.doneLunch.text = view.context.getString(R.string.lunch_end_worktime_label)
                view.doneWorktime.text = view.context.getString(R.string.finish_worktime_label)
                view.dateWorktime.text = view.context.getString(R.string.date)


                view.startWorktime.setTextColor(ContextCompat.getColor(view.context, R.color.primary_dark_material_dark))
                view.startLunch.setTextColor(ContextCompat.getColor(view.context, R.color.primary_dark_material_dark))
                view.doneLunch.setTextColor(ContextCompat.getColor(view.context, R.color.primary_dark_material_dark))
                view.doneWorktime.setTextColor(ContextCompat.getColor(view.context, R.color.primary_dark_material_dark))
                view.dateWorktime.setTextColor(ContextCompat.getColor(view.context, R.color.primary_dark_material_dark))
            }else{
                this.worktime = worktime
                view.startWorktime.setText(IsEmptyOrNull(worktime!!.beginWorktime))
                view.startLunch.setText(IsEmptyOrNull(worktime!!.beginLunch))
                view.doneLunch.setText(IsEmptyOrNull(worktime!!.doneLunch))
                view.doneWorktime.setText(IsEmptyOrNull(worktime!!.doneWorktime))
                view.dateWorktime.setText(worktime!!.date)

                view.dateWorktime.setTextColor(ContextCompat.getColor(view.context, R.color.colorPrimary))
            }
        }

        fun IsEmptyOrNull(data: String?) : String{
            if(data.equals("") || data == null){
                return "N/A"
            }
            else{
                return data
            }
        }
    }
}