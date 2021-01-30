package com.example.estrrado_sujeeshtask.data

data class ProductPojo(
    val `data`: Data,
    val httpcode: Int,
    val message: String,
    val status: String
){
    data class Data(
        val banner_slider: List<BannerSlider>,
        val cartcount: Int,
        val cartid: Int,
        val categories: List<Category>,
        val featured: List<Featured>,
        val logo: String,
        val profiledata: List<Any>,
        val regions: List<Region>
    ){
        data class BannerSlider(
            val slider_id: String,
            val slider_image: String,
            val slider_name: String
        )

        data class Category(
            val category_id: String,
            val category_image: String,
            val category_name: String
        )

        data class Featured(
            val currency: String,
            val current_stock: String,
            val prd_id: String,
            val prd_image: String,
            val prd_name: String,
            val price: String,
            val qty: String,
            val rating: String,
            val unit: String
        )

        data class Region(
            val country_code: String,
            val country_id: String,
            val country_name: String
        )
    }
}