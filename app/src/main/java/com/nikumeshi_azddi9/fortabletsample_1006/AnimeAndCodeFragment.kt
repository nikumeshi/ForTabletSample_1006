package com.nikumeshi_azddi9.fortabletsample_1006

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_anime_and_code.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AnimeAndCodeFragment : Fragment(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    enum class IVEnum(val code: Int, val resId: Int){
        IV1(1, R.id.bs_bar1),
        IV2(2, R.id.bs_bar2),
        IV3(3, R.id.bs_bar3),
        IV4(4, R.id.bs_bar4),
        IV5(5, R.id.bs_bar5),
        IV6(6, R.id.bs_bar6),
        IV7(7, R.id.bs_bar7),
        IV8(8, R.id.bs_bar8),
        IV9(9, R.id.bs_bar9);


        companion object {
            fun getCode(resId: Int) = values().find { resId == it.resId }?.code
            fun getResId(code: Int) = values().find { code == it.code }?.resId
        }
    }

    private fun initAlphaAnimation(): AlphaAnimation {
        val alphaAnimation = AlphaAnimation(1.0F,0.3F)
        alphaAnimation.apply {
            duration = 200
            repeatMode = Animation.REVERSE
            repeatCount = 2
        }
        return alphaAnimation
    }

    private fun initAlphaAnimation2(view: ImageView) =
        listOf<ObjectAnimator>(
            ObjectAnimator.ofFloat(view, "alpha", 1F),
            ObjectAnimator.ofFloat(view, "alpha", 0F),
            ObjectAnimator.ofFloat(view, "alpha", 1F),
            ObjectAnimator.ofFloat(view, "alpha", 0F),
            ObjectAnimator.ofFloat(view, "alpha", 1F)
        )


    private fun View.getLocation() = IntArray(2).also { this.getLocationOnScreen(it) }
    private fun View.getTranslationX(view: View): Float{
        val array1 = this.getLocation()
        val array2 = view.getLocation()
        return (array2[0] - array1[0]).toFloat()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_anime_and_code, container, false)

        val viewList = arrayListOf(IVEnum.IV9, IVEnum.IV5, IVEnum.IV6, IVEnum.IV8, IVEnum.IV3, IVEnum.IV1, IVEnum.IV2, IVEnum.IV4, IVEnum.IV7)
//        val alphaAnimation = initAlphaAnimation()

        var x = viewList.size-1

        view.findViewById<Button>(R.id.runBtn).setOnClickListener {
            val iv1 = view.findViewById<ImageView>(viewList[x].resId)
            val iv2 = view.findViewById<ImageView>(viewList[x-1].resId)

            AnimatorSet().apply {
                playSequentially( initAlphaAnimation2(iv1) )
                duration = 300
            }.start()
            AnimatorSet().apply {
                playSequentially( initAlphaAnimation2(iv2) )
                duration = 300
            }.start()

            when (viewList[x].code < viewList[x-1].code){
                true -> {
                    AnimatorSet().apply {
                        startDelay = 1500
                        playTogether(
                            ObjectAnimator.ofFloat(iv1, "translationX", iv1.getTranslationX(iv2)),
                            ObjectAnimator.ofFloat(iv2, "translationX", iv2.getTranslationX(iv1))
                        )
                        duration = 300
                    }.start()

                    val tmp = viewList[x]
                    viewList[x] = viewList[x-1]
                    viewList[x-1] = tmp
                }
            }
            x--
        }

//        view.findViewById<Button>(R.id.runBtn).setOnClickListener {
//            launch {
//                for (x in viewList.size-1 downTo  1){
//                    val iv1 = view.findViewById<ImageView>(viewList[x].resId)
//                    val iv2 = view.findViewById<ImageView>(viewList[x-1].resId)
//
//                    launch {
//                        iv1.startAnimation(alphaAnimation)
//                        iv2.startAnimation(alphaAnimation)
//                    }.join()
//
//                    when (viewList[x].code < viewList[x-1].code){
//                        true -> {
//                            launch {
//                                iv1.setColorFilter(Color.BLUE)
//                                iv2.setColorFilter(Color.RED)
//                            }.join()
//
//                            launch {
//                                val translationX1 = ObjectAnimator.ofFloat(iv1, "translationX", iv1.getTranslationX(iv2))
//                                val translationX2 = ObjectAnimator.ofFloat(iv2, "translationX", iv2.getTranslationX(iv1))
//
////                                Log.d("hoge", "iv1X: ${iv1.getLocation()[0]}, iv2X: ${iv2.getLocation()[0]}")
//
//                                AnimatorSet().apply {
//                                    playTogether(translationX1, translationX2)
//                                    duration = 300
//                                }.start()
//                                Thread.sleep(310)
//
//                                val tmp = viewList[x]
//                                viewList[x] = viewList[x-1]
//                                viewList[x-1] = tmp
//                            }.join()
//                        }
//                        false -> {
//                            launch {
//                                iv1.setColorFilter(Color.RED)
//                                iv2.setColorFilter(Color.BLUE)
//                            }.join()
//                        }
//                    }
//                    launch {
//                        iv1.colorFilter = null
//                        iv2.colorFilter = null
//
//                    }.join()
//                }
//            }
//        }

        return view
    }

//    fun show(alData: AlData){
//        //TODO 対応するアニメーションとコードを表示する
//    }

    //    private fun viewCompare(iv1: IVEnum, iv2: IVEnum){
//        val image1 = findViewById<ImageView>(iv1.resId)
//        val image2 = findViewById<ImageView>(iv2.resId)
//
//        when (iv1.code < iv2.code){
//            true -> {
//                image1.setColorFilter(Color.BLUE)
//                image2.setColorFilter(Color.RED)
//            }
//            false -> {
//                image1.setColorFilter(Color.RED)
//                image2.setColorFilter(Color.BLUE)
//            }
//        }
//    }
}
