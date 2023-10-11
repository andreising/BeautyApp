package com.andreisingeleytsev.beautyapp.ui.utils

import com.andreisingeleytsev.beautyapp.R

sealed class Category(val imageRes: Int, val stringRes: Int, val tips: List<Tip>) {
    object Body :
        Category(R.drawable.categ_1, R.string.categ_1, listOf(Tip.Body1, Tip.Body2, Tip.Body3))

    object Face :
        Category(R.drawable.categ_2, R.string.categ_2, listOf(Tip.Face1, Tip.Face2, Tip.Face3))

    object Hair :
        Category(R.drawable.categ_3, R.string.categ_3, listOf(Tip.Hair1, Tip.Hair2, Tip.Hair3))

    object MakeUp : Category(
        R.drawable.categ_4,
        R.string.categ_4,
        listOf(Tip.MakeUp1, Tip.MakeUp2, Tip.MakeUp3)
    )

    object Nails : Category(
        R.drawable.categ_5,
        R.string.categ_5,
        listOf(Tip.Nails1, Tip.Nails2, Tip.Nails3)
    )
}

sealed class Tip(val imageRes: Int, val nameStringRes: Int,  val titleStringRes: Int) {
    object Body1 : Tip(R.drawable.body1, R.string.body_1_name, R.string.body_1_title)
    object Body2 : Tip(R.drawable.body2, R.string.body_2_name, R.string.body_2_title)
    object Body3 : Tip(R.drawable.body3, R.string.body_3_name, R.string.body_3_title)
    object Face1 : Tip(R.drawable.face1, R.string.face_1_name, R.string.face_1_title)
    object Face2 : Tip(R.drawable.face2, R.string.face_2_name, R.string.face_2_title)
    object Face3 : Tip(R.drawable.face3, R.string.face_3_name, R.string.face_3_title)
    object Hair1 : Tip(R.drawable.hair1, R.string.hair_1_name, R.string.hair_1_title)
    object Hair2 : Tip(R.drawable.hair2, R.string.hair_2_name, R.string.hair_2_title)
    object Hair3 : Tip(R.drawable.hair3, R.string.hair_3_name, R.string.hair_3_title)
    object MakeUp1 : Tip(R.drawable.make1, R.string.make_1_name, R.string.make_1_title)
    object MakeUp2 : Tip(R.drawable.make2, R.string.make_2_name, R.string.make_2_title)
    object MakeUp3 : Tip(R.drawable.make3, R.string.make_3_name, R.string.make_3_title)
    object Nails1 : Tip(R.drawable.nails1, R.string.nails_1_name, R.string.nails_1_title)
    object Nails2 : Tip(R.drawable.nails2, R.string.nails_2_name, R.string.nails_2_title)
    object Nails3 : Tip(R.drawable.nails3, R.string.nails_3_name, R.string.nails_3_title)
}

object CategoryConverter {
    fun fromIndexToCategory(index: Int): Category {
        return when (index) {
            0 -> {
                Category.Body
            }

            1 -> {
                Category.Face
            }

            2 -> {
                Category.Hair
            }

            3 -> {
                Category.MakeUp
            }

            4 -> {
                Category.Nails
            }

            else -> {
                Category.Body
            }
        }
    }
}
