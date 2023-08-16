package com.agmoacademy.quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agmoacademy.quizapp.model.Question
import com.agmoacademy.quizapp.network.QuizApi
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [MainActivity].
 */
class QuizViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()
    private val _list = MutableLiveData<List<Question>>()

    // The external immutable LiveData for the request status
    val status: LiveData<ApiStatus> = _status
    val quizList: LiveData<List<Question>> = _list

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [Question] [List] [LiveData].
     */
     fun getQuizList() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val listResult = QuizApi.retrofitService.getQuizList(
                    amount = 10,
                    category = 18,
                    difficulty = "easy",
                    type = "multiple"
                )
                _list.value = listResult.results
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _list.value = listOf()
            }
        }
    }
}
