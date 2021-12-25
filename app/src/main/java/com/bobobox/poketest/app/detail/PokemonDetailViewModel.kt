package com.bobobox.poketest.app.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bobobox.poketest.resources.data.entity.FavPokemon
import com.bobobox.poketest.resources.data.entity.Pokemon
import com.bobobox.poketest.resources.data.entity.PokemonDetail.AbilityData
import com.bobobox.poketest.resources.data.entity.PokemonDetail.PokemonDetail
import com.bobobox.poketest.resources.data.repository.OfflinePokeRepository
import com.bobobox.poketest.resources.data.repository.OnlinePokeRepository
import com.bobobox.poketest.resources.util.base.BaseViewModel
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonDetailViewModel(val onlineRepo : OnlinePokeRepository, val offlineRepo : OfflinePokeRepository) : BaseViewModel() {
    val monster: LiveData<PokemonDetail> get() = _monster
    private val _monster = MutableLiveData<PokemonDetail>()

    val favorited: LiveData<Boolean> get() = _favorited
    private val _favorited = MutableLiveData<Boolean>()

    val ability: LiveData<AbilityData.Ability> get() = _ability
    private val _ability = MutableLiveData<AbilityData.Ability>()

    fun getPokemonAbility(ids : List<Int>) {
        viewModelScope.launch {
            onlineRepo.getPokemonAbility(ids)
                .buffer()
                .collect {
                    _ability.postValue(it)
                }
        }
    }

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
                offlineRepo.addFavoriteMonster(pokemon).also {
                    Log.d("fav", "fav $it")
                    isMonsterFav(pokemon.id)
                }
            } else {
                offlineRepo.removeFavoriteMonster(pokemon).also {
                    Log.d("fav-remove", "fav $it")
                    isMonsterFav(pokemon.id)
                }
            }
        }
    }
}