package com.e.mood.view.ui.fragment

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.mood.R
import com.e.mood.model.Product
import com.e.mood.view.adapter.BookmarkAdapter


class BookmarkFragment : Fragment() {

    private lateinit var recView: RecyclerView
    private var adapter: BookmarkAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recView = view!!.findViewById(R.id.RV_bookmark_recView)
        adapter = BookmarkAdapter(productGenerator())
        recView.adapter = adapter
        adapter!!.notifyDataSetChanged()

        val layoutManager = LinearLayoutManager(context)
        recView.layoutManager = layoutManager
        layoutManager.orientation = LinearLayoutManager.VERTICAL
    }

    private fun productGenerator(): List<Product>{
        val product: MutableList<Product> = ArrayList()

        val p1: Product = Product(R.drawable.sneak1,
                "Nike Yellow",
                "Белый Nike с линиями",
                arrayListOf("39", "41" , "43"))
        val p2: Product = Product(R.drawable.sneak2,
                "Puma R34",
                "Черная Puma в стиле R34",
                arrayListOf("35", "36", "39", "41"))
        val p3: Product = Product(R.drawable.sneak3,
                "Reebok Classic",
                "Кроссовка TRADITION",
                arrayListOf("42", "44", "46"))
        val p4: Product = Product(R.drawable.sneak4,
                "Onitsuka Tiger",
                "Кроссовки Tiger Orizona",
                arrayListOf("39", "41.5"))
        val p5: Product = Product(R.drawable.sneak5,
                "Adidas",
                "Кроссовки OZWEEGO X",
                arrayListOf("36.5", "40", "43.5"))
        val p6: Product = Product(R.drawable.sneak6,
                "ASICS",
                "Кроссовки GEL-KUMO LYTE 2",
                arrayListOf("35", "36", "41"))

        val p7: Product = Product(R.drawable.sneak2,
                "NIKE EL Edition",
                "Кроссовки NIKE EL EDITION",
                arrayListOf("40", "42")
                )

        product.add(p1)
        product.add(p2)
        product.add(p3)
        product.add(p4)
        product.add(p5)
        product.add(p6)
        product.add(p7)

        return product
    }

}