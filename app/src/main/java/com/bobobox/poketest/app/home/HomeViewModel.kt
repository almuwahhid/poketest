package com.bobobox.poketest.app.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bobobox.poketest.resources.data.entity.FavPokemon
import com.bobobox.poketest.resources.data.entity.Pokemon
import com.bobobox.poketest.resources.data.repository.OfflinePokeRepository
import com.bobobox.poketest.resources.data.repository.OnlinePokeRepository
import com.bobobox.poketest.resources.util.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(val onlineRepo : OnlinePokeRepository, val offlineRepo : OfflinePokeRepository) : BaseViewModel() {
    val monsters: LiveData<Pair<List<Pokemon>, Boolean>> get() = _monsters
    private val _monsters = MutableLiveData<Pair<List<Pokemon>, Boolean>>()

    private var offset = 0

    val favmonsters: LiveData<List<FavPokemon>> get() = _favmonsters
    private val _favmonsters = MutableLiveData<List<FavPokemon>>()

    fun requiredRefresh(after : () -> Unit) {
        offset = 0
        after()
    }

    fun getMonsters() {
        viewModelScope.launch {
            if(offset == 0) _loading.postValue(true)
            onlineRepo.getPokemonList(offset).fold(
                fnL = {
                    if(offset == 0) _loading.postValue(false)
                    _monsters.postValue(Pair(it.first, offset == 0))
                    offset = it.second.toInt()
                },
                fnR = {
                    if(offset == 0) _loading.postValue(false)
                    _error_message.postValue(it.second)
                }
            )
        }
    }

    fun getFavMonsters() {
        viewModelScope.launch {
            _loading.postValue(true)
            offlineRepo.getFavoriteMonsters().let {
                _loading.postValue(false)
                _favmonsters.postValue(it)
            }
        }
    }
}