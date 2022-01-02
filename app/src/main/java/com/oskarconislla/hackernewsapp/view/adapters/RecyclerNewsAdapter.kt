package com.oskarconislla.hackernewsapp.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.oskarconislla.hackernewsapp.model.entities.New
import com.oskarconislla.hackernewsapp.viewmodel.NewViewModel
import com.oskarconislla.hackernewsapp.BR
import com.oskarconislla.hackernewsapp.view.NewDetailActivity

class RecyclerNewsAdapter ( var couponViewModel: NewViewModel ,var resource: Int) : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerNewsAdapter.CardCouponHolder>() {

    var coupons: List<New>? = null

    fun setCounponsList(coupons: List<New>?){
        this.coupons= coupons
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardCouponHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardCouponHolder(binding)
    }

    override fun getItemCount(): Int {
        return coupons?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardCouponHolder, p1: Int) {
        p0.setDataCard(couponViewModel, p1)

    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int{
        return resource
    }

    fun deleteItemAt(i : Int) {
        couponViewModel.deleteItemAt(i)

    }

    class CardCouponHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var mDetector : GestureDetectorCompat

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
         }

        fun setDataCard(couponViewModel: NewViewModel, position: Int){
            binding?.setVariable(BR.model, couponViewModel)
            binding?.setVariable(BR.position, position)
            binding?.root?.setOnClickListener{ view ->
                val context = view.context
                val showNewIntent = Intent(context,NewDetailActivity::class.java)
                showNewIntent.putExtra("NewSelected",couponViewModel.getNewAt(position))
                context.startActivity(showNewIntent)
            }

            binding?.executePendingBindings()
        }


    }
}