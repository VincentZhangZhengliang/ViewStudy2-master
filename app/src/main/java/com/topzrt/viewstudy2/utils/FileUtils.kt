package com.topzrt.viewstudy2.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import com.topzrt.viewstudy2.bean.User
import java.io.*

/**
 * Created by Vincent;
 * Created on 2017/12/29;
 * DSC:
 */
class FileUtils {

    companion object {

        @SuppressLint("NewApi")
        fun saveLogin(context: Context, user: User?) {
            var oos: ObjectOutputStream? = null
            try {
                val file = File(context.cacheDir.absolutePath, "user")
                val fos = FileOutputStream(file)
                oos = ObjectOutputStream(fos)
                oos.writeObject(user)
                oos.flush()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                oos?.close()
            }
        }

        @RequiresApi(Build.VERSION_CODES.N)
        fun getLogin(context: Context): User? {
            var ois: ObjectInputStream? = null
            var user: User? = null
            try {
                val file = File(context.cacheDir.absolutePath, "user")
                val fis = FileInputStream(file)
                ois = ObjectInputStream(fis)
                user = ois.readObject() as User?
                L.e("getUser : ${user.toString()}")
                return user
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                ois?.close()
            }
            return user
        }

    }
}