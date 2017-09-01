package com.lp.practice.neihantv.utils

import android.content.Context
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.io.*

/**
 * Created by LiPin on 2017/7/11 13:54.
 * 描述：
 */
object ObjectSaveUtils {

    fun saveObject(context: Context, name: String, value: Any) {
        Thread(Runnable {
            var fos: FileOutputStream? = null
            var oos: ObjectOutputStream? = null
            try {
                fos = context.openFileOutput(name, Context.MODE_PRIVATE)
                oos = ObjectOutputStream(fos)
                oos.writeObject(value)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (fos != null) {
                    fos.close()
                }
                if (oos != null) {
                    oos.close()
                }
            }
        })
    }

    fun getValue(context: Context, name: String): Any? {
        var any: Any? = null
        Observable.just(name)
                .subscribeOn(Schedulers.io())
                .map(Function<String, Any?> {
                    var fis: FileInputStream? = null
                    var ois: ObjectInputStream? = null
                    try {
                        fis = context.openFileInput(name)
                        if (fis == null) {
                            return@Function null
                        }
                        ois = ObjectInputStream(fis)
                        return@Function ois.readObject()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        if (fis != null) {
                            try {
                                fis.close()
                            } catch (e: IOException) {
                                // fis流关闭异常
                                e.printStackTrace()
                            }
                        }
                        if (ois != null) {
                            try {
                                ois.close()
                            } catch (e: IOException) {
                                // ois流关闭异常
                                e.printStackTrace()
                            }
                        }
                    }
                    return@Function null
                })
                .subscribe(Consumer<Any?> { t -> any = t }, Consumer<Throwable> { any = null })
        return any
    }

    fun deleteFile(name: String, context: Context) {
        context.deleteFile(name)
    }
}
