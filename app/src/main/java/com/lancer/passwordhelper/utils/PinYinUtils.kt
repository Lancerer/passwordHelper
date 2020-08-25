package com.lancer.passwordhelper.utils

import android.widget.TextView
import com.lancer.passwordhelper.R
import net.sourceforge.pinyin4j.PinyinHelper
import java.util.*

/**
 * @author lancer
 * @des  截取字符串首个字符设置背景
 * @Date 2020/7/23 14:19
 */
class PinYinUtils {

    companion object {


        private fun getPinYinHeadChar(str: String): String {
            var convert: String = ""
            for (i in 0..str.length) {
                val word = str[i]
                val pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word)
                convert += if (pinyinArray.isNotEmpty()) {
                    pinyinArray[0][0]
                } else {
                    word
                }
            }
            convert = convert.toUpperCase(Locale.ROOT)
            return convert
        }


        /**
         * 得到中文首字母
         *
         * @param str
         * @return
         */
        private fun getPinYinHead(str: String): Int {
            val chars: CharArray?
            var convert = ""
            for (j in str.indices) {
                val word = str[j]
                val pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word)
                convert += if (pinyinArray != null) {
                    pinyinArray[0][0]
                } else {
                    word
                }
            }
            convert = convert.toUpperCase(Locale.ROOT)
            chars = convert.toCharArray()

            return if ((chars[0] >= 'A') && (chars[0] < 'G')) {
                1
            } else if ((chars[0] >= 'G') && (chars[0] < 'N')) {
                2
            } else if ((chars[0] >= 'N') && (chars[0] < 'T')) {
                3
            } else if ((chars[0] >= 'T') && (chars[0] < 'Z')) {
                4
            } else {
                5
            }

        }


        fun setTextBg(textView: TextView, username: String) {
            if (username.isNotEmpty()) {
                val type: Int
                if (username.length >= 2) {
                    textView.text = username.substring(0,2)
                    type = getPinYinHead(
                        username.substring(
                            username.length - 2,
                            username.length
                        )
                    )
                } else {
                    textView.text = username
                    type = getPinYinHead(username)
                }
                when (type) {
                    1 -> {
                        textView.setBackgroundResource(R.drawable.shape_textview_bg1)
                    }
                    2 -> {
                        textView.setBackgroundResource(R.drawable.shape_textview_bg2)

                    }
                    3 -> {
                        textView.setBackgroundResource(R.drawable.shape_textview_bg3)

                    }
                    4 -> {
                        textView.setBackgroundResource(R.drawable.shape_textview_bg4)

                    }
                    5 -> {
                        textView.setBackgroundResource(R.drawable.shape_textview_bg5)

                    }
                    else -> {
                        textView.setBackgroundResource(R.drawable.shape_textview_bg5)

                    }
                }
            }

        }
    }
}