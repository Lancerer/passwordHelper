package com.lancer.passwordhelper.utils

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators
import com.bumptech.glide.Glide
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey


/**
 * @author lancer
 * @des
 * @Date 2020/8/11 10:27
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object FingerPrintUtils {

    /**
     * 判断是否支持指纹
     */
    fun isSupportFingerPrint(activity: Activity): Boolean {
        if (Build.VERSION.SDK_INT < 23) {
            Toast.makeText(activity, "您的系统版本过低，不支持指纹功能", Toast.LENGTH_SHORT).show();
            return false
        } else {

            //键盘锁管理者
            val keyguardManager: KeyguardManager =
                activity.getSystemService(KeyguardManager::class.java)
            //指纹管理者
            val fingerprintManager: FingerprintManager =
                activity.getSystemService(FingerprintManager::class.java)
            if (!fingerprintManager.isHardwareDetected) {//判断硬件支不支持指纹
                Toast.makeText(activity, "您的手机不支持指纹功能", Toast.LENGTH_SHORT).show();
                return false
            } else if (!keyguardManager.isKeyguardSecure) {//还未设置锁屏
                Toast.makeText(activity, "您还未设置锁屏，请先设置锁屏并添加一个指纹", Toast.LENGTH_SHORT).show();
                return false
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {//指纹未登记
                Toast.makeText(activity, "您至少需要在系统设置中添加一个指纹", Toast.LENGTH_SHORT).show();
                return false
            }
        }

        return true
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initKey(): KeyStore {
        try {
            val keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore.load(null)
            val keyGenerator: KeyGenerator =
                KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
            val builder = KeyGenParameterSpec.Builder(
                "default_key",
                KeyProperties.PURPOSE_ENCRYPT or
                        KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setUserAuthenticationRequired(true)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            keyGenerator.init(builder.build())
            keyGenerator.generateKey()
            return keyStore
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initCipher(): Cipher {
        val key = initKey().getKey("default_key", null) as SecretKey
        val cipher: Cipher = Cipher.getInstance(
            KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7
        )
        cipher.init(Cipher.ENCRYPT_MODE, key)

        return cipher;
    }


    /**
     * 硬件是否支持
     */
    fun isHardwareAvailable(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val bm = BiometricManager.from(context)
            val canAuthenticate = bm.canAuthenticate(Authenticators.BIOMETRIC_WEAK)
            !(canAuthenticate ==
                    BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ||
                    canAuthenticate == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE)

        } else {
            false
        }
    }

    /**
     * 是否有生物识别注册
     */
    fun hasBiometricEnrolled(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val bm = BiometricManager.from(context)
            val canAuthenticate = bm.canAuthenticate(Authenticators.BIOMETRIC_WEAK)
            (canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS)

        } else {
            false
        }
    }
}