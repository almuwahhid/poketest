package com.bobobox.poketest.app.detail

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import com.bobobox.poketest.R
import com.bobobox.poketest.app.detail.adapter.AbilityAdapter
import com.bobobox.poketest.app.detail.adapter.AvatarAdapter
import com.bobobox.poketest.app.detail.adapter.MovesAdapter
import com.bobobox.poketest.databinding.ActivityPokemonDetailBinding
import com.bobobox.poketest.resources.GlobalConfig
import com.bobobox.poketest.resources.data.entity.FavPokemon
import com.bobobox.poketest.resources.data.entity.PokemonDetail.PokemonDetail
import com.bobobox.poketest.resources.data.mapper.toAbilities
import com.bobobox.poketest.resources.data.mapper.toListedAvatar
import com.bobobox.poketest.resources.util.base.BaseActivity
import com.bobobox.poketest.resources.util.ext.ToastShort
import com.bobobox.poketest.resources.util.ext.bounceEffect
import com.bobobox.poketest.resources.util.ext.toData
import org.koin.androidx.viewmodel.ext.android.viewModel


class PokemonDetailActivity : BaseActivity<ActivityPokemonDetailBinding>() {

    private val viewModel by viewModel<PokemonDetailViewModel>()

    private val pokemon : FavPokemon? by lazy {
        intent.getStringExtra(GlobalConfig.KEY_INTENT)?.let {
            it.toData()
        }
    }

    private val adapterMoves : MovesAdapter? by lazy {
        MovesAdapter()
    }

    private val adapterAbility : AbilityAdapter? by lazy {
        AbilityAdapter()
    }

    private val adapterAvatar : AvatarAdapter? by lazy {
        AvatarAdapter()
    }

    override val bindingInflater: (LayoutInflater) -> ActivityPokemonDetailBinding
        get() = ActivityPokemonDetailBinding::inflate

    override fun observeViewModel() = with(viewModel) {
        favorited.observe(this@PokemonDetailActivity, {
            pokemon!!.flag = it

            if (it) binding.btnFav.setImageResource(R.drawable.ic_love_fill)
            else binding.btnFav.setImageResource(R.drawable.ic_love_outline)
        })
        loading.observe(this@PokemonDetailActivity, {
            if (it) binding.helper.helperLoading.visibility = View.VISIBLE
            else binding.helper.helperLoading.visibility = View.GONE
        })

        error_message.observe(this@PokemonDetailActivity, {
            ToastShort(it)
            finish()
        })

        monster.observe(this@PokemonDetailActivity, {
            syncData(it)
        })
    }

    private fun syncData(monster: PokemonDetail) = with(binding) {
        monster.sprites?.let {
            adapterAvatar?.add(true, it.toListedAvatar())
        }?:fun() {
            adapterAvatar?.add(true, ArrayList<String>().apply { add("https://google.com") })
        }()
        monster.abilityData?.let {
            adapterAbility?.addAll(it.toAbilities())
        }
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(rvAvatar)
        indicator.attachToRecyclerView(rvAvatar, pagerSnapHelper)

        monster.moves?.let {
            adapterMoves?.add(true, it)
        }?:fun() {
            tvNomove.visibility = View.VISIBLE
        }()

        tvName.text = monster.name
        tvExp.text = "exp : ${monster.baseExperience}"
        tvHeight.text = "${monster.height} m"
        tvWeight.text = "${monster.weight} kg"
        tvSpecies.text = "${monster.species?.name}"

    }

    override fun initView(binding: ActivityPokemonDetailBinding): Unit = with(binding) {
        setSupportActionBar(appbar.toolbar)
        supportActionBar.let {
            it!!.setDisplayHomeAsUpEnabled(true)
            it!!.setTitle("")
            it!!.setHomeAsUpIndicator(R.drawable.ic_back)
        }

        rvAvatar.adapter = adapterAvatar

        rvMoves.adapter = adapterMoves

        pokemon?.let {
            viewModel.getPokemonDetail(it.id)
            viewModel.isMonsterFav(it.id)

            btnFav.setOnClickListener{ v ->
                viewModel.removeOrAdd(it, !it.flag)
                v.bounceEffect(this@PokemonDetailActivity)
            }
        }
    }

}