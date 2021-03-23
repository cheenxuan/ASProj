package me.xuan.demo.ans.proj

import android.os.Bundle
import android.os.PersistableBundle
import me.xuan.demo.ans.proj.common.ui.component.HiBaseActivity
import me.xuan.demo.ans.proj.logic.MainActivityLogic

class MainActivity : HiBaseActivity(), MainActivityLogic.ActivityProvider {
    
    var activityLogic: MainActivityLogic? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityLogic = MainActivityLogic(this, savedInstanceState)
        
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        activityLogic?.onSaveInstanceState(outState)
    }
}