package io.ciox.news.ui.home

import androidx.lifecycle.*
import io.ciox.news.domain.PostRepository
import io.ciox.news.domain.local.model.Post
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: PostRepository) : ViewModel() {

    val allPosts:LiveData<List<Post>> = repository.allPosts.asLiveData()


    fun insertPost(post: Post) = viewModelScope.launch {
        repository.insertPost(post)
    }
}

class HomeViewModelFactory (private val repository: PostRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkoown model class")
    }
}