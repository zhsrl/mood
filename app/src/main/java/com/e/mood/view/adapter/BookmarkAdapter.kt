package com.e.mood.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.mood.R
import com.e.mood.model.Product

class BookmarkAdapter(val productList: List<Product>): RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view){

        private lateinit var productImage: ImageView
        private lateinit var productName: TextView
        private lateinit var productDescription: TextView
        private lateinit var productSizes: TextView

        fun bind(product: Product?){
            init()

            Glide.with(view)
                    .load(product?.productImage)
                    .into(productImage)

            productName.text = product?.productName
            productDescription.text = product?.productDescription
            productSizes.text = product?.productSizes.toString()

        }

        fun init(){
            productImage = view.findViewById(R.id.IV_bookmark_product_image)
            productName = view.findViewById(R.id.TV_bookmark_item_model_product_name)
            productDescription = view.findViewById(R.id.TV_bookmark_item_model_product_description)
            productSizes = view.findViewById(R.id.TV_bookmark_item_model_product_sizes)
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bookmark_item_model, null, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.ViewHolder, position: Int) {
        holder.bind(productList[position])


    }

    override fun getItemCount(): Int {
        return productList.size
    }
}