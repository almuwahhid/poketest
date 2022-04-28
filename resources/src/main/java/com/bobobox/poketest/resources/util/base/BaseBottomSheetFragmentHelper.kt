package com.almuwahhid.themoviedb.resources.util.base

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.bobobox.poketest.resources.R
import com.bobobox.poketest.resources.databinding.HelperBinding
import com.bobobox.poketest.resources.util.base.HelperInterface
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment



abstract class BaseBottomSheetFragmentHelper<T : ViewBinding> : BottomSheetDialogFragment(), HelperInterface {
    //    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun getTheme(): Int = R.style.AppTheme_AppBarCustom

    private var _binding: T? = null
    val binding get() = _binding!!

    abstract fun observeViewModel()
    abstract fun bindingHelper(binding : T) : HelperBinding
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T

    protected abstract fun initView(view: View, savedInstanceState: Bundle?)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme)

    override fun onRefresh() = with(bindingHelper(binding)){
//        noconnection.visibility = View.GONE
//        servererror.visibility = View.GONE
//        btnRefresh.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setOnShowListener { d ->
            val frame =
                dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behaviour = BottomSheetBehavior.from(frame)
            behaviour.apply {
                state = BottomSheetBehavior.STATE_EXPANDED
                peekHeight = 0
                isHideable = true
                skipCollapsed = true
            }
        }
        initView(view, savedInstanceState)
    }

    open fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from<FrameLayout?>(bottomSheet!!)
        val layoutParams = bottomSheet.layoutParams
        val windowHeight = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    open fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onPause() {
//        hideKeyboard()
        super.onPause()
    }
}