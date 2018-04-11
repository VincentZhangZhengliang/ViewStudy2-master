package com.topzrt.viewstudy2.utils

import android.content.Context

/**
 * Created by Vincent;
 * Created on 2017/12/15;
 * DSC:
 */
class SpUtils {

    companion object {

        /**
         *  存放String
         */
        fun saveString(context: Context, key: String, value: String, spname: String = "sp") {
            saveAny(context, key, value, spname)
        }

        fun saveLong(context: Context, key: String, value: Long, spname: String = "sp") {
            saveAny(context, key, value, spname)
        }

        /**
         *  存放boolean
         */
        fun saveBoolean(context: Context, key: String, value: Boolean, spname: String = "sp") {
            saveAny(context, key, value, spname)
        }

        /**
         *  存放Int
         */
        fun saveInt(context: Context, key: String, value: Int, spname: String = "sp") {
            saveAny(context, key, value, spname)
        }

        /**
         *  存放数据
         */
        fun saveAny(context: Context, key: String, value: Any, spname: String = "sp") {
            val spf = context.getSharedPreferences(spname, Context.MODE_PRIVATE)
            val edit = spf.edit()
            when (value) {
                is String  -> edit.putString(key, value)
                is Boolean -> edit.putBoolean(key, value)
                is Int     -> edit.putInt(key, value)
                is Long    -> edit.putLong(key, value)
            }
            edit.apply()
        }


        /**
         *  获取String
         */
        fun getString(context: Context, key: String, defValue: String,
                      spname: String = "sp"): String {
            return getAny(context, key, defValue, spname) as String
        }

        /**
         *  获取boolean
         */
        fun getBoolean(context: Context, key: String, defValue: Boolean,
                       spname: String = "sp"): Boolean {
            return getAny(context, key, defValue, spname) as Boolean
        }

        /**
         *  获取Int
         */
        fun getInt(context: Context, key: String, defValue: Int, spname: String = "sp"): Int {
            return getAny(context, key, defValue, spname) as Int
        }

        fun getLong(context: Context, key: String, defValue: Long, spname: String = "sp"): Long {
            return getAny(context, key, defValue, spname) as Long
        }

        /**
         *  存放数据
         */
        fun getAny(context: Context, key: String, defValue: Any, spname: String = "sp"): Any {
            val sp = context.getSharedPreferences(spname, Context.MODE_PRIVATE)
            return when (defValue) {
                is Boolean -> sp.getBoolean(key, defValue)
                is Int     -> sp.getInt(key, defValue)
                is Long    -> sp.getLong(key, defValue)
                is String  -> sp.getString(key, defValue)
                else       -> "Error"
            }
        }
    }

}


