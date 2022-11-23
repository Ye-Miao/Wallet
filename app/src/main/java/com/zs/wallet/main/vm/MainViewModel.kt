package com.zs.wallet.main.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zs.lib.base.net.RequestLoadState
import com.zs.lib.base.utils.ext.launch
import com.zs.lib.log.Logger
import com.zs.wallet.main.api.model.MainVo
import com.zs.wallet.main.api.model.PublicInfoVo
import com.zs.wallet.main.repo.MainRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 *
 * desc: MainViewModel 相关逻辑
 * date: 2019-12-16 18:24
 */
class MainViewModel : ViewModel() {

    private val mainRepository: MainRepository = MainRepository()
    val gitDataLiveData = MutableLiveData<List<MainVo>>()
    val publicInfoLiveData = MutableLiveData<RequestLoadState<PublicInfoVo>>()
    val loadStateLiveData = MutableLiveData<RequestLoadState<List<PublicInfoVo>>>()

    fun github() {
        viewModelScope.launch {
            val body = mainRepository.contributors()
                .body()
            gitDataLiveData.postValue(body)
            Log.e("Main", "body=$body,thread=${Thread.currentThread().name}")
        }
    }

    fun getPublicInfo() {
        launch({
            publicInfoLiveData.value = RequestLoadState.Loading("getPublicInfo")
            val resultVo = mainRepository.getPublicInfo()
            Logger.e("thread=${Thread.currentThread().name},result=$resultVo")
            if (resultVo?.isSuccess() == true) {
                publicInfoLiveData.value = RequestLoadState.Success(resultVo.data!!)
                return@launch
            }
            loadStateLiveData.value = RequestLoadState.Error(resultVo?.code, resultVo?.msg)
        }, {
            loadStateLiveData.value = RequestLoadState.Error(throwable = it)
        })
    }


    fun cpmplex() {
        launch({
            loadStateLiveData.value = RequestLoadState.Loading()
            val result1 = async { mainRepository.getPublicInfo2() }
            val result2 = async { mainRepository.getPublicInfo() }
            val result3 = async { mainRepository.getPublicInfo2() }

            val newList = arrayListOf<PublicInfoVo>()
            result1.await()
            newList.add(result1.await().data!!)
            newList.add(result2.await()?.data!!)
            newList.add(result3.await().data!!)
            Logger.e("thread=${Thread.currentThread().name}, newList=$newList")

            loadStateLiveData.value = RequestLoadState.Success(newList)
        }, {
            loadStateLiveData.value = RequestLoadState.Error(throwable = it)
        })
    }

    fun loadDbUsers() {
        viewModelScope.launch {
            val resultList = mainRepository.loadDbUser()
            Logger.e("db user= $resultList")
        }
    }
}