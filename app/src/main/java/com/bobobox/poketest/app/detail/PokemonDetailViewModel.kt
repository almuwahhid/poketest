package com.bobobox.poketest.app.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bobobox.poketest.resources.data.entity.FavPokemon
import com.bobobox.poketest.resources.data.entity.Pokemon
import com.bobobox.poketest.resources.data.entity.PokemonDetail.PokemonDetail
import com.bobobox.poketest.resources.data.repository.OfflinePokeRepository
import com.bobobox.poketest.resources.data.repository.OnlinePokeRepository
import com.bobobox.poketest.resources.util.base.BaseViewModel
import kotlinx.coroutines.launch

class PokemonDetailViewModel(val onlineRepo : OnlinePokeRepository, val offlineRepo : OfflinePokeRepository) : BaseViewModel() {
    val monster: LiveData<PokemonDetail> get() = _monster
    private val _monster = MutableLiveData<PokemonDetail>()

    val favorited: LiveData<Boolean> get() = _favorited
    private val _favorited = MutableLiveData<Boolean>()

    fun getPokemonDetail(id : Int) {
        viewModelScope.launch {
            _loading.postValue(true)
            onlineRepo.getPokemonDetail(id).fold(
                fnL = {
                    _loading.postValue(false)
                    _monster.postValue(it)
                },
                fnR = {
                    _loading.postValue(false)
                    _error_message.postValue(it.second)
                }
            )
        }
    }

    fun isMonsterFav(id : Int) {
        viewModelScope.launch {
            offlineRepo.isMonsterFavorited(id).let {
                _favorited.postValue(it)
            }
        }
    }

    fun removeOrAdd(pokemon : FavPokemon, favorited : Boolean) {
        viewModelScope.launch {
            if(favorited) {
                offlineRepo.addFavoriteMonster(pokemon)
            } else {
                offlineRepo.removeFavoriteMonster(pokemon)
            }
        }
        isMonsterFav(pokemon.id)
    }
}